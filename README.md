# jFairy by Codearte

[![Build Status](https://travis-ci.org/Devskiller/jfairy.svg?branch=master)](https://travis-ci.org/Devskiller/jfairy) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.codearte.jfairy/jfairy/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.codearte.jfairy/jfairy) [![Javadocs](http://www.javadoc.io/badge/io.codearte.jfairy/jfairy.svg)](http://www.javadoc.io/doc/io.codearte.jfairy/jfairy) [![Coverage Status](https://img.shields.io/coveralls/Codearte/jfairy.svg)](https://coveralls.io/r/Codearte/jfairy)
[![Stack Overflow](https://img.shields.io/badge/stack%20overflow-jfairy-4183C4.svg)](https://stackoverflow.com/questions/tagged/jfairy)
[![Join the chat at https://gitter.im/Codearte/jfairy](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/Codearte/jfairy?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Java fake data generator. Based on Wikipedia:

> Fairyland, in folklore, is the fabulous land or abode of fairies or fays.

## Usage

Creating simple objects:

```java
Fairy fairy = Fairy.create();
Person person = fairy.person();

System.out.println(person.fullName());            
// Chloe Barker
System.out.println(person.email());               
// barker@yahoo.com
System.out.println(person.telephoneNumber());     
// 690-950-802

Person adultMale = fairy.person(male(), minAge(21));
System.out.println(adultMale.isMale());           
// true
System.out.println(adultMale.dateOfBirth());      
// at least 21 years earlier
```

Creating related objects:

```java
Fairy fairy = Fairy.create();
Company company = fairy.company();
System.out.println(company.name());          
// Robuten Associates
System.out.println(company.url());           
// http://www.robuteniaassociates.com

Person salesman = fairy.person(withCompany(company));
System.out.println(salesman.fullName());     
// Juan Camacho
System.out.println(salesman.companyEmail()); 
// juan.camacho@robuteniaassociates.com
```

Locale support:

```java
Fairy enFairy = Fairy.create();                               
// Locale.ENGLISH is default
Fairy plFairy = Fairy.create(Locale.forLanguageTag("pl"));    
// Polish version
```

## Other samples

Look into [code samples](https://github.com/Codearte/fairyland/tree/master/src/test/groovy/snippets/)

## Building

This project can be built using maven command:

    ./mvnw install

