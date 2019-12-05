package mk.ukim.finki.aps.lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

class IncompatibleIPException extends Exception{
    public IncompatibleIPException() {
        super();
    }
}

class IP implements Comparable<IP> {
    String mask;
    String address;

    public IP(String rawIP) throws IncompatibleIPException {
        /*Sekoja IP e vo format XX.XX.XX.YY
            Kade:
                XX.XX.XX e mask;
                YY e address;
         */
        String[] tokens = rawIP.split("\\.");

        if (tokens.length < 4) {
            throw new IncompatibleIPException();
        } else {
            this.mask = tokens[0] + "." + tokens[1] + "." + tokens[2] + ".";
            this.address = tokens[3];
        }
    }

    boolean canBeRouted(IP toCheck) {
        return this.mask.equals(toCheck.mask);
    }

    String getFullIP() {
        return "" + this.mask + this.address;
    }

    @Override
    public String toString() {
        return "IP{" +
                "mask='" + mask + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int compareTo(IP ip) {
        return this.getFullIP().compareTo(ip.getFullIP());
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            IP temp = (IP) obj;
            return (this.getFullIP().equals(temp.getFullIP()));
        }
    }
}

public class RoutingHashJava {
    public static void main(String[] args) throws IOException, IncompatibleIPException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Hashtable<String, ArrayList<IP>> routingTable = new Hashtable<>(N);

        for (int i = 0; i < N; i++) {
            String routerIP = br.readLine();
            String staticRoutes = br.readLine();
            routingTable.put(routerIP, generateIPList(staticRoutes));
        }


        int attempts = Integer.parseInt(br.readLine());

        for (int i = 0; i < attempts; i++) {
            String rip = br.readLine();
            String sip = br.readLine();

            IP searchIP = new IP(sip);

            if (routingTable.containsKey(rip)) {
                ArrayList<IP> resultIPArray = routingTable.get(rip);
                boolean canBeRouted = false;
                for (IP ip : resultIPArray) {
                    if (ip.canBeRouted(searchIP)) {
                        canBeRouted = true;
                        break;
                    }
                }

                if (canBeRouted) {
                    System.out.println("postoi");
                } else {
                    System.out.println("ne postoi");
                }
            } else {
                System.out.println("ne postoi");
            }
        }
    }


    private static ArrayList<IP> generateIPList(String rawIPList) throws IncompatibleIPException {
        /*Metoda, koja prima string od IP adresi so DELIMITER:, do koja ruterot moze da rutira, i vraka
           ArrayLista od IP adresi;
        */
        ArrayList<IP> retList = new ArrayList<>();
        String[] tokens = rawIPList.split(",");
        for (String token : tokens) {
            try {
                retList.add(new IP(token));
            } catch (IncompatibleIPException e) {
                retList.add(new IP(token + ".0"));
            }
        }

        return retList;
    }
}
