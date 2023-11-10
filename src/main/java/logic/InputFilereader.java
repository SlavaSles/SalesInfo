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

/**
 * Класс, который выполняет чтение запроса из входного файла
 */
public class InputFilereader {

    /**
     * Поле - имя входного файла
     */
    private String inputFilePath;

    /**
     * Поле - тип ответа
     */
    private ResponseType type;

    /**
     * Конструктор класса
     * @param cliArgs объект, в котором содержатся аргументы командной строки
     */
    public InputFilereader(CliArgs cliArgs) {
        this.inputFilePath = cliArgs.getInputFilePath();
        this.type = cliArgs.getType();
    }

    /**
     * Функция, в которой выполняется чтение входного файла и формирование запроса
     * @return возвращает объект запроса {@link Request}
     * @throws RuntimeException Исключение выбрасывается:
     * <p> - при возникновении ошибок чтения входного файла;</p>
     * <p> - при его преобразовании в формат JSON;</p>
     * <p> - при не соответствии содержимого файла спецификации API;</p>
     * <p> - при не соответствии значений параметров запроса требованиям</p>
     */
    public Request getRequest() throws RuntimeException{
        String jsonFile = readFile();
        Request request;
        if (type == ResponseType.search) {
            request = parseSearchRequest(jsonFile);
        } else {
            request = parseStatRequest(jsonFile);
        }
        return request;
    }

    private String readFile() {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))){
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
     * При парсинге названия полей JSON файла должны четко соответствовать спецификации.
     * Если во входном файле в каком-либо из критериев будут указаны некорректные данные,
     * то приложение выдаст сообщение об ошибке!
     */
    private SearchRequest parseSearchRequest(String jsonFile) {
        SearchRequest searchRequest = new SearchRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonData;
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
            Criteriya criteriya;
            try {
                criteriya = findCriteria(criteriaNode);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_13 + inputFilePath);
            }
            searchRequest.getCriteriyas().add(criteriya);
        }
        return searchRequest;
    }

    private Criteriya findCriteria(JsonNode criteriaNode) throws RuntimeException {
        String textCriteria = criteriaNode.toString();
        if (textCriteria.contains("lastName")) {
            return new Lastname(criteriaNode.get("lastName").asText());
        } else if (textCriteria.contains("productName")) {
            int minTimes = Integer.parseInt(criteriaNode.get("minTimes").asText());
            if (minTimes < 1) {
                throw new IllegalArgumentException(ErrorMessages.ERROR_MESSAGE_CODE_08);
            }
            return new Product(criteriaNode.get("productName").asText(), minTimes);
        } else if (textCriteria.contains("minExpenses")) {
            int minExpenses = Integer.parseInt(criteriaNode.get("minExpenses").asText());
            int maxExpenses = Integer.parseInt(criteriaNode.get("maxExpenses").asText());
            Criteriya criteriya = new ExpensesRange(minExpenses, maxExpenses);
            if (minExpenses < 0 || maxExpenses < 0) {
                throw new IllegalArgumentException(ErrorMessages.ERROR_MESSAGE_CODE_09);
            } else if (minExpenses > maxExpenses) {
                throw new IllegalArgumentException(ErrorMessages.ERROR_MESSAGE_CODE_10);
            }
            return criteriya;
        } else if (textCriteria.contains("badCustomers")) {
            int countBadCustomers = Integer.parseInt(criteriaNode.get("badCustomers").asText());
            if (countBadCustomers < 1) {
                throw new IllegalArgumentException(ErrorMessages.ERROR_MESSAGE_CODE_11);
            }
            return new BadCustomers(countBadCustomers);
        } else {
            throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_12 + inputFilePath);
        }
    }

    /**
     * При парсинге названия полей JSON файла должны четко соответствовать спецификации
     */
    private StatRequest parseStatRequest(String jsonFile) {
        StatRequest statRequest = new StatRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonData;
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
