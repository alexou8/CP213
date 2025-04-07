package cp213;

/**
 * @author Zi Feng (Alex) Ou, 169025748
 * @version 2025-02-01
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {

	// your code here

	StringBuilder checker = new StringBuilder();

	for (int i = 0; i < string.length(); i++) {
	    char currentChar = string.charAt(i);
	    if (Character.isLetter(currentChar)) {
		checker.append(Character.toLowerCase(currentChar));
	    }
	}

	return checker.toString().equals(checker.reverse().toString());
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

	// your code here

	boolean valid = true;

	if (name == null || name.isEmpty()) {
	    valid = false;
	} else if (!Character.isLetter(name.charAt(0)) && name.charAt(0) != '_') {
	    valid = false;
	} else if (name.equals("_")) {
	    valid = false;
	} else {
	    for (int i = 1; i < name.length(); i++) {
		char ch = name.charAt(i);
		if (!Character.isLetterOrDigit(ch) && ch != '_') {
		    valid = false;
		}
	    }
	}

	return valid;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {

	// your code here

	if (word == null || word.isEmpty()) {
	    word = "";
	} else {
	    int index = 0;
	    String orgWord = word;
	    if (VOWELS.indexOf(word.charAt(0)) != -1) {
		word += "way";
	    } else {
		while (index < word.length() && VOWELS.indexOf(word.charAt(index)) == -1) {
		    index++;
		}
		String prefix = word.substring(0, index);
		String leftOut = word.substring(index);
		word = leftOut + prefix + "ay";
	    }
	    if (Character.isUpperCase(orgWord.charAt(0))) {
		word = Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
	    }
	}

	return word;
    }

}
