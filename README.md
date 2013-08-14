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

TODO

