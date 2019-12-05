package mk.ukim.finki.aps.lab3.zad1;

import java.util.Scanner;
import java.util.Stack;

public class ArithmeticExpression {
    private static int evaluate(String expression) {
        char[] tokens = expression.toCharArray();
        Stack<Integer> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (Character.isDigit(tokens[i])) {
                values.push(Character.getNumericValue(tokens[i]));
            } else if (tokens[i] == '(') {
                ops.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop();
            } else if (tokens[i] == '+' || tokens[i] == '-') {
                while (!ops.empty() && ops.peek() != '(' && ops.peek() != ')') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                    i--;
                }

                ops.push(tokens[i]);
            }
        }

        while (!ops.empty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        str = str.substring(1, str.length() - 1);
        System.out.println(ArithmeticExpression.evaluate(str));
    }
}

//Ovoj kod raboti perfektno ako vrednosta vo (A+-B) e od 0 do 9
/*
public class ArithmeticExpression {
    public static int solve(char[] c, int l) {
        int firstIndex = -1;
        int lastIndex = -1;
        for (int i = 0; i < l - 1; i++) {

            if (c[i] == '(' && Character.isDigit(c[i + 1])) {
                firstIndex = i;
            }

            if (Character.isDigit(c[i]) && c[i + 1] == ')') {
                lastIndex = i + 1;
                break;
            }
        }

        char[] arrayToSolve = new char[5];
        System.arraycopy(c, firstIndex + 0, arrayToSolve, 0, 5);


        printCharArray(arrayToSolve);
        System.out.println("Rezultat: " + simpleExpressionSolver(arrayToSolve) + " ");


        //firstIndex = 0, lastIndex = 4 bazicen slucaj;
        if (firstIndex==0 && lastIndex == 4){
            //TODO implement Base Case;
            return simpleExpressionSolver(arrayToSolve);
        }
        else{
            //c[firstIndex]=  simpleExpressionSolver(arrayToSolve);
            c[firstIndex] = (char)(simpleExpressionSolver(arrayToSolve)+48);
            int k = 0;
            for(int i = firstIndex+1; i<l-4;i++){
                c[i]=c[lastIndex+1+k];
                k++;
            }

            char[] temp = new char[l-3];
            System.arraycopy(c, 0, temp, 0, l - 3);
            c=temp;
            System.out.println("The new Char array is: ");
            printCharArray(c);

            return solve(c,l-3);
        }
    }

    public static int simpleExpressionSolver(char[] simpleExp) {
        int op1 = Character.getNumericValue(simpleExp[1]);
        int op2 = Character.getNumericValue(simpleExp[3]);
        char operation = simpleExp[2];

        if (operation == '+') {
            return op1 + op2;
        } else {
            return op1 - op2;
        }
    }

    public static void printCharArray(char[] arrToPrint) {
        for (int i = 0; i < arrToPrint.length; i++) {
            System.out.print(arrToPrint[i]);
        }
    }

    public static void main(String[] args) {
        // String exp = "(((1+1)+(1+1))+((1+1)+(1+1)))";
        String exp = "((4+1)+((8+1)-(1+1)))";
        char[] c = exp.toCharArray();
        System.out.println(solve(c, exp.length()));
    }


}
*/

/*
Algoritam - Psvedokod;
1. Se dodeka ima tokeni za citanje citaj gi
    1.1 Zemi go naredniot token
    1.2 Ako tokenot e:
        1.2.1 Broj: PUSH vo stekot so broevi (value)
        1.2.2 Promenliva: Rezolviraj ja nejzinata vrednost i PUSH vo stekot so broevi (value)
        1.2.3 Leva zagrada ( PUSH vo stekot so operatori (ops)
        1.2.4 Desna zagrada )
            1. Se dodeka toa sto e na vrvot na stekot so operatori e Leva zagrada
                1. POP na operatorot od stekot so operatori
                2. POP dvapati na stekot so broevi, so sto se zemat dvata operandi
                3. Realiziraj ja operacijata na operandite vo soodvetniot redosled
                4. PUSH na rezultatot vo stekot so broevi (value)
            2. POP na levata zagrada od stekot so operatori i ignoriraj ja
        1.2.5 Operator (povikaj metoda thisOp)
            1. Se dodeka stekot so operatori ne e prazen i toa sto e na vrvot na stekot so operatori i
            ima ist ili pogolem prioritet od thisOp,
                1. POP na operatorot od stekot so operatori
                2. POP dvapati na stekot so vrednosti, za da se zemat dvata operandi
                3. Realiziraj ja operacijata na operandite vo soodvetniot redosled
                4. PUSH na rezultatot vo stekot so broevi (value)
            2. PUSH thisOp na stekot so operatori (ops)
2. Se dodeka stekot so operatori ne e prazen
    1. POP na operatorot od stekot so operatori
    2. POP dvapati na stekot so vrednosti, za da se zemat dvata operandi
    3. Realiziraj ja operacijata na operandite vo soodvetniot redosled
    4. PUSH na rezultatot vo stekot so broevi (value)
3. Vo ovoj moment stekot na operatori (ops) e prazen i stekot na broevi (values) ima samo eden
element i toj element e vsusnost baraniot rezultat,, t.e. resenieto na aritmetickiot izraz
 */