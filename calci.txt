import java.util.InputMismatchException;
import java.util.Scanner;
class calculator{
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        double num1,num2;
        double result;
        char operator;
        num1 = getValidNumber(in, "Enter first number:");
        num2 = getValidNumber(in,"Enter second number:");
        operator = getValidOperator(in, "Enter an operator (+, -, *, /):");
        switch (operator){
                case '+':
                result = num1 + num2;
                System.out.println("result : "+ num1 + "+" + num2 + "=" + result);
                break;
                case '-':
                result = num1 - num2;
                System.out.println("result : "+ num1 + "-" + num2 + "=" + result);
                break;
                case '*':
                result = num1 * num2;
                System.out.println("result : "+ num1 + "*" + num2 + "=" + result);
                break;
                case '/':
                if(num2 != 0){
                    result = num1 / num2;
                    System.out.println("result : "+ num1 +"/" + num2 + "=" + result);
                    break;
                }
                else {
                        System.out.println("Division with 0 is not possible.");
                        System.out.print("Do you want to re-enter the denominator value (YES or NO)? ");
                        String choice = in.next().toLowerCase();
                        if (choice.equals("yes")) {
                            System.out.println("re-enter the second number:");
                            num2=in.nextDouble();
                            result=num1/num2;
                            System.out.println("result : " + num1 + "/" + num2 + "=" + result);
                            return; 
                        }
                        else if(choice.equals("no")){
                            System.out.println("Operation terminated due to division with 0 is not possible");
                            return;
                        }
                    break;
                }
                default:
                System.out.println("Error: Invalid operator.");
                break;
        }
        in.close();
    }
        
    
    private static double getValidNumber(Scanner in, String prompt) {
        double number = 0;
        while (true) {
            System.out.println(prompt);
            try {
                number = in.nextDouble();
                break; 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                in.next(); 
            }
        }
        return number;
    }
    
    private static char getValidOperator(Scanner in, String prompt) {
        char op;
        while (true) {
            System.out.print(prompt);
            String input = in.next();
            if (input.length() == 1 && "+-*/".indexOf(input.charAt(0)) != -1) {
                op = input.charAt(0);
                break; 
            } else {
                System.out.println("Invalid operator! Please enter one of the following: +, -, *, /.");
            }
        }
        return op;
    }

}




***************************************************************************************************************************************



import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class CalculatorWithFocusedParen {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the expression:");
        String expr = in.nextLine();

        expr = fixUnmatchedRightParentheses(expr, in);

        if (checkMismatchedParentheses(expr)) {
            System.out.println("Please check your parentheses. There are unmatched left parentheses.");
        }

        try {
            double result = evaluateExpression(expr);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error evaluating expression: " + e.getMessage());
        }

        in.close();
    }

    private static String fixUnmatchedRightParentheses(String expr, Scanner in) {
        LinkedList<Integer> leftParenPositions = new LinkedList<>();

        while (true) {
            leftParenPositions.clear();
            boolean unmatchedRightFound = false;

            for (int i = 0; i < expr.length(); i++) {
                char c = expr.charAt(i);
                if (c == '(') {
                    leftParenPositions.add(i);
                } else if (c == ')') {
                    if (leftParenPositions.isEmpty()) {
                        unmatchedRightFound = true;
                        System.out.println("Unmatched right parenthesis detected at index " + i);
                        int insertIndex = -1;
                        while (true) {
                            System.out.print("Enter index to insert missing '(' (0 to " + i + "): ");
                            String input = in.nextLine();
                            try {
                                insertIndex = Integer.parseInt(input);
                                if (insertIndex >= 0 && insertIndex <= i) {
                                    break;
                                } else {
                                    System.out.println("Invalid index. Must be between 0 and " + i);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter an integer.");
                            }
                        }
                        expr = expr.substring(0, insertIndex) + "(" + expr.substring(insertIndex);
                        System.out.println("Expression after insertion: " + expr);
                        break;
                    } else {
                        leftParenPositions.removeLast();
                    }
                }
            }

            if (!unmatchedRightFound) {
                break; // no unmatched right parentheses remain
            }
        }
        return expr;
    }

    private static boolean checkMismatchedParentheses(String expr) {
        int openCount = 0;
        for (char c : expr.toCharArray()) {
            if (c == '(') openCount++;
            else if (c == ')') openCount--;
        }
        return openCount > 0; // returns true if there are unmatched left parentheses
    }

    private static double evaluateExpression(String expr) throws Exception {
        expr = expr.replaceAll("\\s+", "");
        return evaluateRecursive(expr);
    }

    private static double evaluateRecursive(String expr) throws Exception {
        int open = expr.lastIndexOf('(');
        if (open != -1) {
            int close = expr.indexOf(')', open);
            if (close == -1) {
                throw new Exception("Mismatched parentheses");
            }
            String inside = expr.substring(open + 1, close);
            double insideVal = evaluateRecursive(inside);
            expr = expr.substring(0, open) + insideVal + expr.substring(close + 1);
            return evaluateRecursive(expr);
        }
        return evaluateSimple(expr);
    }

    private static double evaluateSimple(String expr) throws Exception {
        if (expr.isEmpty()) {
            throw new Exception("Empty expression");
        }
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                int start = i;
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    i++;
                }
                double val = Double.parseDouble(expr.substring(start, i));
                values.push(val);
                continue;
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (c == '-' && (i == 0 || "+-*/".indexOf(expr.charAt(i - 1)) != -1)) {
                    int start = i;
                    i++;
                    while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                        i++;
                    }
                    double val = Double.parseDouble(expr.substring(start, i));
                    values.push(val);
                    continue;
                }
                while (!ops.isEmpty() && hasPrecedence(c, ops.peek())) {
                    char op = ops.pop();
                    double b = values.pop();
                    double a = values.pop();
                    values.push(applyOp(op, a, b));
                }
                ops.push(c);
            } else {
                throw new Exception("Invalid character found: " + c);
            }
            i++;
        }
        while (!ops.isEmpty()) {
            char op = ops.pop();
            double b = values.pop();
            double a = values.pop();
            values.push(applyOp(op, a, b));
        }
        return values.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if ((op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-')) return true;
        if ((op2 == '+' || op2 == '-') && (op1 == '+' || op1 == '-')) return true;
        if ((op2 == '*' || op2 == '/') && (op1 == '*' || op1 == '/')) return true;
        return false;
    }

    private static double applyOp(char op, double a, double b) throws Exception {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': 
                if (b == 0) throw new Exception("Division by zero");
                return a / b;
        }
        throw new Exception("Invalid operator: " + op);
    }
}













