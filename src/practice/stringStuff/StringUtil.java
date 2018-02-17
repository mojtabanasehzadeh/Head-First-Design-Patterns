package practice.stringStuff;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Moji on 21-Oct-17.
 * mojtaba.nasehzadeh@gmail.com
 */
public class StringUtil {

    public static void main(String[] args) {
        //System.out.println(validShuffle("moji", "naseh", "nasm      ojeih"));//abcdefig
        //System.out.println(removeChar("mojtaba", 'a'));
        System.out.println(longestPalindrome("mojtabalolaBythewayawepppiiiuuqqa"));
    }

    @Test
    public void testIt() {
        Assert.assertEquals(findCharOccurrence("Mojiii", 'm'), 1);
    }

    /********************************************************************************/

    public static Map<String, Long> findStringCharsFrequencyJava8(String s) {
        Stream<String> stream = Stream.of(s.toLowerCase().split("")).parallel();
        Map<String, Long> wordFreq = stream.collect(Collectors.groupingBy(String::toString, Collectors.counting()));
        return wordFreq;
    }

    /**
     * Finds occurrence of given char in given string
     *
     * @param s
     * @param c
     * @return
     */
    public static int findCharOccurrence(String s, Character c) {
        if (s == null || s.length() == 0) return 0;
        String inputStr = s.toLowerCase();
        Character inputChar = Character.toLowerCase(c);
        List<Character> listOfChars = inputStr.chars().mapToObj(ch -> (char) ch).collect(Collectors.toList());
        Map<Character, Integer> map = new HashMap<>();
        for (Character element : listOfChars) {
            map.put(element, Collections.frequency(listOfChars, Character.toLowerCase(inputChar)));
        }
        return map.get(inputChar).intValue();
    }

