package com.codingsignaltest.cloudDBStorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

public class TestSimulateCodingFramework {

    private List<List<String>> testData1;
    private List<List<String>> testData2;
    private List<List<String>> testData3;
    private List<List<String>> testData4;

    @BeforeEach
    public void setUp() {
        testData1 = Arrays.asList(
                Arrays.asList("FILE_UPLOAD", "Cars.txt", "200kb"),
                Arrays.asList("FILE_GET", "Cars.txt"),
                Arrays.asList("FILE_COPY", "Cars.txt", "Cars2.txt"),
                Arrays.asList("FILE_GET", "Cars2.txt")
        );

        testData2 = Arrays.asList(
                Arrays.asList("FILE_UPLOAD", "Foo.txt", "100kb"),
                Arrays.asList("FILE_UPLOAD", "Bar.csv", "200kb"),
                Arrays.asList("FILE_UPLOAD", "Baz.pdf", "300kb"),
                Arrays.asList("FILE_SEARCH", "Ba")
        );

        testData3 = Arrays.asList(
                Arrays.asList("FILE_UPLOAD_AT", "2021-07-01T12:00:00", "Python.txt", "150kb"),
                Arrays.asList("FILE_UPLOAD_AT", "2021-07-01T12:00:00", "CodeSignal.txt", "150kb", "3600"),
                Arrays.asList("FILE_GET_AT", "2021-07-01T13:00:01", "Python.txt"),
                Arrays.asList("FILE_COPY_AT", "2021-07-01T12:00:00", "Python.txt", "PythonCopy.txt"),
                Arrays.asList("FILE_SEARCH_AT", "2021-07-01T12:00:00", "Py"),
                Arrays.asList("FILE_UPLOAD_AT", "2021-07-01T12:00:00", "Expired.txt", "100kb", "1"),
                Arrays.asList("FILE_GET_AT", "2021-07-01T12:00:02", "Expired.txt"),
                Arrays.asList("FILE_COPY_AT", "2021-07-01T12:00:00", "CodeSignal.txt", "CodeSignalCopy.txt"),
                Arrays.asList("FILE_SEARCH_AT", "2021-07-01T12:00:00", "Code")
        );

        testData4 = Arrays.asList(
                Arrays.asList("FILE_UPLOAD_AT", "2021-07-01T12:00:00", "Initial.txt", "100kb"),
                Arrays.asList("FILE_UPLOAD_AT", "2021-07-01T12:05:00", "Update1.txt", "150kb", "3600"),
                Arrays.asList("FILE_GET_AT", "2021-07-01T12:10:00", "Initial.txt"),
                Arrays.asList("FILE_COPY_AT", "2021-07-01T12:15:00", "Update1.txt", "Update1Copy.txt"),
                Arrays.asList("FILE_UPLOAD_AT", "2021-07-01T12:20:00", "Update2.txt", "200kb", "1800"),
                Arrays.asList("ROLLBACK", "2021-07-01T12:10:00"),
                Arrays.asList("FILE_GET_AT", "2021-07-01T12:25:00", "Update1.txt"),
                Arrays.asList("FILE_GET_AT", "2021-07-01T12:25:00", "Initial.txt"),
                Arrays.asList("FILE_SEARCH_AT", "2021-07-01T12:25:00", "Up"),
                Arrays.asList("FILE_GET_AT", "2021-07-01T12:25:00", "Update2.txt")
        );
    }

    @Test
    public void testGroup1() {
        List<String> output = Simulation.simulateCodingFramework(testData1);
        assertEquals(
                Arrays.asList("uploaded Cars.txt", "got Cars.txt", "copied Cars.txt to Cars2.txt", "got Cars2.txt"),
                output
        );
    }

    @Test
    public void testGroup2() {
        List<String> output = Simulation.simulateCodingFramework(testData2);
        assertEquals(
                Arrays.asList("uploaded Foo.txt", "uploaded Bar.csv", "uploaded Baz.pdf", "found [Baz.pdf, Bar.csv]"),
                output
        );
    }

    @Test
    public void testGroup3() {
        List<String> output = Simulation.simulateCodingFramework(testData3);
        assertEquals(
                Arrays.asList(
                        "uploaded at Python.txt",
                        "uploaded at CodeSignal.txt",
                        "got at Python.txt",
                        "copied at Python.txt to PythonCopy.txt",
                        "found at [Python.txt, PythonCopy.txt]",
                        "uploaded at Expired.txt",
                        "file not found",
                        "copied at CodeSignal.txt to CodeSignalCopy.txt",
                        "found at [CodeSignal.txt, CodeSignalCopy.txt]"
                ),
                output
        );
    }

    @Test
    public void testGroup4() {
        List<String> output = Simulation.simulateCodingFramework(testData4);
        assertEquals(
                Arrays.asList(
                        "uploaded at Initial.txt",
                        "uploaded at Update1.txt",
                        "got at Initial.txt",
                        "copied at Update1.txt to Update1Copy.txt",
                        "uploaded at Update2.txt",
                        "rollback to 2021-07-01T12:10:00",
                        "got at Update1.txt",
                        "got at Initial.txt",
                        "found at [Update1.txt, Update1Copy.txt, Update2.txt]",
                        "got at Update2.txt"
                ),
                output
        );
    }
}
