package homework6;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;

public class WordsInAlphabeticalOrder {

    private static final String LETTER_REGEX = "[a-zA-Zа-яА-Я]";
    private static final String REGEX_WITHOUT_BIG_LETTERS_DUPLICATES = "([A-ZА-Я])(?=.*\\1)";
    private static final String SQUARE_BRACKETS_REGEX = "\\[";
    private static final String COLON_AFTER_NUMBERS_REGEX = "(?<=\\d+)[^A-Za-zА-Яа-я0-9]+";

    private static boolean isLineNotContainLetter(String sentence) {
        return sentence.isEmpty() || (sentence.length() == 1 && !(sentence.contains(LETTER_REGEX)));
    }

    private static boolean isCharSuite(char c) {
        return Character.isLetter(c) || c == '\'' || c == ' ';
    }

    public String[] getWordsArr(String sentence) {
        if (isLineNotContainLetter(sentence)) {
            throw new IllegalArgumentException("String doesn't contain letter!");
        }
        char[] charsArrOfSentence = sentence.toCharArray();
        String letters = IntStream.range(0, sentence.length())
                .filter(i -> isCharSuite(charsArrOfSentence[i]))
                .mapToObj(i -> String.valueOf(charsArrOfSentence[i]))
                .collect(Collectors.joining());
        return Stream.of(letters.replaceAll(" +", " ").trim())
                .map(p -> p.toLowerCase())
                .flatMap(p -> Arrays.asList(p.split(" ")).stream())
                .toArray(String[]::new);
    }

    public Map<String, Long> getMapFromWordsArrAndNumberOfOccurrences(String[] words) {
        return Stream.of(words)
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));
    }

    public String getMapAsSortedString(Map<String, Long> mapFromString) {
        return mapFromString.keySet().stream()
                .map(key -> String.valueOf(key.charAt(0)).toUpperCase() + " : " + key + " " + mapFromString.get(key))
                .sorted()
                .collect(Collectors.joining(","));
    }

    public List<String> getListWithoutBigLettersDuplicates(String sortedString) {
        String withoutBigLettersDuplicates = new StringBuilder(sortedString)
                .reverse()
                .toString()
                .replaceAll(REGEX_WITHOUT_BIG_LETTERS_DUPLICATES, "");
        String[] withColon = new StringBuilder(withoutBigLettersDuplicates)
                .reverse()
                .toString()
                .split(",");
        return Stream.of(withColon)
                .map(String::new)
                .collect(Collectors.toList());
    }

    public String[] getArrWithoutColon(List<String> list) {
        return list.toString()
                .replaceAll(SQUARE_BRACKETS_REGEX, "")
                .split(COLON_AFTER_NUMBERS_REGEX);
    }

    public String getWordsInAlphabeticalOrder(String[] strWithoutColon) {
        return Stream.of(strWithoutColon)
                .map(p -> p.charAt(0) >= 'a' && p.charAt(0) <= 'z' ? "    " + p : p)
                .flatMap(p -> Arrays.asList(p.split(",")).stream())
                .collect(Collectors.joining("\n"));
    }
}

