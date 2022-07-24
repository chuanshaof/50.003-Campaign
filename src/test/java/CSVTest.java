import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class CSVTest {
    @ParameterizedTest
    @ValueSource(strings = {"sample_file_1.txt", "sample_file_1.tsv", "otherfile.pdf"})
    void fileExtensionInvalidation(String filePath) {
        boolean csv = App.isCSV(filePath);
        assertEquals(csv, false);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"sample_file_1.csv", "sample_file_2.csv", "sample_file_3.csv"})
    void fileExtensionValidation(String filePath) {
        boolean csv = App.isCSV(filePath);
        assertEquals(csv, true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"sample_file_1.csv", "sample_file_2.csv", "sample_file_3.csv"})
    void fileReadableValidation(String filePath) {
        ArrayList<String> csv = App.getCsv(filePath);
        assertNotNull(csv);
    }

    @Test
    void fileNotFound() {
        ArrayList<String> csv = App.getCsv("file_not_found.csv");
        assertNull(csv);
    }
}