package mk.ukim.finki.aps.vezbanjekol2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Speluvanje {
    public static void main(String[] args) throws IOException {
        Set<String> dictionary = new HashSet<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            dictionary.add(br.readLine().toLowerCase());
        }

        AtomicInteger counter = new AtomicInteger();
        counter.set(0);
        String[] textTokens = br.readLine().split("\\s+");
        Arrays.stream(textTokens)
                .map(Speluvanje::removePunctuation)
                .map(String::toLowerCase)
                .filter(word-> !word.equals(""))
                .forEach(word->{
                    if(!dictionary.contains(word)){
                        System.out.println(word);
                        counter.set(counter.get()+1);
                    }
                });

        if(counter.get()==0){
            System.out.println("Bravo");
        }

    }

    private static String removePunctuation(String word) {
        if (word.charAt(word.length() - 1) == '.'
                || word.charAt(word.length() - 1) == ','
                || word.charAt(word.length() - 1) == '!'
                || word.charAt(word.length() - 1) == '?') {
            return word.substring(0, word.length() - 1);
        } else {
            return word;
        }
    }
}
