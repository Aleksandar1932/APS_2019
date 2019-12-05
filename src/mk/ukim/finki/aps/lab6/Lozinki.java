package mk.ukim.finki.aps.lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class Lozinki {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Hashtable<String, String> credentials = new Hashtable<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            String[] tokens = line.split("\\s+");

            credentials.put(tokens[0], tokens[1]);
        }


        while (true) {
            String loginAttempt = br.readLine();
            if (loginAttempt.equals("KRAJ")) {
                br.close();
                break;
            }

            String[] tokens = loginAttempt.split("\\s+");

                if (tokens[1].equals(credentials.get(tokens[0]))) {
                    System.out.println("Najaven");
                    break;
                } else {
                    System.out.println("Nenajaven");
                }
        }

    }
}
