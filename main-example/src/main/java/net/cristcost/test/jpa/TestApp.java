/*
 * Copyright (C) 2013 by @cristcost
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.cristcost.test.jpa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * The Class MemoryService.
 */
public class TestApp implements Runnable {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
  private static Logger logger = Logger.getLogger(TestApp.class.getName());

  private EntityManagerFactory entityManagerFactory;

  private final ScheduledExecutorService executorService;

  private final String sessionId;

  public TestApp() {
    executorService = Executors.newSingleThreadScheduledExecutor();
    sessionId = "id:" + dateFormat.format(new Date());
  }

  public void osgiDestroy() {
    logger.info("App is stopping at " + dateFormat.format(new Date()));
    executorService.shutdown();
    try {
      executorService.awaitTermination(30, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      logger.warning("Service terminated after 30 seconds");
    }
    removeEntity();
    logger.info("App stopped at " + dateFormat.format(new Date()));
  }

  /**
   * Invoked when Osgi start this bundle.
   */
  public void osgiInit() {
    logger.info("App is starting at " + dateFormat.format(new Date()));
    createEntity();
    executorService.scheduleAtFixedRate(this, 5, 5, TimeUnit.SECONDS);
  }

  @Override
  public void run() {
    logger.info("Current time " + dateFormat.format(new Date()));
    updateEntity();
  }

  public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
    this.entityManagerFactory = entityManagerFactory;
    logger.info("Initialized EntityManagerFactory with a "
        + entityManagerFactory.getClass().getName());
  }

  private void createEntity() {
    TestEntity entity = new TestEntity(sessionId);
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    em.close();
  }

  private void removeEntity() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    TestEntity entity = em.find(TestEntity.class, sessionId);
    em.remove(entity);
    em.getTransaction().commit();
    em.close();
  }

  private void updateEntity() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    TestEntity entity = em.find(TestEntity.class, sessionId);
    if (entity.time != null) {
      entity.description = "Previously invoked at " + dateFormat.format(entity.time);
      entity.value = entity.description.getBytes();
    }
    entity.time = new Date();
    entity.calls += 1;

    em.getTransaction().commit();
    em.close();
  }
}
