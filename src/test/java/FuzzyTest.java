import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import org.testng.annotations.Test;

public class FuzzyTest {
    @Test
    public void fuzzyTest() {
        for (int i = 0; i < 100; i++) {
            generateRandomCSV();

            App.main(new String[] { "sample_file_1.csv", "fuzzer.csv" });
    
            ArrayList<String> output = App.getCsv("mismatch.csv");
            ArrayList<String> expectedOutput = App.getCsv("expectedFuzzer.csv");
    
            assertEquals(output, expectedOutput);
        }
    }

    public void generateRandomCSV() {
        ArrayList<String> csv = App.getCsv("sample_file_1.csv");
        int rand = new Random().nextInt(2);
        if (rand == 0) {
            swapCharacters(csv);
        } else if (rand == 1) {
            changeCharacter(csv);
        }
    }

    public void swapCharacters(ArrayList<String> csv) {   
        ArrayList<String> expectedFuzzer = new ArrayList<>();

        // Pick random row from csv  
        int index = new Random().nextInt(1, csv.size());
        String row = csv.get(index);

        expectedFuzzer.add(row);
        
        // Pick random item from row
        String[] elements = row.split(",");
        int randElement = new Random().nextInt(elements.length);
        String element = elements[randElement];

        // Meant to swap characters in a string
        char[] charArr = element.toCharArray();
        int randChar = new Random().nextInt(1, element.length() - 2);
        char temp = charArr[randChar];
        charArr[randChar] = charArr[randChar + 1];
        charArr[randChar + 1] = temp;

        // Return item back into its original string
        elements[randElement] = new String(charArr);
        row = String.join(",", elements);    
        expectedFuzzer.add(row);
        csv.set(index, row);
        
        // In the case where the swapped characters are the same
        if (charArr[randChar] == charArr[randChar + 1]) {
            expectedFuzzer = new ArrayList<>();
        }

        File fuzzerFile =new File("src/main/resources/fuzzer.csv");
        try {
            PrintWriter writer = new PrintWriter(fuzzerFile);
            for (String str : csv) {
                writer.println(str);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }

        File outputFile = new File("src/main/resources/expectedFuzzer.csv");
        try {
            PrintWriter writer = new PrintWriter(outputFile);
            for (String str : expectedFuzzer) {
                writer.println(str);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }

    public void changeCharacter(ArrayList<String> csv) {
        ArrayList<String> expectedFuzzer = new ArrayList<>();

        // Pick random row from csv  
        int index = new Random().nextInt(1, csv.size());
        String row = csv.get(index);

        expectedFuzzer.add(row);
        
        // Pick random item from row
        String[] elements = row.split(",");
        int randElement = new Random().nextInt(elements.length);
        String element = elements[randElement];

        // Meant to change a character in a string
        char[] charArr = element.toCharArray();
        int randChar = new Random().nextInt(1, element.length() - 2);
        char originalChar = charArr[randChar];
        char[] alphaNum = "abcdefghjilkmnopqrstuvwxyz0123456789".toCharArray();
        char randAlphaNum = alphaNum[new Random().nextInt(alphaNum.length)];
        charArr[randChar] = randAlphaNum;

        // Return item back into its original string
        elements[randElement] = new String(charArr);
        row = String.join(",", elements);    
        expectedFuzzer.add(row);
        csv.set(index, row);
        
        // In the case where the swapped characters are the same
        if (randAlphaNum == originalChar) {
            expectedFuzzer = new ArrayList<>();
        }

        File fuzzerFile =new File("src/main/resources/fuzzer.csv");
        try {
            PrintWriter writer = new PrintWriter(fuzzerFile);
            for (String str : csv) {
                writer.println(str);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }

        File outputFile = new File("src/main/resources/expectedFuzzer.csv");
        try {
            PrintWriter writer = new PrintWriter(outputFile);
            for (String str : expectedFuzzer) {
                writer.println(str);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }
}
