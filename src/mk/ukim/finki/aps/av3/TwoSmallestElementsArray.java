package mk.ukim.finki.aps.av3;

/*
Пронајдете ги двата најмали елементи во дадена низа со помош на техниката "раздели и владеј".
 */
class TwoSmallest {
    public int a;
    public int b;

    public TwoSmallest() {

    }

    public TwoSmallest(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

class TwoSmallestElementsArray {
    int INF = 1000000;

    TwoSmallest find(int a[], int l, int r) {
        if (l == r) {
            TwoSmallest dn = new TwoSmallest(a[1], INF);
            return dn;
        }

        int mid = (l + r) / 2;

        TwoSmallest r1 = find(a, l, mid);
        TwoSmallest r2 = find(a, mid + 1, r);
        TwoSmallest r3 = new TwoSmallest();

        if (r1.a < r2.a) {
            r3.a = r1.a;
            r3.b = Math.min(r1.b, r2.a);
        } else {
            r3.a = r2.a;
            r3.b = Math.min(r1.a, r2.b);
        }

        return r3;
    }
}

class TwoSmallestElementsArrayTest {
    public static void main(String[] args) {
        int niza[] = new int[]{9, 2, 4, 6, 0, 8, 7, 3, 1, 5};
        TwoSmallestElementsArray obj = new TwoSmallestElementsArray();
        TwoSmallest result = obj.find(niza, 0, 10);
        System.out.println("Najmaliot e: " + result.a + ", dodeka vtoriot najmal e: " + result.b);
    }
}
