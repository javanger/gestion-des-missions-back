package dev.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {

	/**
	 * @param dateTime
	 * @return la date au format jour/mois/année
	 */
	public static String shortDateTimeFormat(LocalDateTime dateTime) {
		return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
	}

	/**
	 * @param dateTime
	 * @return l'heure au format heures:minutes:secondes
	 */
	public static String timeDateTimeFormat(LocalDateTime dateTime) {
		return dateTime.format(DateTimeFormatter.ofPattern("h:m:s"));
	}
	
	/**
	 * @param dateTime
	 * @return la date au format jour/mois/année
	 */
	public static String shortDateFormat(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
	}

}
