import java.io.*;
import java.util.Stack;

public class MyInfixToPostfixConverter {
    
    static boolean eSinal(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    static boolean eNumero(char ch) {
        return ch >= '0' && ch <= '9';
    }

    static int precedencia(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        }
        return -1;
    }

    static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (eNumero(ch)) {
                result.append(ch);
            } else if (eSinal(ch)) {
                while (!stack.isEmpty() && precedencia(stack.peek()) >= precedencia(ch)) {
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String postfixExpression = infixToPostfix(line);
                System.out.println("Expressão Infixa: " + line);
                System.out.println("Expressão Pós-fixa: " + postfixExpression);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
