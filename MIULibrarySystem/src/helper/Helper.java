package helper;
import java.util.regex.Pattern;

public class Helper {
	private static final Pattern PHONE_PATTERN = Pattern.compile("^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$");
	private static final Pattern ISBN_PATTERN = Pattern.compile("[0-9]*[-| ][0-9]*[-| ][0-9]*[-| ][0-9]*[-| ][0-9]*");
	private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

//    public static boolean isPhoneNumber(String phoneNumber) {
//        return PHONE_PATTERN.matcher(phoneNumber).matches();
//    }
//    public static boolean isISBNNumber(String isbn) {
//    	return ISBN_PATTERN.matcher(isbn).matches();
//    }
//    public static boolean isNumber(String number) {
//    	return NUMBER_PATTERN.matcher(number).matches();
//    }
    
    public static boolean isPhoneNumber(String phoneNumber) {
        return true;
    }
    public static boolean isISBNNumber(String isbn) {
    	return true;
    }
    public static boolean isNumber(String number) {
    	return true;
    }
}
