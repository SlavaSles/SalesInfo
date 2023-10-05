package logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.criteria.*;
import dto.request.Request;
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

    public Request getRequest() {
        String jsonFile = readFile();
        Request request;
        if (type == ResponseType.SEARCH) {
            request = parseSearchRequest(jsonFile);
        } else {
            request = parseStatRequest(jsonFile);
        }
        System.out.println(request);
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
            throw new RuntimeException("Не удалось найти файл " + inputFilePath);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Ошибка чтения файла " + inputFilePath);
        }
        return builder.toString();
    }

//    При парсинге названия полей JSON файла должны четко соответствовать примеру из задания
//    ToDo: сделать проверку на допустимые значения параметров (>< 0 и т. д.)
    private SearchRequest parseSearchRequest(String jsonFile) {
        SearchRequest searchRequest = new SearchRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonData = null;
        try {
            jsonData = objectMapper.readTree(jsonFile);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Ошибка преобразования json содержимого файла " + inputFilePath + " в объект");
        }
        JsonNode criterias = jsonData.get("criterias");
        if (criterias == null) {
            throw new RuntimeException("Тип операции в параметрах запуска не соответствует типу входного файла "
                    + inputFilePath);
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
                        throw new IllegalArgumentException("Количество товаров minTimes должно быть больше 0");
                    }
                    criteriya = new Product(criteriaNode.get("productName").asText(), minTimes);
                } else if (textCriteria.contains("minExpenses")) {
                    int minExpenses = Integer.parseInt(criteriaNode.get("minExpenses").asText());
                    int maxExpenses = Integer.parseInt(criteriaNode.get("maxExpenses").asText());
                    criteriya = new ExpensesRange(minExpenses, maxExpenses);
                    if (minExpenses < 0 || maxExpenses < 0) {
                        throw new IllegalArgumentException("Минимальная minExpenses и максимальная maxExpenses " +
                                "стоимость покупок должны быть не меньше 0");
                    } else if (minExpenses > maxExpenses) {
                        throw new IllegalArgumentException("Значение минимальной стоимости покупок minExpenses " +
                                "должно быть меньше максимальной maxExpenses стоимости");
                    }
                } else if (textCriteria.contains("badCustomers")) {
                    int countBadCustomers = Integer.parseInt(criteriaNode.get("badCustomers").asText());
                    if (countBadCustomers < 0) {
                        throw new IllegalArgumentException("Количество запрашиваемых пассивных покупателей " +
                                "должно быть больше 0");
                    }
                    criteriya = new BadCustomers(countBadCustomers);
                } else {
                    throw new RuntimeException("Неверный формат одного из критериев (полей) во входном файле " +
                            inputFilePath);
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Ошибка преобразования значения поля входного файла " +
                        inputFilePath + " в целое число");
            }
            searchRequest.getCriteriyas().add(criteriya);
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
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Ошибка преобразования json содержимого файла " + inputFilePath + " в объект");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        JsonNode startDateJson = jsonData.get("startDate");
        if (startDateJson == null) {
            throw new RuntimeException("Тип операции в параметрах запуска не соответствует типу входного файла "
                    + inputFilePath);
        }
        String startDateString = startDateJson.asText();
        JsonNode endDateJson = jsonData.get("endDate");
        String endDateString = endDateJson.asText();
        try {
            statRequest.setStartDate(LocalDate.parse(startDateString, formatter));
            statRequest.setEndDate(LocalDate.parse(endDateString, formatter));
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
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
