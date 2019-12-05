package mk.ukim.finki.aps.lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Preveduvac {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        HashMap<String,String> dictionary = new HashMap<>();
        for(int i = 0 ; i < N ; i ++){
            String line = br.readLine();
            String[] tokens = line.split("\\s+");

            if(tokens.length==2){
                dictionary.put(tokens[1],tokens[0]);
            }
            else{
                System.out.println("Greska");
            }
        }

       while (true){
           String key = br.readLine();
           if(key.equals("KRAJ")){
               break;
           }

           if(dictionary.containsKey(key)){
               String translatedWord = dictionary.get(key);
               if(translatedWord.equals("")){
                   System.out.println("/");
               }
               else{
                   System.out.println(translatedWord);
               }
           }
           else{
               System.out.println("/");
           }
       }
    }
}
