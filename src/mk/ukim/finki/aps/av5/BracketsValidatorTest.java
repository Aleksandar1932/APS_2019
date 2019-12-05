package mk.ukim.finki.aps.av5;

import java.util.Scanner;
import java.util.Stack;

/*
    Даден е аритметички израз, да се провери валидноста на заградите.
 */
public class BracketsValidatorTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        if (bracketsValidator(expression)) {
            System.out.println("All the brackets in the expression are correct");
        } else {
            System.out.println("There are incorrect brackets in the expression");
        }
    }

    private static boolean bracketsValidator(String expression) {
        Stack<Character> Brackets = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            if (isLeftBracket(expression.charAt(i))) {
                Brackets.push(expression.charAt(i));
            } else if (isRightBracket(expression.charAt(i))) {
                if (Brackets.empty()) {
                    return false;
                }
                char left = Brackets.pop();
                if (!compareBracketsType(left, expression.charAt(i))) {
                    return false;
                }
            }
        }
        return Brackets.empty();
    }

    private static boolean isLeftBracket(char bracket) {
        return bracket == '(' || bracket == '[' || bracket == '{';
    }

    private static boolean isRightBracket(char bracket) {
        return bracket == ')' || bracket == ']' || bracket == '}';
    }

    private static boolean compareBracketsType(char left, char right) {
        switch (left) {
            case '(':
                return (right == ')');
            case '[':
                return (right == ']');
            case '{':
                return (right == '}');
        }
        return false;
    }
}
