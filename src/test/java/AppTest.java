import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.testng.annotations.Test;


public class AppTest {

    @Test
    public void mainAppTest() {
        App.main(new String[] { "sample_file_1.csv", "sample_file_2.csv" });

        ArrayList<String> mainOutput12 = App.getCsv("mismatch.csv");
        ArrayList<String> expectedOutput12 = App.getCsv("expectedMismatch12.csv");

        assertEquals(mainOutput12, expectedOutput12);

        App.main(new String[] { "sample_file_1.csv", "sample_file_3.csv" });

        ArrayList<String> mainOutput13 = App.getCsv("mismatch.csv");
        ArrayList<String> expectedOutput13 = App.getCsv("expectedMismatch13.csv");

        assertEquals(mainOutput13, expectedOutput13);
    }

    @Test
    public void mismatchTest() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("first");
        list1.add("second");
        list1.add("third");


        ArrayList<String> list2 = new ArrayList<>();
        list2.add("first");
        list2.add("not second");
        list2.add("third");

        ArrayList<String> actualMismatch = App.listDifference(list1, list2);

        ArrayList<String> expectedMismatch = new ArrayList<>();
        expectedMismatch.add("second" + "\n" + "not second");

        assertEquals(actualMismatch, expectedMismatch);
    }
}
