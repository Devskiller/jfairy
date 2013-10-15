fairyland [![Build Status](https://travis-ci.org/Codearte/fairyland.png)](https://travis-ci.org/Codearte/fairyland)
==============

Java fake data generator

Building
---------

This project can be built using [gradle](http://www.gradle.org/) command:

    ./gradlew build

Installation
------------

Installation into [maven](maven.apache.org) local repository

    ./gradlew publishToMavenLocal

Usage
-----

In maven projects:

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
                <version>0.0.1</version>
            </dependency>
        </dependencies>
        ...
    </pom>

Sample
------

Look into [code sample](snippets/)
