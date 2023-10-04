package logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.criteria.*;
import dto.request.SearchRequest;
import dto.request.StatRequest;
import dto.response.ResponseType;

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

    public void getRequest() {
        String jsonFile = readFile();
        if (type == ResponseType.SEARCH) {
            SearchRequest searchRequest = parseSearchRequest(jsonFile);
            System.out.println(searchRequest);
        } else {
            StatRequest statRequest = parseStatRequest(jsonFile);
            System.out.println(statRequest);
        }
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
            throw new RuntimeException("Не удалось найти файл " + inputFilePath);
        } catch (Exception ex) {
            throw new RuntimeException("Ошибка чтения файла " + inputFilePath);
        }
        return builder.toString();
    }

//    При парсинге названия полей JSON файла должны четко соответствовать примеру из задания
//    Todo: сделать проверку на допустимые значения параметров (>< 0 и т. д.)
    private SearchRequest parseSearchRequest(String jsonFile) {
        SearchRequest searchRequest = new SearchRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonData = null;
        try {
            jsonData = objectMapper.readTree(jsonFile);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка преобразования json содержимого файла " + inputFilePath + " в объект");
        }
        JsonNode criterias = jsonData.get("criterias");
        for (JsonNode criteriaNode : criterias) {
            Criteria criteria = null;
            String textCriteria = criteriaNode.toString();
            try {
                if (textCriteria.contains("lastName")) {
                    criteria = new Lastname(criteriaNode.get("lastName").asText());
                } else if (textCriteria.contains("productName")) {
                    criteria = new Product(criteriaNode.get("productName").asText(),
                            Integer.parseInt(criteriaNode.get("minTimes").asText()));
                } else if (textCriteria.contains("minExpenses")) {
                    criteria = new ExpensesRange(
                            Integer.parseInt(criteriaNode.get("minExpenses").asText()),
                            Integer.parseInt(criteriaNode.get("maxExpenses").asText())
                    );
                } else if (textCriteria.contains("badCustomers")) {
                    criteria = new BadCustomers(
                            Integer.parseInt(criteriaNode.get("badCustomers").asText())
                    );
                } else {
                    throw new RuntimeException("Неверный формат одного из критериев (полей) во входном файле " +
                            inputFilePath);
                }
            } catch (NumberFormatException ex) {
                throw new RuntimeException("Ошибка преобразования значения поля входного файла " +
                        inputFilePath + " в целое число");
            }
            searchRequest.getCriterias().add(criteria);
        }
        return searchRequest;
    }

//    При парсинге названия полей JSON файла должны четко соответствовать примеру из задания
    private StatRequest parseStatRequest(String jsonFile) {
        StatRequest statRequest = new StatRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonData = null;
        try {
            jsonData = objectMapper.readTree(jsonFile);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка преобразования json содержимого файла " + inputFilePath + " в объект");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDateString = jsonData.get("startDate").asText();
        String endDateString = jsonData.get("endDate").asText();
        try {
            statRequest.setStartDate(LocalDate.parse(startDateString, formatter));
            statRequest.setEndDate(LocalDate.parse(endDateString, formatter));
        } catch (DateTimeParseException ex) {
            throw new RuntimeException("Ошибка преобразования значения поля входного файла " +
                    inputFilePath + " в дату");
        }
        if (statRequest.getStartDate() == null || statRequest.getEndDate() == null) {
            throw new RuntimeException("Неверный формат одной из дат во входном файле " + inputFilePath);
        } else if (statRequest.getStartDate().isAfter(statRequest.getEndDate())) {
            throw new RuntimeException("Дата начала периода для сбора статистики должна быть " +
                    "меньше даты окончания периода");
        }
        return statRequest;
    }
}
