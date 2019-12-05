package mk.ukim.finki.aps.av5;

import java.util.Scanner;
import java.util.Stack;

public class PostfixNotationSolverTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        System.out.println("The result is: " + evaluatePostfix(expression));
    }

    private static int evaluatePostfix(String expression) {
        Stack<Integer> Values = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expression.charAt(i))) {
                Values.push(Character.getNumericValue(expression.charAt(i)));
            }
            if (isOperator(expression.charAt(i))) {
                int tempValue = evaluate(Values.pop(), Values.pop(), expression.charAt(i));
                Values.push(tempValue);
            }
        }

        return Values.pop();
    }

    private static boolean isOperator(char ch) {
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
