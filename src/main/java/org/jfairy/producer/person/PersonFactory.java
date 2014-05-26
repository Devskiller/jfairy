package org.jfairy.producer.person;

/**
 * Created by Jakub Kubrynski / 2014-05-26
 */
public interface PersonFactory {

	Person producePerson(PersonProperties.PersonProperty... personProperties);

}
