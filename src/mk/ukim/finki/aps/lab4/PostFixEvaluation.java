package mk.ukim.finki.aps.lab4;

import java.util.Scanner;
import java.util.Stack;

public class PostFixEvaluation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split("\\s++");
        System.out.println(evaluatePostfix(input));
    }

    private static int evaluatePostfix(String[] input) {

        Stack<Integer> Values = new Stack<>();

        for (String s : input) {
            if (isDigit(s)) {
                Values.push(Integer.parseInt(s));
            }
            if (isOperator(s)) {
                int tempValue = evaluate(Values.pop(), Values.pop(), s.charAt(0));
                Values.push(tempValue);
            }
        }

        return Values.pop();
    }

    private static boolean isDigit(String s) {
        boolean retValue = true;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                retValue = false;
                break;
            }
        }
        return retValue;
    }

    private static boolean isOperator(String ch) {
        if (ch.length() != 1) {
            return false;
        } else {
            char[] tempCh = ch.toCharArray();
            return charIsOperator(tempCh[0]);
        }
    }

    private static boolean charIsOperator(char ch) {
        switch (ch) {
            case '+':
            case '/':
            case '*':
            case '-':
                return true;
        }
        return false;
    }

    private static int evaluate(int op2, int op1, char operator) {
        switch (operator) {
            case '+':
                return op1 + op2;
            case '/': {
                if (op2 == 0) {
                    throw new ArithmeticException("Division by zero is not allowed!");
                }
                return op1 / op2;
            }
            case '*':
                return op1 * op2;
            case '-':
                return op1 - op2;
        }
        return 0;
    }
}
