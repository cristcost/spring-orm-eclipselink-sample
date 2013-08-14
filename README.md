spring-orm-eclipselink-sample
=============================

Demo of a JPA application using Spring Orm and Eclipselink under Service Mix, with MySQL or HSQLDB.


### How to run the example:

#### In command prompt:

clone the repo
```git clone https://github.com/cristcost/spring-orm-eclipselink-sample.git```

change database settings (see below)

compile and install with maven
```cd spring-orm-eclipselink-sample/
mvn clean install```

#### In Servicemix:

install the feature repository
```features:addurl mvn:net.cristcost.test.jpa/features/1.0-SNAPSHOT/xml/features```

install the feature
```features:install osgijpa-test```


The application insert a new row into the DB and starts updating it every 5 seconds, 
and remove the row when the applicaiton stops. The data into the DB looks like the following:

| id | calls | description | time | value |
| --- | --- | --- | --- | --- |
| id:09:05:28.350 | 158 | Previously invoked at 09:18:33.894 | 2013-08-14 09:18:38 | [BLOB - 34B] |

### How to change database settings:

The demo is preconfigured with a HSQLDB and a MySQL data source. The data sources are not 
configured in the persistence.xml file, but instead are configured by spring. 

To change the settings, open `main-example/src/main/resources/META-INF/spring/beans.xml` and 
configure the `hsqlDataSource` or the `mysqlDataSource` bean with parameters to access your database.

The persistence is then configured by the EntityManagerFactory in the `emf` bean: the class used is part of Spring-ORM.

Set the HSQLDB or MySQL in the `dataSource` property.

Leave the value of `persistenceUnitName` as it is (this refer to a persistence 
unit to be used with the Spring-ORM that only lists the Entity classes, 
see it in `main-example/src/main/resources/META-INF/presistence.xml`).

Check the `loadTimeWeaver` property: this is set to use a custom LoadTimeWeaver, `net.cristcost.test.jpa.TestWeaver`, 
available in the bundle and that return the class loader of the the bundle.
Note: this weaver does ignore weaving and is the the main point to be improved in this approach of using JPA.


## Open points:
The best way to use the weaver need to be investigated.

One approach is to disable dinamic weaving and have weaving of entities at build time
(see http://bit.ly/1bqX6cj and http://bit.ly/RiNYyG).


Spring-ORM is based on Spring-JDBC that in osgi is able to find most famous database drivers as the bundle 
is preconfigured with optional imports. 

Howver Spring-JDBC does not does this for MySQL (see http://planet.jboss.org/post/how_to_use_jdbc_driver_in_osgi_container)




