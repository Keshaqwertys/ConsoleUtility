package Program;

import org.junit.Test;
import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class CutTest {
    @Test
    public void getSliceTest1() throws IOException {
        Cut cutter;
        //File outputFile;
        File inputFile = new File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleUtility\\src\\main\\resources\\inputFile.txt");
        File outputFile = new File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleUtility\\src\\main\\resources\\outputFile.txt");

        // Тест 1
        File testFile1 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleUtility\\src\\main\\resources\\Test1.txt");

        cutter = new Cut(true,false, inputFile,outputFile,"0-11");
        cutter.getSlice();

        //outputFile = cutter.getOutputFile();

        assertTrue(assertEqualsFile(outputFile,testFile1));

        /*
        // Тест 2
        File testFile2 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleUtility\\src\\main\\resources\\Test2.txt");

        cutter = new Cut(false,true, outputFile,inputFile,"1-2");
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(assertEqualsFile(outputFile,testFile2));

        // Тест 3

        File testFile3 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleUtility\\src\\main\\resources\\Test3.txt");

        cutter = new Cut(false,true, outputFile,inputFile,"1-");
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(assertEqualsFile(outputFile,testFile3));

        // Тест 4

        File testFile4 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleUtility\\src\\main\\resources\\Test4.txt");

        cutter = new Cut(false,true,outputFile, inputFile, "-0");
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(assertEqualsFile(outputFile,testFile4));

        // Тест 5

        File testFile5 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleUtility\\src\\main\\resources\\Test5.txt");

        cutter = new Cut(true,false,outputFile, inputFile, "0-50");
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(assertEqualsFile(outputFile,testFile5));


        // Тест 6

        File testFile6 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleUtility\\src\\main\\resources\\Test6.txt");

        cutter = new Cut(true,false,outputFile, inputFile, "2-");
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(assertEqualsFile(outputFile,testFile6));

        // Тест 7

        File testFile7 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleUtility\\src\\main\\resources\\Test7.txt");

        cutter = new Cut(true,false,outputFile, inputFile, "-10");
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(assertEqualsFile(outputFile,testFile7));

        // Тест 8

        File testFile8 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleUtility\\src\\main\\resources\\Test8.txt");

        cutter = new Cut(true,false,outputFile, inputFile, "100-200");
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(assertEqualsFile(outputFile,testFile8));
         */

    }
    @Test
    public void getSliceTest2() throws IOException {

        Cut cutter2;
        File inputFile = new File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleUtility\\src\\main\\resources\\inputFile.txt");
        File outputFile = new File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleUtility\\src\\main\\resources\\outputFile.txt");
        // Тест 2
        File testFile2 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleUtility\\src\\main\\resources\\Test2.txt");

        cutter2 = new Cut(true,false, inputFile,outputFile,"1-2");
        cutter2.getSlice();

        //outputFile = cutter.getOutputFile();

        assertTrue(assertEqualsFile(outputFile,testFile2));


        //File outFile = new File("files\\outputFile.txt");
        //File inFile = new File("files\\input.txt");
        //Cut cutter = new Cut(true,false,inFile,outFile,"1-2");

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