    /**
     * Finds 1st non repated Character of given String
     * implemented by LinkedHashMap
     *
     * @param s
     * @return
     */
    public static Character findFistNonRepeartedChar(String s) {
        //find 1st not repeated char
        Map<Character, Integer> map = new LinkedHashMap<>();
        int counter = 0;
        Character result = null;
        for (int i = 0; i < s.length(); i++) {
            //put char in map
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), 1);
            } else {
                // but if the char is already in the map , add ro counter
                counter = map.get(s.charAt(i)).intValue() + 1;
                map.put(s.charAt(i), counter);
            }
        }
        System.out.println(map);
        for (Map.Entry<Character, Integer> c : map.entrySet()) {
            if (c.getValue() == 1)
                result = c.getKey();
            break;
        }
        return result;
    }

    /**
     * @return
     */
    public static Map<Character, Integer> findDuplicates(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        int count;
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), 1);
            } else {
                count = map.get(s.charAt(i)).intValue() + 1;
                map.put(s.charAt(i), count);
            }
        }

        return map.entrySet().stream()
                .filter(a -> a.getValue().intValue() > 1)
                //.map(a -> a.getKey()) //if you want
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    public static Character findMostRepeatedDuplicate(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        int count;
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), 1);
            } else {
                count = map.get(s.charAt(i)).intValue() + 1;
                map.put(s.charAt(i), count);
            }
        }

        return map
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).get().getKey();
    }

    /**
     * Remove duplicates keeping only the first occurrences
     *
     * @param s
     * @return
     */
    public static String removeDuplicatesAndReturn(String s) {
        //validations/errors
        Set<Character> set = new LinkedHashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.toLowerCase().charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        set.forEach(sb::append);
        return sb.toString();
        //return set.stream().map(Object::toString).collect(Collectors.joining(""));

    }

    /**
     * Using Collections.frequency()
     */
    public static Character findFistNonRepeartedCharByCollectionsApi(String s) {
        List<Character> charList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            charList.add(s.charAt(i));
        }
        System.out.println(charList);
        for (int i = 0; i < charList.size(); i++) {
            if (Collections.frequency(charList, charList.get(i)) == 1) {
                return charList.get(i);
            }
        }
        return null;
    }


    /**
     * String IndexOF
     *
     * @param s
     * @param c
     * @return
     */
    public static int findIndexOfFirstSpace(String s, Character c) {
        return s.indexOf(new Character(' '));
    }

    /**
     * String Replace All method usage
     *
     * @param s
     * @param c
     * @return
     */
    public static String removeChar(String s, Character c) {
        return s.replaceAll(Character.toString(c), "");
    }

    /**
     * same when reversed
     *
     * @param str
     * @return
     */
    private static boolean isPalindrome(String str) {
        if (str == null)
            return false;
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString().equals(str);
    }

    /**
     * Anagram Army  & Mary
     * The key is :
     * 1) Arrays.sort()
     * 2) Arrays.equals()
     *
     * @param s1
     * @param s2
     */
    public static boolean isAnagram(String s1, String s2) {
        char[] c1 = s1.toLowerCase().toCharArray();
        char[] c2 = s2.toLowerCase().toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }

    /**
     * Reverse string iteratively
     *
     * @param s
     * @return
     */
    public static String reversedIterative(String s) {
        if (s == null) return null;
        if (s.length() == 1) return s;
        int length = s.length();
        char[] reversed = new char[length];
        for (int i = 0; i <= (length / 2); i++) {
            reversed[i] = s.charAt(length - i - 1);
            reversed[length - i - 1] = s.charAt(i);
        }
        return new String(reversed);
    }


    /**
     * Reverse string recursively
     *
     * @param s
     * @return
     */
    public static String reverseRecursive(String s) {
        if (s == null) return null;
        if (s.length() == 1) return s;
        String result =
                s.charAt(s.length() - 1) //take last char
                        + reverseRecursive(s.substring(0, s.length() - 1)); // run again while u have
        // removed last char
        return result;
    }

    /**
     * @param s
     * @return
     */
    public static boolean containsOnlyDigits(String s) {
        if (s == null) return false;
        return s.matches("^[0-9]*$");
    }

    /**
     * horoofe seda dar
     * point:  vowels.indexOf(s.charAt(i)) == -1
     *
     * @param s
     * @return
     */
    public static int[] vowelsAndConsonants(String s) {
        if (s == null || s.length() == 0) return new int[]{0, 0};
        int v = 0;
        int c = 0;
        String vowels = "aeiouAEIOU";
        String consonants = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";

        for (int i = 0; i < s.length(); i++) {
            if (vowels.indexOf(s.charAt(i)) == -1) {
                c++;
            } else {
                v++;
            }
        }
        return new int[]{c, v};
    }

    /**
     * @param str
     * @return
     */
    public static Set<String> permutationFinder(String str) {
        Set<String> permutation = new HashSet();
        if (str == null) {
            return null;
        } else if (str.length() == 0) {
            permutation.add("");
            return permutation;
        }
        char firstChar = str.charAt(0); // first character
        String remChars = str.substring(1); // Full string without first character
        Set<String> words = permutationFinder(remChars);
        for (String newStr : words) {
            for (int i = 0; i <= newStr.length(); i++) {
                permutation.add(charInsert(newStr, firstChar, i));
            }
        }
        return permutation;
    }

    // utility method for permutation
    public static String charInsert(String str, char c, int j) {
        String begin = str.substring(0, j);
        String end = str.substring(j);
        return begin + c + end;
    }


    /**
     * Replace character in String
     *
     * @param s
     * @param r
     * @return
     */
    public static String replaceCharacter(String s, String r) {
        return s.replace(" ", "%20");
    }

    /**
     * String to Integer
     *
     * @param s
     * @return
     */
    public static int StringToInt(String s) {
        return Integer.valueOf(s);
    }

    /**
     * @param s1
     * @param s2
     * @param shuffle
     * @return
     */
    public static boolean validShuffle(String s1, String s2, String shuffle) {
        List<Character> listS1 = new LinkedList<>();
        List<Character> listS2 = new LinkedList<>();
        for (int i = 0; i < shuffle.length(); i++) {
            if (s1.indexOf(shuffle.charAt(i)) > -1) {
                listS1.add(shuffle.charAt(i));
            }
        }
        for (int i = 0; i < shuffle.length(); i++) {
            if (s2.indexOf(shuffle.charAt(i)) > -1) {
                listS2.add(shuffle.charAt(i));
            }
        }
        if (listS1.toString().equals(s1) && listS2.toArray().equals(s2)) {
            return true;
        }
//
//        if (listS1.stream().map(Object::toString).collect(Collectors.joining("")).equals(s1)
//                &&
//                listS2.stream().map(Object::toString).collect(Collectors.joining("")).equals(s2)) {
//            return true;
//        }
        return false;
    }

    /**
     * Remove char from String recursively
     *
     * @param s
     * @param c
     * @return
     */
    public static String removeCharRecursive(String s, char c) {
        int index = s.indexOf(c);
        if (index == -1) {
            return s;
        }
        return removeCharRecursive(s.substring(0, index) + s.substring(index + 1, s.length()), c);
    }

    /**
     * @param s
     * @param c
     * @return
     */
    public static String removeChar(String s, char c) {
        char[] charArr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (charArr[i] != c) {
                sb.append(charArr[i]);
            }
        }
        return sb.toString();
    }

    /**
     * smallet and largest char value of string with given length
     *
     * @param s
     * @param k
     * @return
     */
    static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";
        SortedSet<String> sets = new TreeSet<>();

        for (int i = 0; i <= s.length() - k; i++) {
            sets.add(s.substring(i, i + k));
        }
        smallest = sets.first();
        largest = sets.last();

        return smallest + "\n" + largest;
    }

    /**
     * Finds longest palindrome in String
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        List<String> list = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j <= s.length() - i; j++) {
                list.add(s.substring(j, j + i));
            }
        }
        list.sort(Comparator.comparing(String::length).reversed());
        System.out.println(list);
        for (String str : list) {
            StringBuilder sb = new StringBuilder(str);
            if (sb.reverse().toString().equals(str)) {
                resultList.add(str);
            }
        }
        resultList.sort(Comparator.comparing(String::length).reversed());
        return resultList.get(0);
    }

    /**
     * @param sArr
     * @return
     */
    public static String[] sortArray(String[] sArr) {
        Arrays.sort(sArr);
        return sArr;
        //Collections.sort(Arrays.asList(sArr));
    }

    /**
     * @param inputFilePath
     * @param outputFilePath
     * @throws IOException
     */
    private static void linePhraseCounterInFile(String inputFilePath, String outputFilePath) throws IOException {
        // read the string filename
        int count;
        List<String> lines = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();

        // read file into stream
        try (Stream<String> stream = Files.lines(Paths.get(inputFilePath))) {
            lines = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (final String line : lines) {
            final String[] splitValue = line.split(" -");

            if (!map.containsKey(splitValue[0])) {
                map.put(splitValue[0], 1);
            } else {
                count = map.get(splitValue[0]).intValue() + 1;
                map.put(splitValue[0], count);
            }

            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(outputFilePath)), StandardCharsets.UTF_8));
            map.forEach((key, value) -> {
                try {
                    writer.write(key + " " + value + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.flush();
            writer.close();
        }
    }

}


