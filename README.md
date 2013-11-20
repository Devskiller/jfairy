fairyland [![Build Status](https://travis-ci.org/Codearte/fairyland.png)](https://travis-ci.org/Codearte/fairyland)
==============

Java fake data generator

Building
---------

This project can be built using [gradle](http://www.gradle.org/) command:

    ./gradlew build

Installation
------------

Installation into [maven](http://maven.apache.org/) local repository

    ./gradlew publishToMavenLocal

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
                <groupId>eu.codearte</groupId>
                <artifactId>fairyland</artifactId>
                <version>0.0.4</version>
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
    testCompile 'eu.codearte:fairyland:0.0.4'

Sample
------

Look into [code sample](https://github.com/Codearte/fairyland/tree/master/src/test/groovy/snippets/)
