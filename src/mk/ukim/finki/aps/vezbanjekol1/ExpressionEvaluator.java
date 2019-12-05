package mk.ukim.finki.aps.vezbanjekol1;

import java.util.Scanner;

public class ExpressionEvaluator {
    private static int expressionEvaluator(String expression) {
        String[] tokens = expression.split("\\+");
        int sum = 0;
        for (int i = 0; i < tokens.length; i++) {
            if (isMult(tokens[i])) {
                tokens[i] = String.valueOf(multResolver(tokens[i]));
            }
            sum += Integer.parseInt(tokens[i]);
        }
        return sum;
    }

    private static boolean isMult(String s) {
        return s.contains("*");
    }

    private static int multResolver(String s) {
        String[] tokens = s.split("\\*");
        int product = 1;
        for (String token : tokens) {
            product *= Integer.parseInt(token);
        }
        return product;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        System.out.println(expressionEvaluator(expression));
    }
}