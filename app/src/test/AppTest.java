package app.src.test;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import app.src.main.App;

public class AppTest {
    @Test
    public void appHasAGreeting() {
        String[] inputs = new String[] { "sample_file_1.csv", "sample_file_2.csv" };

        App.main(inputs);
    }
}
