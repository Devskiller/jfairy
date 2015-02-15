package eu.codearte.jfairy;

import java.util.Locale;

/**
 * @author Jakub Kubrynski
 */
interface FairyFactory {

	Fairy createFairy(Locale locale, String filePrefix);
}
