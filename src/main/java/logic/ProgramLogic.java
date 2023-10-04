package logic;

public class ProgramLogic {
    private String [] args;

    public ProgramLogic(String[] args) {
        this.args = args;
    }

    public void run() {
        try {
            ArgsReader argsReader = new ArgsReader(args);
            CliArgs cliArgs = argsReader.argsParser();
            InputFilereader inputFilereader = new InputFilereader(cliArgs);
            inputFilereader.getRequest();
        } catch (Exception ex) {
            ex.printStackTrace();
//            System.err.println(ex.getMessage());
        }
    }
}
