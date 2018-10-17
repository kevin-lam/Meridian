package kevinlamcs.android.com.meridian.util;

public class TextUtil {

    public static final String capitalize(String phrase) {
        String[] words = phrase.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(TextUtil.toUpperCaseAt(0, word));
            sb.append(" ");
        }
        return sb.toString();
    }

    public static final String toUpperCaseAt(int index, String word) {
        if (index < word.length()) {
            char[] wordAsArray = word.toCharArray();
            wordAsArray[index] = Character.toUpperCase(wordAsArray[index]);
            return new String(wordAsArray);
        }
        return word;
    }

    public static final String toLowerCaseAt(int index, String word) {
        if (index < word.length()) {
            char[] wordAsArray = word.toCharArray();
            wordAsArray[index] = Character.toLowerCase(wordAsArray[index]);
            return new String(wordAsArray);
        }
        return word;
    }

    public static final String removePunctuation(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
