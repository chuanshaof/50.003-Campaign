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
        ArrayList<String> mismatch = new ArrayList<>();

        if (args.length != 2) {
            System.out.println("Please provide two csv files as input.");
            return;
        }

        csv1 = getCsv(args[0]);

        csv2 = getCsv(args[1]);
        
        // csv1 = getCsv("sample_file_1.csv");
        // csv2 = getCsv("sample_file_2.csv");

        mismatch = listDifference(csv1, csv2);

        File outputFile = new File("src/main/resources/mismatch.csv");

        try {
            PrintWriter writer = new PrintWriter(outputFile);
            for (String str : mismatch) {
                writer.println(str);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }

    public static ArrayList<String> getCsv(String filePath) {

        if (App.isCSV(filePath)) {
            ArrayList<String> csv = new ArrayList<>();
            try {
                File file = new File("src/main/resources/" + filePath);

                Scanner scanner = new Scanner(file);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    csv.add(scanner.nextLine());
                }
                scanner.close();
                return csv;
            } catch (Exception e) {
                System.err.println("File " + filePath + " not found");
                return null;
            }
        } else {
            return null;
        }
    }

    public static Boolean isCSV(String filePath) {
        if (filePath.length() > 4) {
            if (filePath.substring(filePath.length() - 4).equals(".csv")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static ArrayList<String> listDifference(ArrayList<String> list1, ArrayList<String> list2) {
        if (list1.size() != list2.size()) {
            return null;
        }

        ArrayList<String> output = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++) {
            String str1 = list1.get(i);
            String str2 = list2.get(i);

            if (!str1.equals(str2)) {
                output.add(str1 + "\n" + str2);
            }
        }

        return output;
    }
}

