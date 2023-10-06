package logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.criteria.*;
import dto.request.Request;
import dto.request.SearchRequest;
import dto.request.StatRequest;
import dto.response.ResponseType;
import errors.ErrorMessages;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputFilereader {
    private String inputFilePath;
    private ResponseType type;

    public InputFilereader(CliArgs cliArgs) {
        this.inputFilePath = cliArgs.getInputFilePath();
        this.type = cliArgs.getType();
    }

    public Request getRequest() {
        String jsonFile = readFile();
        Request request;
        if (type == ResponseType.search) {
            request = parseSearchRequest(jsonFile);
        } else {
            request = parseStatRequest(jsonFile);
        }
//        System.out.println(request);
        return request;
    }

    public String readFile() {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            for (;;) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                builder.append(line).append("\n");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_04 + inputFilePath);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_05 + inputFilePath);
        }
        return builder.toString();
    }

    /**
     * При парсинге названия полей JSON файла должны четко соответствовать примеру из задания
     * Если во входном файле в каком-либо из критериев будут указаны некорректные данные,
     * то приложение выдаст сообщение об ошибке!
     */
    private SearchRequest parseSearchRequest(String jsonFile) {
        SearchRequest searchRequest = new SearchRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonData = null;
        try {
            jsonData = objectMapper.readTree(jsonFile);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_06 + inputFilePath);
        }
        JsonNode criterias = jsonData.get("criterias");
        if (criterias == null) {
            throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_07 + inputFilePath);
        }
        for (JsonNode criteriaNode : criterias) {
            Criteriya criteriya = null;
            String textCriteria = criteriaNode.toString();
            try {
                if (textCriteria.contains("lastName")) {
                    criteriya = new Lastname(criteriaNode.get("lastName").asText());
                } else if (textCriteria.contains("productName")) {
                    int minTimes = Integer.parseInt(criteriaNode.get("minTimes").asText());
                    if (minTimes < 1) {
                        throw new IllegalArgumentException(ErrorMessages.ERROR_MESSAGE_CODE_08);
                    }
                    criteriya = new Product(criteriaNode.get("productName").asText(), minTimes);
                } else if (textCriteria.contains("minExpenses")) {
                    int minExpenses = Integer.parseInt(criteriaNode.get("minExpenses").asText());
                    int maxExpenses = Integer.parseInt(criteriaNode.get("maxExpenses").asText());
                    criteriya = new ExpensesRange(minExpenses, maxExpenses);
                    if (minExpenses < 0 || maxExpenses < 0) {
                        throw new IllegalArgumentException(ErrorMessages.ERROR_MESSAGE_CODE_09);
                    } else if (minExpenses > maxExpenses) {
                        throw new IllegalArgumentException(ErrorMessages.ERROR_MESSAGE_CODE_10);
                    }
                } else if (textCriteria.contains("badCustomers")) {
                    int countBadCustomers = Integer.parseInt(criteriaNode.get("badCustomers").asText());
                    if (countBadCustomers < 1) {
                        throw new IllegalArgumentException(ErrorMessages.ERROR_MESSAGE_CODE_11);
                    }
                    criteriya = new BadCustomers(countBadCustomers);
                } else {
                    throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_12 + inputFilePath);
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_13 + inputFilePath);
            }
            searchRequest.getCriteriyas().add(criteriya);
        }
        return searchRequest;
    }
    /**
     * При парсинге названия полей JSON файла должны четко соответствовать примеру из задания
     */
    private StatRequest parseStatRequest(String jsonFile) {
        StatRequest statRequest = new StatRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonData = null;
        try {
            jsonData = objectMapper.readTree(jsonFile);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_06 + inputFilePath );
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        JsonNode startDateJson = jsonData.get("startDate");
        if (startDateJson == null) {
            throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_07 + inputFilePath);
        }
        String startDateString = startDateJson.asText();
        JsonNode endDateJson = jsonData.get("endDate");
        String endDateString = endDateJson.asText();
        try {
            statRequest.setStartDate(LocalDate.parse(startDateString, formatter));
            statRequest.setEndDate(LocalDate.parse(endDateString, formatter));
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_14 + inputFilePath);
        }
        if (statRequest.getStartDate() == null || statRequest.getEndDate() == null) {
            throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_15 + inputFilePath);
        } else if (statRequest.getStartDate().isAfter(statRequest.getEndDate())) {
            throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_16);
        }
        return statRequest;
    }
}
