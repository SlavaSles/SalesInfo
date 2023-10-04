package logic;

import dto.request.Request;
import org.hibernate.Session;

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
            Request request = inputFilereader.getRequest();
            Session session = HibernateSF.openSession();
            session.close();
            HibernateSF.close();
        } catch (Exception ex) { // Для печати ошибок в выходной файл
            ex.printStackTrace();
//            System.err.println(ex.getMessage());
        }
    }
}
