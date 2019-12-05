package mk.ukim.finki.aps.lab6;

import java.io.*;
import java.util.HashMap;

class InvalidDrugInputException extends Exception{
    public InvalidDrugInputException() {
        super();
    }
}

class Key {
    String key;

    public Key(String key) {
        this.key = key.toUpperCase();
    }

    public String getKey() {
        return key;
    }

    @Override
    public int hashCode() {
        int c1 = this.key.charAt(0);
        int c2 = this.key.charAt(1);
        int c3 = this.key.charAt(2);

        return ((29 * (29 * (c1) + c2) + c3) % 102780);

    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        } else {
            Key otherKey = (Key) obj;
            return otherKey.getKey().equals(this.getKey());
        }
    }

    @Override
    public String toString() {
        return this.key;
    }
}

class Drug {
    String drugName;
    int positiveList;
    double price;
    int quantity;

    public Drug(String drugName, int positiveList, double price, int quantity) {
        this.drugName = drugName.toUpperCase();
        this.positiveList = positiveList;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity(int decreaseNumber) {
        this.quantity -= decreaseNumber;
    }

    @Override
    public String toString() {
        String positiveListString;
        if (this.positiveList == 0) {
            positiveListString = "NEG";
        } else {
            positiveListString = "POZ";
        }
        return this.drugName
                + "\n" + positiveListString
                + "\n" + (int) this.price
                + "\n" + this.quantity;
    }
}

public class Apteka {
    public static void main(String[] args) throws IOException, InvalidDrugInputException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        HashMap<Key, Drug> drugsDB = new HashMap<>();

        importDrugs(br, N, drugsDB); //Method for importing drugs into the system;

        searchForDrugs(br, drugsDB); //Method for performing search into the drugs hash map;
    }

    private static void searchForDrugs(BufferedReader br, HashMap<Key, Drug> drugsDB) throws IOException {
        while (true) {
            String line = br.readLine();
            //Terminalen slucaj;
            if (line.equals("KRAJ")) {
                break;
            }

            int orderQuantity = Integer.parseInt(br.readLine());
            Key searchKey = new Key(line);

            if (!drugsDB.containsKey(searchKey)) {
                System.out.println("Nema takov lek");
            } else {
                Drug drugToOrder = drugsDB.get(searchKey);
                System.out.println(drugToOrder);
                if (drugToOrder.getQuantity() < orderQuantity) {
                    System.out.println("Nema dovolno lekovi");
                } else {
                    System.out.println("Napravena naracka");
                    drugToOrder.decreaseQuantity(orderQuantity);
                }

            }
        }
    }

    private static void importDrugs(BufferedReader br, int n, HashMap<Key, Drug> drugsDB) throws IOException, InvalidDrugInputException {
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] tokens = line.split("\\s+");
            /*
                tokens[0] - drugName
                tokens[1] - positiveList
                tokens[2] - price
                tokens[3] - quantity

                tokens.size() == 4;
             */
            if (tokens.length != 4) {
                throw new InvalidDrugInputException();
            } else {
                Key inputKey = new Key(tokens[0]);
                Drug inputDrug = new Drug(tokens[0]
                        , Integer.parseInt(tokens[1])
                        , Double.parseDouble(tokens[2])
                        , Integer.parseInt(tokens[3])
                );
                drugsDB.put(inputKey, inputDrug);
            }
        }
    }
}
