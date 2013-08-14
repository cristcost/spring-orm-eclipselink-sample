spring-orm-eclipselink-sample
=============================

Demo of a JPA application using Spring Orm and Eclipselink under Service Mix, with MySQL or HSQLDB.


How to run the example:

# clone the repo
git clone https://github.com/cristcost/spring-orm-eclipselink-sample.git

# compile and install with maven
cd spring-orm-eclipselink-sample/
mvn clean install

# install the feature (in servicemix shell)
features:addurl mvn:net.cristcost.test.jpa/features/1.0-SNAPSHOT/xml/features

#TODO: work in progress
