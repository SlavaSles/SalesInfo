package logic;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dto.response.ErrorResponse;
import dto.response.Response;
import dto.response.search.SearchResponse;

import java.io.IOException;
import java.nio.file.Paths;

public class OutputFilewriter {


    public void write(Response response, CliArgs cliArgs) {
        ObjectMapper writeMapper = new ObjectMapper();
        ObjectWriter writer = writeMapper.writer(new DefaultPrettyPrinter());
        try {
            if (response instanceof SearchResponse) {
                SearchResponse searchResponse = (SearchResponse) response;

                writer.writeValue(Paths.get(cliArgs.getOutputFilePath()).toFile(), searchResponse);
            } else if (response instanceof ErrorResponse) {
                ErrorResponse errorResponse = (ErrorResponse) response;
                writer.writeValue(Paths.get(cliArgs.getOutputFilePath()).toFile(), errorResponse);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи ответа в файл " + cliArgs.getOutputFilePath());
        }
    }


    }
}
