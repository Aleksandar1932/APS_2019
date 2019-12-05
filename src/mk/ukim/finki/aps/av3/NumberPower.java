package mk.ukim.finki.aps.av3;
/*
Да се напише функција која ќе го пресмета n-тиот
степен на некој број со примена на техниката
"Раздели и владеј"
Се бара x^n;
 */
public class NumberPower {

    public static int nthPower(int x, int n){
        int r;
        if (n==0){
            return 1;
        }
        else if(n%2==0){
            r = nthPower(x,(n/2));
            return r*r;
        }
        else{
            r=nthPower(x,(n/2));
            return x*r*r;
        }
    }
}
