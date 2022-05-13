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

    @Option(name = "-r", required = true)
    private String range;

    @Argument()
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

        Cut cutter = new Cut(this.flagW, this.flagC ,this.inputFile, this.outputFile, this.range);

        cutter.getSlice();


    }
}
