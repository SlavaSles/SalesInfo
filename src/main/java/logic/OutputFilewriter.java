package logic;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dto.response.Response;
import errors.ErrorMessages;

import java.io.IOException;
import java.nio.file.Paths;

public class OutputFilewriter {


    public void write(Response response, CliArgs cliArgs) {
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
