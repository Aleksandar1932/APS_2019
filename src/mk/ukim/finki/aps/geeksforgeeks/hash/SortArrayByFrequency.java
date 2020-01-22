package mk.ukim.finki.aps.geeksforgeeks.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class SortArrayByFrequency {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int numberOfElements = Integer.parseInt(br.readLine());
            String array = br.readLine();
            sortArrayByFrequency(Arrays.stream(array.split("\\s+")).map(element -> Integer.parseInt(element)).collect(Collectors.toCollection(ArrayList::new)));
        }
    }

    public static ArrayList<Integer> sortArrayByFrequency(ArrayList<Integer> array) {
        Map<Integer, Integer> countingMap = new HashMap<>();

        array.forEach(integer -> {
            countingMap.putIfAbsent(integer, 0);
            countingMap.computeIfPresent(integer, (k, v) -> v + 1);
        });

        ArrayList<Integer> returnList = new ArrayList<>();

        countingMap.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(integerIntegerEntry -> {
                    int numberOfOccurrances = integerIntegerEntry.getValue();
                    int element = integerIntegerEntry.getKey();
                    for (int i = 0; i < numberOfOccurrances; i++) {
                        returnList.add(element);
                    }
                });

        Collections.reverse(returnList);
        return returnList;

    }
}
