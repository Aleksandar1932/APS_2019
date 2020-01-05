package mk.ukim.finki.aps.vezbanjekol2;

import java.io.*;
import java.util.*;

public class MostFrequentSubstring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();

        ArrayList<String> subStringsList = generateAllSubStrings(inputString); //Lista so site podstringovi generirani
        HashMap<String, Integer> countedSubStrings = countSubStrings(subStringsList); //Counting mapa so br na pojavuvanje

        System.out.println(findMostFrequentSubString(countedSubStrings));
    }

    public static String findMostFrequentSubString(HashMap<String, Integer> countingMap) {
        Integer maxFrequency = countingMap.values().stream().max(Integer::compareTo).orElse(0);

        return countingMap.entrySet().stream()
                .filter(es -> es.getValue().equals(maxFrequency))
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparing(String::length).reversed())
                .limit(1)
                .findFirst()
                .orElse("");
    }

    public static HashMap<String, Integer> countSubStrings(ArrayList<String> subStringsList) {
        HashMap<String, Integer> countingMap = new HashMap<>();

        subStringsList.forEach(s -> {
            if (countingMap.containsKey(s)) {
                int currentNumber = countingMap.get(s);
                countingMap.put(s, currentNumber + 1);
            } else {
                countingMap.put(s, 1);
            }
        });
        return countingMap;
    }

    public static ArrayList<String> generateAllSubStrings(String string) {
        ArrayList<String> substringsList = new ArrayList<>();
        int length = string.length();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                substringsList.add(string.substring(i, j));
            }
        }
        return substringsList;
    }
}
