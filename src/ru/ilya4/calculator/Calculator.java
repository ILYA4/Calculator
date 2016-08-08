package ru.ilya4.calculator;

import java.util.*;

/**
 * Created by ilya4 on 07.08.16.
 * ver. 0.9
 */

public class Calculator {

    private double answer;

    public Calculator(String expression)
    {
        answer = xyz(convert(expression));
    }

    private double operation(double first, char operator, double second){
        double result  = 0;
        switch (operator) {
            case '*': result =  first*second;
            break;
            case '/': result =  first/second;
            break;
            case '-': result = first-second;
            break;
            case '+': result = first+second;
            break;
        }
        return result;
    }

    private String convert(String str){
        StringBuilder sb = new StringBuilder();
        LinkedList<Character> stackOp = new LinkedList<>();
        for (int i = 0; i<str.length(); i++){
            char c = str.charAt(i);
            String s = str.substring(i,i+1);
            if (str.charAt(i)==' ') continue;
            if (c == '(') stackOp.add(c);
            else if (c==')') {
                if (stackOp.getLast()=='(') stackOp.removeLast();
                else {
                    if (sb.charAt(sb.length()-1) !=',')sb.append(',');
                    sb.append(stackOp.getLast());
                    sb.append(',');
                    stackOp.removeLast();
                    i--;
                }
            }
            else if (isNumber(s) || c == '.') sb.append(c);
            else if (isOperator(c)){
                if (sb.charAt(sb.length()-1) !=',')sb.append(',');
                if (stackOp.isEmpty() || stackOp.getLast()=='('){
                    stackOp.add(c);
                }else if (c=='+' || c=='-' && stackOp.getLast()!='('){
                    sb.append(stackOp.getLast());
                    stackOp.removeLast();
                    sb.append(",");
                    i--;
                }else if (c=='*' || c == '/'){
                    if (stackOp.getLast()=='+' || stackOp.getLast()=='-'){
                        stackOp.add(c);
                    }else if (stackOp.getLast()=='/' || stackOp.getLast()=='*'){
                        sb.append(stackOp.getLast());
                        stackOp.removeLast();
                        sb.append(',');
                        i--;
                    }
                }
            }
        }
        if (sb.charAt(sb.length()-1)!=',') sb.append(',');
        sb.append(stackOp.getLast());
        return sb.toString();
    }



    public double xyz(String inPol){
        String[] s = inPol.split(",");
        LinkedList<Double> stack = new LinkedList<>();
        for (String str : s){
            if (isNumber(str)) stack.add(Double.parseDouble(str));
            else{
               double d = operation(stack.get(stack.size()-2), str.charAt(0), stack.get(stack.size()-1));
                stack.removeLast(); stack.removeLast();
                stack.add(d);
            }
        }
        return stack.getLast();
    }

    private boolean isNumber(String s){
        try{
            Double.parseDouble(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    @Override
    public String toString() {
        return String.valueOf(answer);
    }
}
