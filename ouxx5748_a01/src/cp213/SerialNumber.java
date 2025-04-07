package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Zi Feng (Alex) Ou, 169025748
 * @version 2025-02-01
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {

	// your code here

	boolean check = true;

	if (str == null || str.isEmpty()) {
	    check = false;
	} else {
	    for (int i = 0; i < str.length(); i++) {
		if (!Character.isDigit(str.charAt(i))) {
		    check = false;
		}
	    }
	}

	return check;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {

	// your code here

	boolean valid = true;

	if (sn == null || sn.length() != 11) {
	    valid = false;
	} else if (sn.charAt(0) != 'S' || sn.charAt(1) != 'N' || sn.charAt(2) != '/') {
	    valid = false;
	} else {
	    for (int i = 3; i < 7; i++) {
		if (!Character.isDigit(sn.charAt(i))) {
		    valid = false;
		}
	    }
	    if (sn.charAt(7) != '-') {
		valid = false;
	    }
	    for (int i = 8; i < 11; i++) {
		if (!Character.isDigit(sn.charAt(i))) {
		    valid = false;
		}
	    }
	}

	return valid;

    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns. Each line of fileIn contains
     * a (possibly valid) serial number.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {

	// your code here

	while (fileIn.hasNextLine()) {
	    String sn = fileIn.nextLine().trim();

	    if (validSn(sn)) {
		goodSns.println(sn);
	    } else {
		badSns.println(sn);
	    }
	}

	return;

    }

}
