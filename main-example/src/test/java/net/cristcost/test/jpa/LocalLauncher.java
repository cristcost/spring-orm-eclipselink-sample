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

import net.cristcost.test.jpa.TestApp;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class LocalLauncher {

  /**
   * @param args
   * @throws InterruptedException
   * @throws IOException
   */
  public static void main(String[] args) throws InterruptedException, IOException {

    TestApp app = new TestApp();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-eclipselik-mysql");
    app.setEntityManagerFactory(emf);

    app.osgiInit();

    System.out.println("Press ENTER to exit");
    System.in.read();

    System.out.println("exit");

    app.osgiDestroy();

    emf.close();
  }

}
