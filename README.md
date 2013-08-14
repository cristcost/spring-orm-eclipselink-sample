spring-orm-eclipselink-sample
=============================

Demo of a JPA application using Spring Orm and Eclipselink under Service Mix, with MySQL or HSQLDB.


### How to run the example:

#### In command prompt:

clone the repo
```
git clone https://github.com/cristcost/spring-orm-eclipselink-sample.git
```

change database settings (see below)

compile and install with maven
```
cd spring-orm-eclipselink-sample/
mvn clean install
```

#### (Optional):

Nice commands to setup a clean servicemix for testing:

```
# get servicemix 4.5.2 from your repo
wget http://replace_with_your_apache_mirror/servicemix/servicemix-4/4.5.2/apache-servicemix-4.5.2.tar.gz

# to delete an old servicemix and retry on a clean one
rm -Rf apache-servicemix-4.5.2/
tar xzf dist/apache-servicemix-4.5.2.tar.gz
apache-servicemix-4.5.2/bin/servicemix
```

#### In Servicemix:

install the feature repository
```
features:addurl mvn:net.cristcost.test.jpa/features/1.0-SNAPSHOT/xml/features
```

install the feature
```
features:install osgijpa-test
```

### The demo application:
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

### Testing outside of servicemix:

In `src/test/java` of main-example projects there are two java console applications that execute the demo 
outside of the osgi environemnt.

`SpringLauncher.java` starts a Spring context that load the same beans.xml used when deploying 
to servicemix and uses Spring-ORM.

`LocalLauncher.java` instead launches the demo without Spring-ORM: the PersistenceManagerFactory 
is retrieved by a call to `Persistence.createEntityManagerFactory("test-eclipselik-hsqldb");`

I've used these two classes to inspect the behaviour of Eclipselink with Spring-ORM 
in J2SE and without complexity of beeing in OSGi.

Please note that `LocalLauncher.java` uses a different persistence unit which configures the proper access 
to the data source for testing in J2SE environemnt. The persistence.xml defines 2 more persistence units, 
one for HSQLDB and one for MySQL in J2SE. Fix their parameters to configure it for your database.


## Open points:

The two following points are still unclear for me: I welcome your feedback on servicemix's users mailing list (users@servicemix.apache.org
). 

### Eclipselink Weaving
The best way to use the weaver at load time need to be investigated.

One alternative approach is to disable dinamic weaving and have weaving of entities at build time
(see http://bit.ly/1bqX6cj and http://bit.ly/RiNYyG) or disable weaving at all.

### MySQL Driver in Spring-JDBC
Spring-ORM is based on Spring-JDBC that in osgi is able to find most famous database drivers as the bundle 
is preconfigured with optional imports. 

However Spring-JDBC does not does this for MySQL (see 
http://planet.jboss.org/post/how_to_use_jdbc_driver_in_osgi_container), at least in version 3.0.7-RELEASE 
which is currently used in servicemix 4.5.2.

To fix it, this demo include a project of a specific framgment bundle (project mysql-fragment) 
that extends the spring-jdbc bundle to import the MySQL driver. 


