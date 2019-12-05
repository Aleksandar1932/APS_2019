package mk.ukim.finki.aps.lab3;

public class SumOfAbsoluteDifferences {
    public static int solve(int[] numbers, int N, int K) {

        int[] newArray = new int[K];
        sortArray(numbers);
        int len = N-1;
        int temp = 0;
        int i = 0;
        int k = 0;
        while (temp < K-2) {
            newArray[i] = numbers[len - k];
            newArray[i + 1] = numbers[k];

            temp++;
            i += 2;
            k++;
        }

        if(K%2==1){
            newArray[K-1] = numbers[K/2];
        }

        printArray(newArray);
        return calculateAbsoluteDifference(newArray);
    }

    public static void printArray(int[] array){
        for(int i = 0 ;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    public static int calculateAbsoluteDifference(int array[]) {
        int retValue = 0;
        for (int i = 0; i < array.length - 1; i++) {
            retValue += Math.abs(array[i + 1] - array[i]);
        }
        return retValue;
    }

    public static void sortArray(int array[]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        /*
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();
    */
        int N = 10;
        int K = 3;
        int[] numbers = {1, 9, 2, 3, 6, 1, 3, 2, 1, 3};
        System.out.println(solve(numbers, N, K));
    }
}
