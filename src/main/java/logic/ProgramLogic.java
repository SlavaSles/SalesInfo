package logic;

import dto.request.Request;
import dto.request.SearchRequest;
import dto.request.StatRequest;
import dto.response.ErrorResponse;
import dto.response.Response;
import dto.response.ResponseType;
import dto.response.statistic.StatResponse;

/**
 * Класс, в котором реализована логика выполнения программы
 * @author Slava Sles
 * @version 1.0
 */
public class ProgramLogic {

    /**
     * Поле - строковый массив аргументов командной строки
     */
    private String[] args;

    /**
     * Конструктор класса
     * @param args строковый массив аргументов командной строки
     */
    public ProgramLogic(String[] args) {
        this.args = args;
    }

    /**
     * Метод, который выполняет операции распознавания аргументов командной строки, чтения входного файла, выполнения
     * запросов к БД и записи в выходной файл
     */
    public void run() {
        CliArgs cliArgs = null;
        OutputFilewriter outputFilewriter = new OutputFilewriter();
        try {
            ArgsReader argsReader = new ArgsReader(args);
            cliArgs = argsReader.argsParser();
            InputFilereader inputFilereader = new InputFilereader(cliArgs);
            Request request = inputFilereader.getRequest();
            HibernateSF.initSessionFactory();
            if (cliArgs.getType() == ResponseType.search) {
                SearchLogic searchLogic = new SearchLogic((SearchRequest) request);
                Response searchResponse = searchLogic.search();
                outputFilewriter.write(searchResponse, cliArgs);
            } else {
                StatisticLogic statisticLogic = new StatisticLogic((StatRequest) request);
                StatResponse statResponse = statisticLogic.getStatistic();
                outputFilewriter.write(statResponse, cliArgs);
            }
            HibernateSF.close();
        } catch (Exception ex) { // Для печати ошибок в выходной файл
            ex.printStackTrace();
            if (cliArgs.getOutputFilePath() != null) {
                Response errorResponse = new ErrorResponse(ResponseType.error, ex.getMessage());
                outputFilewriter.write(errorResponse, cliArgs);
            }
        }
    }
}
