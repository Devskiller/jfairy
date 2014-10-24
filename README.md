jFairy [![Build Status](https://travis-ci.org/Codearte/jfairy.png)](https://travis-ci.org/Codearte/jfairy) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.jfairy/jfairy/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.jfairy/jfairy)
==============

Java fake data generator

Usage
-----

In Maven projects (pom.xml):

```xml
<pom>
    ...
    <dependencies>
        <dependency>
            <groupId>org.jfairy</groupId>
            <artifactId>jfairy</artifactId>
            <version>0.2.4</version>
        </dependency>
    </dependencies>
    ...
</pom>
```

In Gradle projects (build.gradle):

```gradle
repositories {
   mavenCentral()
}
...
testCompile 'org.jfairy:jfairy:0.2.4'
```
    
Sample
------

Look into [code sample](https://github.com/Codearte/jfairy/tree/master/src/test/groovy/snippets/)

Building
---------

This project can be built using [gradle](http://www.gradle.org/) command:

    ./gradlew build

Installation
------------

Installation into [maven](http://maven.apache.org/) local repository

    ./gradlew install
