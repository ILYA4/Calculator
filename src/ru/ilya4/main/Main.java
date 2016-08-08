package ru.ilya4.main;

import ru.ilya4.calculator.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by ilya4 on 07.08.16.
 * ver. 0.9
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        Calculator calc;
        while(!s.equalsIgnoreCase("exit")){
            try {
                calc = new Calculator(s);
            }catch (Exception e) {
                System.out.println("Ошибка ввода");
                s = reader.readLine();
                continue;
            }
            System.out.println(calc);
            s = reader.readLine();
        }
    }
}
