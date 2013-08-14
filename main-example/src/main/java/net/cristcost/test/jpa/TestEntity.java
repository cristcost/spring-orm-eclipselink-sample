/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class EntryEntity.
 */
@Entity
@Table(name = "\"test_entities\"")
public class TestEntity {

  public TestEntity() {
  }

  public TestEntity(String id) {
    this.id = id;
    this.calls = 0;
  }

  @Id
  @Column(name = "\"id\"", unique = true, nullable = false)
  public String id;

  @Column(name = "\"description\"")
  public String description;

  @Column(name = "\"time\"")
  @Temporal(TemporalType.TIMESTAMP)
  public Date time;

  @Column(name = "\"calls\"")
  public int calls;

  @Lob
  @Column(name = "\"value\"", nullable = true)
  public byte[] value;
}
