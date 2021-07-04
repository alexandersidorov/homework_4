package ru.smsoft;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReporterTest {

    private final String test1 = "\u0020Наша любимая Java?Наша любимая Java.\u0020Наша любимая Java!)";
    private final String test2 = "\u0020Наша любимая Java!)";
    private final String test3 = """
            \u0020Наша любимая Java?\
            Наша любимая Java.   \s
            \u0020Наша любимая Java!)""";

    @Test
    void getReport() {
        try {
            System.out.println(Reporter.getReport(test1));
            System.out.println(Reporter.getReport(test2));
            System.out.println(Reporter.getReport(test3));
        }catch(Exception e){
            fail();
        }
    }
}