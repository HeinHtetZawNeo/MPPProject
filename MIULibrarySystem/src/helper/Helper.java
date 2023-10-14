package helper;

import java.time.LocalDate;
import java.util.regex.Pattern;
import java.time.Period;

public class Helper {
	private static final Pattern PHONE_PATTERN = Pattern.compile("^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$");
	private static final Pattern ISBN_PATTERN = Pattern.compile(
			"^(?:ISBN(?:-1[03])?:?\\ )?(?=[0-9X]{10}$|(?=(?:[0-9]+[-\\ ]){3})[-\\ 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[-\\ ]){4})[-\\ 0-9]{17}$)(?:97[89][-\\ ]?)?[0-9]{1,5}[-\\ ]?[0-9]+[-\\ ]?[0-9]+[-\\ ]?[0-9X]$");
	private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

	public static boolean isPhoneNumber(String phoneNumber) {
		return PHONE_PATTERN.matcher(phoneNumber).matches();
	}

	public static boolean isISBNNumber(String isbn) {
		return ISBN_PATTERN.matcher(isbn).matches();
	}

	public static boolean isNumber(String number) {
		return NUMBER_PATTERN.matcher(number).matches();
	}

//    public static boolean isPhoneNumber(String phoneNumber) {
//        return true;
//    }
//    public static boolean isISBNNumber(String isbn) {
//    	return true;
//    }
//    public static boolean isNumber(String number) {
//    	return true;
//    }

	public static int dateDifferent(LocalDate d1, LocalDate d2) {
		return Period.between(d1, d2).getDays();
	}
}
