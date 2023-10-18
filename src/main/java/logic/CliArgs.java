package logic;

import dto.response.ResponseType;

import java.util.Objects;

/**
 * Класс, в котором содержатся аргументы командной строки в виде объекта
 */
public class CliArgs {

    /**
     * Поле - тип ответа Enum (search, stat, error) {@link ResponseType}
     */
    private ResponseType type;

    /**
     * Поле - имя входного файла
     */
    private String inputFilePath;

    /**
     * Поле - имя выходного файла
     */
    private String outputFilePath;

    /**
     * Конструктор класса
     */
    public CliArgs() {
    }

    /**
     * Функция получения типа ответа
     * @return возвращает тип ответа
     */
    public ResponseType getType() {
        return type;
    }

    /**
     * Процедура присвоения типа ответа
     * @param type тип ответа, определяемый из аргументов командной строки
     */
    public void setType(ResponseType type) {
        this.type = type;
    }

    /**
     * Функция получения имени входного файла
     * @return возвращает имя входного файла
     */
    public String getInputFilePath() {
        return inputFilePath;
    }

    /**
     * Процедура присвоения имени входного файла
     * @param inputFilePath имя входного файла, определяемое из аргументов командной строки
     */
    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    /**
     * Функция получения имени выходного файла
     * @return возвращает имя выходного файла
     */
    public String getOutputFilePath() {
        return outputFilePath;
    }

    /**
     * Процедура присвоения имени выходного файла
     * @param outputFilePath имя выходного файла, определяемое из аргументов командной строки
     */
    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    /**
     * Функция преобразования полей объекта в строку
     * @return возвращает содержимое объекта в виде строки
     */
    @Override
    public String toString() {
        return "CliArgs{" +
                "type=" + type +
                ", inputFilePath='" + inputFilePath + '\'' +
                ", outputFilePath='" + outputFilePath + '\'' +
                '}';
    }

    /**
     * Проверка на равенство с аналогичным объектом для выполнения автотестов
     * @param o объект, в котором содержатся аргументы командной строки
     * @return true, если все поля объектов равны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CliArgs)) return false;
        CliArgs cliArgs = (CliArgs) o;
        return type == cliArgs.type && Objects.equals(inputFilePath, cliArgs.inputFilePath) && Objects.equals(outputFilePath, cliArgs.outputFilePath);
    }

    /**
     * Расчет hashCode объекта
     * @return возвращает значение hashCode объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, inputFilePath, outputFilePath);
    }
}
