package homework6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class WordsInAlphabeticalOrderTest {

    private WordsInAlphabeticalOrder words;

    private static final String SENTENCE = "7 Once up6on a t)ime a Wolf's was 5lapp%ing";
    private static final String[] WORDS_ARR = new String[]{"once", "upon", "a", "time", "a", "wolf's", "was", "lapping"};
    private static final Map<String, Long> MAP_FROM_ARR = new HashMap<>();

    {
        MAP_FROM_ARR.put("wolf's", 1L);
        MAP_FROM_ARR.put("a", 2L);
        MAP_FROM_ARR.put("once", 1L);
        MAP_FROM_ARR.put("lapping", 1L);
        MAP_FROM_ARR.put("was", 1L);
        MAP_FROM_ARR.put("time", 1L);
        MAP_FROM_ARR.put("upon", 1L);
    }

    private static final String SORTED_STRING_FROM_MAP = "A : a 2,L : lapping 1,O : once 1,T : time 1,U : upon 1," +
            "W : was 1,W : wolf's 1";

    private static final String[] ARR_WITHOUT_UNNECESSARY_COLON = new String[]
            {"A : a 2", "L : lapping 1", "O : once 1", "T : time 1", "U : upon 1", "W : was 1", "wolf's 1"};

    private static final String RESULT = "A : a 2\n" +
            "L : lapping 1\n" +
            "O : once 1\n" +
            "T : time 1\n" +
            "U : upon 1\n" +
            "W : was 1\n" +
            "    wolf's 1";

    @Before
    public void setUp() throws Exception {
        words = new WordsInAlphabeticalOrder();
    }

    @After
    public void tearDown() throws Exception {
        words = null;
    }

    @Test
    public void testGetWordsArrWithLettersAndApostrofFromString() {
        String s = SENTENCE;
        String[] expected = WORDS_ARR;

        String[] actual = words.getWordsArr(s);

        assertArrayEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWordsArrWithLettersAndApostrofFromStringNegative_IfLineWithoutLetters() {
        String s = "5";

        String[] actual = words.getWordsArr(s);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWordsArrWithLettersAndApostrofFromStringNegative_IfLineLengthIsZero() {
        String s = "";

        String[] actual = words.getWordsArr(s);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWordsArrWithLettersAndApostrofFromStringNegative_IfLineIsSpace() {
        String s = " ";

        String[] actual = words.getWordsArr(s);
    }

    @Test
    public void testGetMapFromWordsArrAndNumberOfOccurrences() {
        String[] s = WORDS_ARR;
        Map<String, Long> expected = MAP_FROM_ARR;

        Map<String, Long> actual = words.getMapFromWordsArrAndNumberOfOccurrences(s);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetMapAsSortedString() {
        Map<String, Long> mp = MAP_FROM_ARR;
        String expected = SORTED_STRING_FROM_MAP;

        String actual = words.getMapAsSortedString(mp);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetListWithoutBigLettersDuplicates() {
        String s = SORTED_STRING_FROM_MAP;
        List<String> expected = new ArrayList<>();
        expected.add("A : a 2");
        expected.add("L : lapping 1");
        expected.add("O : once 1");
        expected.add("T : time 1");
        expected.add("U : upon 1");
        expected.add("W : was 1");
        expected.add(" : wolf's 1");

        List<String> actual = words.getListWithoutBigLettersDuplicates(s);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetArrWithoutUnnecessaryColon() {
        List<String> list = new ArrayList<>();
        list.add("A : a 2");
        list.add("L : lapping 1");
        list.add("O : once 1");
        list.add("T : time 1");
        list.add("U : upon 1");
        list.add("W : was 1");
        list.add(" : wolf's 1");
        String[] expected = ARR_WITHOUT_UNNECESSARY_COLON;

        String[] actual = words.getArrWithoutColon(list);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testGetWordsInAlphabeticalOrder() {
        String[] arr = ARR_WITHOUT_UNNECESSARY_COLON;
        String expected = RESULT;

        String actual = words.getWordsInAlphabeticalOrder(arr);

        assertEquals(expected, actual);
    }
}