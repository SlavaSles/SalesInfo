package logic;

import dto.response.ResponseType;
import errors.ErrorMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ArgsReaderTest {

    @Test
    @DisplayName("Проверка на недостаточное количество аргументов командной строки (меньше 3)")
    void argsParserCliArgsLessThenThree() {
        String cliArgsStr = "input.json output.json";
        String[] cliArgs = cliArgsStr.split(" ");
        ArgsReader argsReader = new ArgsReader(cliArgs);
//        assertThrows(IllegalArgumentException.class, () -> argsReader.argsParser());
        try {
            argsReader.argsParser();
        } catch (Exception e) {
//            assertThat(e.getMessage()).isEqualTo(ErrorMessages.ERROR_MESSAGE_CODE_01);
            assertEquals(ErrorMessages.ERROR_MESSAGE_CODE_01, e.getMessage());
            return;
        }
        fail("Ожидаемое исключение не было выброшено");
    }

    @Test
    @DisplayName("Проверка на превышение количества аргументов командной строки (больше 3)")
    void argsParserCliArgsMoreThenThree() {
        String cliArgsStr = "sadf asdf input.json output.json";
        String[] cliArgs = cliArgsStr.split(" ");
        ArgsReader argsReader = new ArgsReader(cliArgs);
        try {
            argsReader.argsParser();
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo(ErrorMessages.ERROR_MESSAGE_CODE_01);
            return;
        }
        fail("Ожидаемое исключение не было выброшено");
    }

    @Test
    @DisplayName("Проверка на неверное название типа операции")
    void argsParserWrongOperationType() {
        String cliArgsStr = "sadf input.json output.json";
        String[] cliArgs = cliArgsStr.split(" ");
        ArgsReader argsReader = new ArgsReader(cliArgs);
        try {
            argsReader.argsParser();
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo(ErrorMessages.ERROR_MESSAGE_CODE_02);
            return;
        }
        fail("Ожидаемое исключение не было выброшено");
    }

    @Test
    @DisplayName("Проверка на недопустимое название входного файла")
    void argsParserWrongInputFileName() {
        String cliArgsStr = "search inpu&t.abc output.json";
        String[] cliArgs = cliArgsStr.split(" ");
        ArgsReader argsReader = new ArgsReader(cliArgs);
        try {
            argsReader.argsParser();
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo(ErrorMessages.ERROR_MESSAGE_CODE_03);
            return;
        }
        fail("Ожидаемое исключение не было выброшено");
    }

    @Test
    @DisplayName("Проверка на недопустимое название выходного файла")
    void argsParserWrongOutputFileName() {
        String cliArgsStr = "search data/abc-def_ghi.abc output/file/cde_fgh.";
        String[] cliArgs = cliArgsStr.split(" ");
        ArgsReader argsReader = new ArgsReader(cliArgs);
        try {
            argsReader.argsParser();
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo(ErrorMessages.ERROR_MESSAGE_CODE_03);
            return;
        }
        fail("Ожидаемое исключение не было выброшено");
    }

    @Test
    @DisplayName("Проверка написания типа операции в разном регистре и успешного преобразования")
    void argsParserLowerAndUpperCaseOperationType() {
        String cliArgsStr = "seaRCh input.json output.json";
        String[] cliArgs = cliArgsStr.split(" ");
        CliArgs expected = new CliArgs();
        expected.setType(ResponseType.search);
        expected.setInputFilePath("input.json");
        expected.setOutputFilePath("output.json");
        ArgsReader argsReader = new ArgsReader(cliArgs);
        CliArgs actual = argsReader.argsParser();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка успешного преобразования аргументов командной строки в объект")
    void argsParserSuccess() {
        String cliArgsStr = "stat data/abc-def_ghi.abc output/file/cde_fgh.json";
        String[] cliArgs = cliArgsStr.split(" ");
        CliArgs expected = new CliArgs();
        expected.setType(ResponseType.stat);
        expected.setInputFilePath("data/abc-def_ghi.abc");
        expected.setOutputFilePath("output/file/cde_fgh.json");
        ArgsReader argsReader = new ArgsReader(cliArgs);
        CliArgs actual = argsReader.argsParser();
        assertThat(actual).isEqualTo(expected);
    }
}