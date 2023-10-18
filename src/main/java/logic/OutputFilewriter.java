package logic;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dto.response.Response;
import errors.ErrorMessages;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Класс, который выполняет запись ответов в выходной файл в формате JSON
 */
public class OutputFilewriter {
    /**
     * Процедура, которая выполняет запись ответа в формате JSON в выходной файл с помощью Jackson JSON
     * @param response ответ на запрос из входного файла
     * @param cliArgs объект, в котором содержатся аргументы командной строки
     * @throws RuntimeException Исключение выбрасывается в случае возникновения ошибки при записи ответа в выходной
     * файл
     */
    public void write(Response response, CliArgs cliArgs) throws RuntimeException{
        ObjectMapper writeMapper = new ObjectMapper();
        ObjectWriter writer = writeMapper.writer(new DefaultPrettyPrinter());
        try {
            JsonNode jResponse = writeMapper.valueToTree(response);
            writer.writeValue(Paths.get(cliArgs.getOutputFilePath()).toFile(), jResponse);
        } catch (IOException e) {
            throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_18 + cliArgs.getOutputFilePath());
        }
    }
}
