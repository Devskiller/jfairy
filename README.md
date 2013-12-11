jFairy [![Build Status](https://travis-ci.org/Codearte/jfairy.png)](https://travis-ci.org/Codearte/jfairy)
==============

Java fake data generator

Building
---------

This project can be built using [gradle](http://www.gradle.org/) command:

    ./gradlew build

Installation
------------

Installation into [maven](http://maven.apache.org/) local repository

    ./gradlew install

Usage
-----

In Maven projects (pom.xml):

    <pom>
        ...
        <repositories>
            <repository>
                <url>http://dl.bintray.com/codearte/public</url>
            </repository>
        </repositories>
        ...
        <dependencies>
            <dependency>
                <groupId>org.jfairy</groupId>
                <artifactId>jfairy</artifactId>
                <version>0.1</version>
            </dependency>
        </dependencies>
        ...
    </pom>

In Gradle projects (build.gradle):

    repositories {
        mavenCentral()
        maven {
            url 'http://dl.bintray.com/codearte/public'
        }
    }
    ...
    testCompile 'org.jfairy:jfairy:0.1'

Sample
------

Look into [code sample](https://github.com/Codearte/jfairy/tree/master/src/test/groovy/snippets/)
