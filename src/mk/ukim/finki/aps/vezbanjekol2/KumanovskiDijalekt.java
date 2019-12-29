package mk.ukim.finki.aps.vezbanjekol2;

import java.io.*;
import java.util.*;

public class KumanovskiDijalekt {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Reading the Dictionary and the untranslated text form keyboard;
        Map<String, String> dictionary = readDictionary(br);
        String untranslatedText = br.readLine();

        //Translating the text;
        ArrayList<String> translatedText = translateText(untranslatedText, dictionary);

        //Printing the result;
        printTranslatedText(translatedText);

    }

    private static Map<String, String> readDictionary(BufferedReader br) throws IOException {
        Map<String, String> dictionary = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            String[] tokens = line.split("\\s+");

            dictionary.put(tokens[0], tokens[1]);
        }
        return dictionary;
    }

    private static ArrayList<String> translateText(String untranslatedText, Map<String, String> dictionary) {
        ArrayList<String> translatedText = new ArrayList<>();
        String[] words = untranslatedText.split("\\s+");

        Arrays.stream(words).forEach(word -> {
            String key = removeInterpunction(word).toLowerCase();
            if (dictionary.containsKey(key)) {
                if (hasInterpunction(word)) {
                    if (!firstLetterCapital(word)) {
                        translatedText.add(dictionary.get(key) + getInterpunctionSign(word));
                    } else {
                        String wordToCapitalize = dictionary.get(key);
                        translatedText.add(capitalizeFirstLetter(wordToCapitalize) + getInterpunctionSign(word));
                    }
                } else {
                    if (!firstLetterCapital(word)) {
                        translatedText.add(dictionary.get(key));
                    } else {
                        String wordToCapitalize = dictionary.get(key);
                        translatedText.add(capitalizeFirstLetter(wordToCapitalize));
                    }
                }
            } else {
                translatedText.add(word);
            }
        });

        return translatedText;

    }

    private static String removeInterpunction(String s) {
        if (s.charAt(s.length() - 1) == '.' || s.charAt(s.length() - 1) == ',') {
            return s.substring(0, s.length() - 1);
        } else {
            return s;
        }
    }

    private static String capitalizeFirstLetter(String s) {
        return Character.toUpperCase(s.charAt(0)) +
                s.substring(1);
    }

    private static boolean firstLetterCapital(String s) {
        return s.charAt(0) >= 'A' && s.charAt(0) <= 'Z';
    }

    private static boolean hasInterpunction(String s) {
        return (s.charAt(s.length() - 1) == '.' || s.charAt(s.length() - 1) == ',');
    }

    private static String getInterpunctionSign(String s) {
        return String.valueOf(s.charAt(s.length() - 1));
    }

    private static void printTranslatedText(ArrayList<String> text) {
        text.stream()
                .limit(text.size() - 1)
                .forEach(word -> {
                    System.out.print(word + " ");
                });
        System.out.println(text.get(text.size() - 1));
    }
}
