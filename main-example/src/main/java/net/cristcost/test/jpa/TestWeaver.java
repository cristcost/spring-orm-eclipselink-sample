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

import org.springframework.instrument.classloading.LoadTimeWeaver;

import java.lang.instrument.ClassFileTransformer;
import java.util.logging.Logger;

/**
 * The Class SimpleWeaver.
 */
public class TestWeaver implements LoadTimeWeaver {

  private static Logger logger = Logger.getLogger(TestWeaver.class.getName());

  @Override
  public void addTransformer(ClassFileTransformer transformer) {
    logger.info("Requested add of transformer: " + transformer.getClass().getName());
  }

  @Override
  public ClassLoader getInstrumentableClassLoader() {

    logger.info("Returning Instrumented Class Loader: "
        + this.getClass().getClassLoader().getClass().getName());
    return this.getClass().getClassLoader();
  }

  @Override
  public ClassLoader getThrowawayClassLoader() {
    logger.info("Returning Throwaway Class Loader: "
        + this.getClass().getClassLoader().getClass().getName());
    return this.getClass().getClassLoader();
  }

}
