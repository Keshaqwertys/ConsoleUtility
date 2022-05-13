package Program;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Cut {
    boolean flagInput = false;
    boolean flagOutput = false;

    boolean flagW;

    boolean flagC;
    File inputFile;
    File outputFile;
    int startRange;
    int endRange;

    public Cut(boolean flagW, boolean flagC, File inputFile, File outputFile, String range) throws IOException {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.flagW = flagW;
        this.flagC = flagC;
        String[] dividedRange = range.split("-");

        if (dividedRange.length == 1){
            dividedRange = addIndexToArray(dividedRange);
        }

        while (!isCorrect(dividedRange)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введеный вами диапозон неккоректен.");
            System.out.print("Введите дипозон: ");
            range = reader.readLine();

            dividedRange = range.split("-");

            if (dividedRange.length == 1){
                dividedRange = addIndexToArray(dividedRange);
            }
        }

        if (!dividedRange[0].equals("")){
            startRange = Integer.parseInt(dividedRange[0]);
        } else startRange = 0;
        if (!dividedRange[1].equals("")){
            endRange = Integer.parseInt(dividedRange[1]);
        } else endRange = -1;

        if (inputFile == null){
            flagInput = true;
        } else if (!inputFile.isFile()) throw new IllegalArgumentException("Входной файл некорректен");

        if (outputFile == null){
            flagOutput = true;
        } else {
            if (!outputFile.isFile()){
                if (outputFile.createNewFile()) System.out.println("Выходной файл создан");
            }
        }

    }

    public void getSlice() throws IOException {
        ArrayList<String> resultArray = new ArrayList<>();
        String[] text;
        String[] line;
        int rangeFrom = startRange;
        int rangeBefore = endRange;
        int rangeLegth;
        int count = 0;
        int counterSize = 0;

        if (flagInput) text = getInputText();
        else text = getInputFileText();

        if (flagW){
            for (String s : text) {
                StringBuilder resultLine = new StringBuilder();
                line = filterEmptyLines(s.split("[\r\t\f\s]"));
                rangeBefore = endRange;
                if (endRange > line.length - 1 || endRange == -1) rangeBefore = line.length - 1;

                for (int element = rangeFrom; element <= rangeBefore; element++) {
                    resultLine.append(line[element] + " ");
                }

                String slice = resultLine.toString().trim();
                resultArray.add(slice);
                counterSize++;

            }
        } else if (flagC){
            for (String s : text) {
                StringBuilder resultLine = new StringBuilder();
                line = s.split("");
                if (endRange == -1) rangeBefore = line.length;
                rangeLegth = rangeBefore - rangeFrom + 1;
                count = rangeFrom;
                String symbol;

                while (rangeLegth > 0){
                    if (count >= line.length) break;
                    symbol = line[count];

                    if (!Objects.equals(symbol, " ") && !Objects.equals(symbol, "\t")){ //
                        resultLine.append(symbol);
                        rangeLegth--;
                    } else {
                        resultLine.append(" ");
                    }
                    count++;
                }

                String slice = resultLine.toString().trim();
                resultArray.add(slice);
                counterSize++;
            }
        }

        String[] result = new String[counterSize];
        for (int index = 0; index < result.length; index++){
            result[index] = resultArray.get(index);
        }
        if (flagOutput)  for (String s: result) System.out.println(s);
        else writeInOutputFile(result);
    }

    private boolean isCorrect(String[] range){
        boolean result = true;
        if (range.length != 2) return false;
        String rangeOne = range[0];
        String rangeTwo = range[1];

        if (rangeOne.matches("\\w*\\D+\\w*")) return false;
        if (rangeTwo.matches("\\w*\\D+\\w*")) return false;

        if ((!rangeOne.equals("")) && (!rangeTwo.equals(""))){
            if (Integer.parseInt(rangeTwo) < Integer.parseInt(rangeOne)) return false;
        }
        return result;
    }

    private String[] getInputText() throws IOException {
        System.out.println("Введите входной текст");
        byte[] bytes;
        BufferedInputStream bis = new BufferedInputStream(System.in);
        InputStreamReader isr = new InputStreamReader(bis);

        bytes = bis.readAllBytes();

        String line = new String(bytes, StandardCharsets.UTF_8);
        isr.close();
        bis.close();
        return line.split("\n");
    }

    private String[] getInputTextAlternative() throws IOException {   //not used
        ArrayList<String> textLines = new ArrayList<String>();
        int counter = 0;
        String line;

        System.out.println("Введите входной текст");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            line = reader.readLine();
            if (line != null) textLines.add(line);
            counter++;
            if (Objects.equals(line, "/close")) line = null;
        } while (line != null);
        counter -= 1;
        reader.close();

        String[] result = new String[counter];
        for (int i = 0; i < counter; i++) {
            result[i] = textLines.get(i);
        }
        return result;
    }

    private String[] getInputFileText() throws IOException {
        ArrayList<String> arrayResult = new ArrayList<>();
        InputStreamReader fr = new InputStreamReader(new FileInputStream(inputFile));
        Scanner scan = new Scanner(fr);

        while (scan.hasNextLine()) {
            arrayResult.add(scan.nextLine());
        }

        fr.close();
        scan.close();

        String[] result = new String[arrayResult.size()];
        for (int i = 0; i < result.length; i ++){
            result[i] = arrayResult.get(i);
        }
        return result;
    }

    private void writeInOutputFile(String[] text) throws IOException {
        FileWriter fw = new FileWriter(outputFile);
        for (String s : text) {
            fw.write(s);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private String[] addIndexToArray(String[] dividedRange){
        String[] newDividedRange = new String[dividedRange.length + 1];
        for(int i = 0; i < dividedRange.length; i++){
            newDividedRange[i] = dividedRange[i];
        }
        newDividedRange[newDividedRange.length - 1] = "";
        return  newDividedRange;
    }

    private String[] filterEmptyLines(String[] splitLine){
        ArrayList<String> filtredArray = new ArrayList<>();
        int counter = 0;
        for (String s: splitLine){
            if (!s.equals("")) {
                filtredArray.add(s);
                counter++;
            }
        }
        String[] result = new String[counter];
        for (int i= 0; i < result.length; i++){
            result[i] = filtredArray.get(i);
        }
        return result;
    }


}
