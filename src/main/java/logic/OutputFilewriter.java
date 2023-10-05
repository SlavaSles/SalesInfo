package logic;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dto.response.ErrorResponse;
import dto.response.Response;
import dto.response.search.SearchResponse;
import dto.response.statistic.StatResponse;

import java.io.IOException;
import java.nio.file.Paths;

public class OutputFilewriter {


    public void write(Response response, CliArgs cliArgs) {
        ObjectMapper writeMapper = new ObjectMapper();
        ObjectWriter writer = writeMapper.writer(new DefaultPrettyPrinter());
        try {
            JsonNode jResponse = writeMapper.valueToTree(response);
            writer.writeValue(Paths.get(cliArgs.getOutputFilePath()).toFile(), jResponse);
//            if (response instanceof SearchResponse) {
//                SearchResponse searchResponse = (SearchResponse) response;
//                JsonNode jSearchResponse = writeMapper.valueToTree(response);
//                writer.writeValue(Paths.get(cliArgs.getOutputFilePath()).toFile(), jSearchResponse);
//            } else if (response instanceof StatResponse) {
//                StatResponse statResponse = (StatResponse) response;
//                JsonNode jStatResponse = writeMapper.valueToTree(statResponse);
//                writer.writeValue(Paths.get(cliArgs.getOutputFilePath()).toFile(), jStatResponse);
//            } else if (response instanceof ErrorResponse) {
//                ErrorResponse errorResponse = (ErrorResponse) response;
//                JsonNode jErrorResponse = writeMapper.valueToTree(errorResponse);
//                writer.writeValue(Paths.get(cliArgs.getOutputFilePath()).toFile(), jErrorResponse);
//            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи ответа в файл " + cliArgs.getOutputFilePath());
        }
    }
}
