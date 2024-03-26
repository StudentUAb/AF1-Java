// Código Java que converte expressões infixas em expressões pós-fixas
import java.io.*; // Importa classes de entrada e saída para leitura de arquivos
import java.util.Stack; // Importa a classe Stack do pacote java.util

// Implementa a lógica de conversão de infixa para pós-fixa
public class MyInfixToPostfixConverter { // Cria uma classe para encapsular a lógica de conversão
    
    static boolean eSinal(char ch) { // Reutiliza método existente para verificar se um caractere é um sinal
        return ch == '+' || ch == '-' || ch == '*' || ch == '/'; // Retorna true se for um dos sinais
    }

    static boolean eNumero(char ch) { // Método para verificar se um caractere é um número
        return ch >= '0' && ch <= '9'; // Retorna true se for um dígito numérico
    }

    static int precedencia(char ch) { // Adiciona método para verificar precedência de operadores
        if (ch == '+' || ch == '-') { // Verifica se é soma ou subtração
            return 1; // Retorna precedência 1
        } else if (ch == '*' || ch == '/') { // Verifica se é multiplicação ou divisão
            return 2; // Retorna precedência 2
        }
        return -1; // Retorna -1 para outros casos
    }

    static String infixToPostfix(String expression) { // Cria método para conversão
        StringBuilder result = new StringBuilder(); // Usa StringBuilder para construir a expressão pós-fixa
        Stack<Character> stack = new Stack<>(); // Cria pilha para armazenar operadores

        for (char ch : expression.toCharArray()) { // Percorre cada caractere da expressão
            if (eNumero(ch)) { // Se o caractere for um número, adiciona à expressão pós-fixa
                result.append(ch); // Adiciona o caractere à expressão pós-fixa
            } else if (eSinal(ch)) { // Se for um sinal, adiciona à pilha
                while (!stack.isEmpty() && precedencia(stack.peek()) >= precedencia(ch)) { // Enquanto a pilha não estiver vazia e o topo da pilha tiver precedência maior ou igual 
                    result.append(stack.pop()); // Adiciona operadores com precedência maior à expressão pós-fixa
                }
                stack.push(ch); // Adiciona o sinal à pilha
            }
        }

        while (!stack.isEmpty()) { // Enquanto a pilha não estiver vazia
            result.append(stack.pop()); // Adiciona todos os operadores restantes à expressão pós-
        }

        return result.toString(); // Retorna a expressão pós-fixa como String
    }

    public static void main(String[] args) { // Adiciona método principal para execução do programa
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) { // Lê arquivo de entrada input.txt
            String line; // Variável para armazenar cada linha lida do arquivo
            while ((line = br.readLine()) != null) { // Lê linha a linha do arquivo até o final
                String postfixExpression = infixToPostfix(line); // Converte cada linha para pós-fixa
                System.out.println("Expressão Infixa: " + line); // Imprime expressão infixa original
                System.out.println("Expressão Pós-fixa: " + postfixExpression); // Imprime expressão correspondente em notação pós-fixa
            }
        } catch (IOException e) { // Adiciona tratamento de exceção para erros de E/S
            System.err.println("Erro ao ler o arquivo: " + e.getMessage()); // Imprime mensagem de erro
        }
    }
}
