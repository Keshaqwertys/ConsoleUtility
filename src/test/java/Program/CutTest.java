package Program;

import org.junit.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CutTest {
    @Test
    public void getSliceWordRangeTest1() throws IOException {
        File inputFile = new File("files\\inputFile.txt");
        File outputFile = new File("files\\outputFile.txt");
        File testFile1 = new File("files\\Test1.txt");
        Cut cutter;

        cutter = new Cut(true,false, inputFile,outputFile,"0-11");
        cutter.getSlice();

        assertTrue(assertEqualsFile(outputFile,testFile1));
        assertTrue(assertEqualsFile(outputFile,inputFile));
    }
    @Test
    public void getSliceWordRangeTest2() throws IOException {
        File inputFile = new File("files\\inputFile.txt");
        File outputFile = new File("files\\outputFile.txt");
        File testFile2 = new File("files\\Test2.txt");
        Cut cutter;

        cutter = new Cut(true,false, inputFile,outputFile,"1-2");
        cutter.getSlice();

        assertTrue(assertEqualsFile(outputFile,testFile2));
        assertFalse(assertEqualsFile(inputFile,testFile2));
    }

    @Test
    public void getSliceWordRangeTest3() throws IOException {
        File inputFile = new File("files\\inputFile.txt");
        File outputFile = new File("files\\outputFile.txt");
        File testFile3 = new File("files\\Test3.txt");
        Cut cutter;

        cutter = new Cut(true,false, inputFile,outputFile,"1-");
        cutter.getSlice();

        assertTrue(assertEqualsFile(outputFile,testFile3));
        assertFalse(assertEqualsFile(inputFile,testFile3));
    }

    @Test
    public void getSliceWordRangeTest4() throws IOException {
        File inputFile = new File("files\\inputFile.txt");
        File outputFile = new File("files\\outputFile.txt");
        File testFile4 = new File("files\\Test4.txt");
        Cut cutter;

        cutter = new Cut(true,false, inputFile,outputFile,"-0");
        cutter.getSlice();

        assertTrue(assertEqualsFile(outputFile,testFile4));
        assertFalse(assertEqualsFile(inputFile,testFile4));
    }

    @Test
    public void getSliceCharRangeTest1() throws IOException {
        File inputFile = new File("files\\inputFile.txt");
        File outputFile = new File("files\\outputFile.txt");
        File testFile5 = new File("files\\Test5.txt");
        Cut cutter;

        cutter = new Cut(false,true, inputFile,outputFile,"0-50");
        cutter.getSlice();

        assertTrue(assertEqualsFile(outputFile,testFile5));
        assertTrue(assertEqualsFile(outputFile,inputFile));
    }
    @Test
    public void getSliceCharRangeTest2() throws IOException {
        File inputFile = new File("files\\inputFile.txt");
        File outputFile = new File("files\\outputFile.txt");
        File testFile6 = new File("files\\Test6.txt");
        Cut cutter;

        cutter = new Cut(false,true, inputFile,outputFile,"2-");
        cutter.getSlice();

        assertTrue(assertEqualsFile(outputFile,testFile6));
    }

    @Test
    public void getSliceCharRangeTest3() throws IOException {
        File inputFile = new File("files\\inputFile.txt");
        File outputFile = new File("files\\outputFile.txt");
        File testFile7 = new File("files\\Test7.txt");
        Cut cutter;

        cutter = new Cut(false,true, inputFile,outputFile,"-10");
        cutter.getSlice();

        assertTrue(assertEqualsFile(outputFile,testFile7));
        assertFalse(assertEqualsFile(inputFile,outputFile));
    }

    @Test
    public void getSliceCharRangeTest4() throws IOException {
        File inputFile = new File("files\\inputFile.txt");
        File outputFile = new File("files\\outputFile.txt");
        File testFile8 = new File("files\\Test8.txt");
        Cut cutter;

        cutter = new Cut(false,true, inputFile,outputFile,"100-200");
        cutter.getSlice();

        assertTrue(assertEqualsFile(outputFile,testFile8));
        assertFalse(assertEqualsFile(inputFile,outputFile));
    }


    public boolean assertEqualsFile(File firsFile, File secondFile) throws IOException {
        boolean result = true;

        if (firsFile == secondFile) return true;

        InputStreamReader frFirst = new InputStreamReader(new FileInputStream(firsFile));
        Scanner scanFirst = new Scanner(frFirst);
        InputStreamReader frSecond = new InputStreamReader(new FileInputStream(secondFile));
        Scanner scanSecond = new Scanner(frSecond);

        while (scanFirst.hasNextLine()){
            if (!scanSecond.hasNextLine()) return false;

            String textFirst = scanFirst.nextLine();
            String textSecond = scanSecond.nextLine();

            if (!textFirst.equals(textSecond)) return false;
        }

        if (scanSecond.hasNextLine()) return false;

        frFirst.close();
        frSecond.close();

        return result;
    }
}
