package com.ssm.common.utils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;


public class MessageFormat {

	/**
	 * Formats arguments using MessageFormat.
	 * 
	 * @param pattern
	 *            pattern, may be malformed or null.
	 * @param arguments
	 *            arguments, may be null or mismatched.
	 * @return Message string or null
	 */
	public static String format(Locale locale, final String pattern,
			final Object[] arguments) {
		if (pattern == null) {
			return null;
		} else if (isSimple(pattern)) {
			String formatted[] = new String[10];
			int prev = 0;
			StringBuffer retval = new StringBuffer();
			int pos = pattern.indexOf('{');
			while (pos >= 0) {
				if (pos + 2 < pattern.length()
						&& pattern.charAt(pos + 2) == '}'
						&& pattern.charAt(pos + 1) >= '0'
						&& pattern.charAt(pos + 1) <= '9') {
					int index = pattern.charAt(pos + 1) - '0';
					retval.append(pattern.substring(prev, pos));
					if (formatted[index] == null) {
						if (arguments == null || index >= arguments.length) {
							formatted[index] = pattern.substring(pos, pos + 3);
						} else {
							formatted[index] = formatObject(locale,
									arguments[index]);
						}
					}
					retval.append(formatted[index]);
					prev = pos + 3;
					pos = pattern.indexOf('{', prev);
				} else {
					pos = pattern.indexOf('{', pos + 1);
				}
			}
			retval.append(pattern.substring(prev));
			return retval.toString();
		}
		System.err.println("FIXME, cannot format message, locale:" + locale
				+ ", pattern:" + pattern);
		return pattern;
	}

	/**
	 * Determines if pattern contains only {n} format elements and not
	 * apostrophes.
	 * 
	 * @param pattern
	 *            pattern, may not be null.
	 * @return true if pattern only contains {n} format elements.
	 */
	private static boolean isSimple(final String pattern) {
		// if (pattern.indexOf('\'') != -1) {
		// return false;
		// }
		for (int pos = pattern.indexOf('{'); pos != -1; pos = pattern.indexOf(
				'{', pos + 1)) {
			if (pos + 2 >= pattern.length() || pattern.charAt(pos + 2) != '}'
					|| pattern.charAt(pos + 1) < '0'
					|| pattern.charAt(pos + 1) > '9') {
				return false;
			}
		}
		return true;

	}

	/**
	 * Format a single parameter like a "{0}" formatting specifier.
	 * 
	 * @param arg0
	 *            parameter, may be null.
	 * @return string representation of arg0.
	 */
	private static String formatObject(Locale locale, final Object arg0) {
		if (arg0 instanceof String) {
			return arg0.toString();
		} else if (arg0 instanceof Double || arg0 instanceof Float) {
			return formatNumber(locale, arg0);
		} else if (arg0 instanceof Date) {
			return formatDate(locale, arg0);
		}
		return String.valueOf(arg0);
	}

	/**
	 * Format number.
	 * 
	 * @param n
	 *            number to format, may not be null.
	 * @return formatted value.
	 */
	private static synchronized String formatNumber(Locale locale,
			final Object n) {
		NumberFormat numberFormat = NumberFormat.getInstance(locale);
		return numberFormat.format(n);
	}

	/**
	 * Format date.
	 * 
	 * @param d
	 *            date, may not be null.
	 * @return formatted value.
	 */
	private static synchronized String formatDate(Locale locale, final Object d) {
		DateFormat dateFormat = DateFormat.getDateTimeInstance(
				DateFormat.SHORT, DateFormat.SHORT, locale);
		return dateFormat.format(d);
	}
}
