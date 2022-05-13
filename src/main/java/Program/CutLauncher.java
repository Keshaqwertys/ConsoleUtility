package Program;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CutLauncher {

    @Option(name = "-c", forbids = {"-w"})
    private boolean flagC;

    @Option(name = "-w")
    private boolean flagW;

    @Option(name = "-o")
    private File outputFile;

    @Option(name = "-r")//, required = true)
    private String range;

    @Argument(metaVar = "InputName")
    private File inputFile;

    public static void main(String[] args) throws IOException {
        new CutLauncher().launch(args);
    }

    public void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e){
            System.err.println(e.getMessage());
            System.err.println("cut [-c|-w] [-o ofile] [-r range] [file]");
            parser.printUsage(System.err);
            System.exit(0);
        }

        if (range == null){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Диапозон не задан. Введите диапозон: ");
            range = reader.readLine();

        }

        boolean checkFlags = (!flagC) && (!flagW);
        int counter = 0;
        while (checkFlags){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if (counter == 0) System.out.println("Вы не указали параметр диапазона");
            System.out.println("-w: диапозон указывает на слова");
            System.out.println("-с: диапозон указывает на буквы");
            System.out.print("Введите параметр: ");
            String result = reader.readLine().trim();

            if (result.equals("-w")) flagW = true;
            if (result.equals("-c")) flagC = true;

            checkFlags = (!flagC) && (!flagW);
            if (checkFlags) System.out.println("Введный вами параметр неккоректен");
            counter += 1;

        }


        Cut cutter = new Cut(this.flagW, this.flagC ,this.inputFile, this.outputFile, this.range);

        //cutter.getInformation();

        //cutter.getSlice(this.flagC, this.flagW);
        cutter.getSlice();


    }
}
