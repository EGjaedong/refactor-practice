package com.twu.refactoring;

public class FormatUtil {
    public static int dateNumberFormat(String inputDate, int startIndex, int endIndex, int minNumber ,int maxNumber, String dateName, int stringLength) {
        int date;
        try {
            String minuteString = inputDate.substring(startIndex, endIndex);
            date = Integer.parseInt(minuteString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(dateName + " string is less than " + stringLength + " characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(dateName + " is not an integer");
        }
        if (date < minNumber || date > maxNumber)
            throw new IllegalArgumentException(dateName + " cannot be less than "+ minNumber +" or more than " + maxNumber);
        return date;
    }
}
