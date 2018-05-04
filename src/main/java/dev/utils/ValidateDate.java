package dev.utils;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * @author Emmanuel
 *
 */
public class ValidateDate {

	private ValidateDate() {
		throw new IllegalStateException("Utility class");
	}

	public static final boolean validateInputDate(final String isoDate) {

		if (isoDate == null) {
			return false;
		}

		String[] dateProperties = isoDate.split("-");

		if (dateProperties != null) {
			int year = Integer.parseInt(dateProperties[0]);

			// A valid month by default in the case it is not provided.
			int month = dateProperties.length > 1 ? Integer.parseInt(dateProperties[1]) : 1;

			// A valid day by default in the case it is not provided.
			int day = dateProperties.length > 2 ? Integer.parseInt(dateProperties[2]) : 1;

			try {
				LocalDate.of(year, month, day);
				return true;
			} catch (DateTimeException e) {
				return false;
			}
		}

		return false;
	}
}
