package mk.ukim.finki.aps.lab4;
/*
    Финтата е иста како задачата за проверка на валидност на загради,
    само наместо отворена заграда(лева) имаме отворен таг, наместо затворена заграда(десна) имаме затворен таг.

    Итерираме по ХМЛ кодот, секој отворен таг го ставаме на стек, во моментот кога ќе наидеме на затворен таг
    го вадиме последниот од стекот и ги споредуваме, ако се различни авотматски кодот не е валиден, ако се исти се
    вади од стекот, и се продолжува со истата логика, на крајот ако се е ОК стекот треба да биде празен, ако не
    е стекот празен значи не е валиден ХМЛ кодот.
 */
import java.io.*;
import java.util.*;

public class CheckXML {

    private static int checkXML(String[] xml) {
        Stack<String> openTags = new Stack<>();

        for (String s : xml) {
            if (isTag(s)) {
                if (isOpenTag(s)) {
                    openTags.push(s);
                }

                if (!isOpenTag(s)) {
                    if (openTags.empty()) {
                        return 0;
                    }
                    if (!checkSameTags(openTags.pop(), s)) {
                        return 0;
                    }
                }
            }
        }

        if (openTags.empty()) {
            return 1;
        }
        return 0;
    }

    private static boolean isOpenTag(String tag) {
        if (tag.charAt(1) != '/') {
            return true;
        } else if (tag.charAt(1) == '/') {
            return false;
        }
        return false;
    }

    private static boolean isTag(String tag) {
        return tag.charAt(0) == '[' && tag.charAt(tag.length() - 1) == ']';
    }

    private static boolean checkSameTags(String tagOpen, String tagClose) {
        return tagOpen.substring(1, tagOpen.length() - 2).equals(tagClose.substring(2, tagClose.length() - 2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String[] rows = new String[n];
        for (int i = 0; i < n; i++) {
            rows[i] = br.readLine();
        }

        System.out.println(checkXML(rows));
        br.close();
    }
}
