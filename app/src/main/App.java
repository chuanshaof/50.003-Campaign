package app.src.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    // Description: Consider a CSV file that stores a list of records (e.g., records of bank accounts).
    // You are required to write a software program that reads two such CSV files, compares records
    // stored in these CSV files row by row against a unique combination and records all mismatches
    // as exceptions. Finally, the software program generates another csv file listing the exceptions.

    public static void main(String[] args) {
        ArrayList<String> csv1 = new ArrayList<>();
        ArrayList<String> csv2 = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();

        try {
            File file0 = new File("./" + args[0]);
            Scanner scanner1 = new Scanner(file0);
            scanner1.useDelimiter(",");
            while (scanner1.hasNext()) {
                csv1.add(scanner1.nextLine());
            }
            scanner1.close();
        } catch (Exception e) {
            System.err.println("File 1 not found");
        }

        try {
            File file1 = new File("./" + args[1]);
            Scanner scanner2 = new Scanner(file1);
            scanner2.useDelimiter(",");
            while (scanner2.hasNext()) {
                csv2.add(scanner2.nextLine());
            }
            scanner2.close();
        } catch (Exception e) {
            System.err.println("File 2 not found");
        }

        for (int i = 0; i < csv1.size(); i++) {
            String str1 = csv1.get(i);
            String str2 = csv2.get(i);

            if (!str1.equals(str2)) {
                output.add(str1 + "\n" + str2);
            }
        }

        File outputFile = new File("./mismatch.csv");
        try {
            PrintWriter writer = new PrintWriter(outputFile);
            for (String str : output) {
                writer.println(str);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }    
}
