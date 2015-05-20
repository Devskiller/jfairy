package io.codearte.jfairy.dataProvider.person;

/**
 * Created by Jakub Kubrynski / 2014-05-26
 */
public interface PersonFactory {

	PersonProvider producePersonProvider(PersonProperties.PersonProperty... personProperties);

}
