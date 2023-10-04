package logic;

import dto.response.ResponseType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsReader {
    private final String FILENAME_REGEX = "^(((\\w+[\\-_\\w]*/)*)\\w+[\\-_\\w]*\\.\\w+)$";
    private String[] args;
    private CliArgs cliArgs;

    public ArgsReader(String[] args) {
        this.args = args;
        this.cliArgs = new CliArgs();
    }

    public CliArgs argsParser() throws IllegalArgumentException  {
        String errorMessage = "";
        if (args.length != 3) {
            errorMessage = "В параметрах запуска указано неверное количество аргументов";
            throw new IllegalArgumentException(errorMessage);
        }
        args[0] = args[0].toLowerCase();
        switch (args[0]) {
            case "search":
                cliArgs.setType(ResponseType.SEARCH);
                break;
            case "stat":
                cliArgs.setType(ResponseType.STAT);
                break;
            default:
                errorMessage = "В параметрах запуска неверно указан тип операции";
                throw new IllegalArgumentException(errorMessage);
        }
        Pattern pattern = Pattern.compile(FILENAME_REGEX);
        for (int i = 1; i < 3; i++) {
            Matcher matcher = pattern.matcher(args[i]);
            if (matcher.find()) {
                if (cliArgs.getInputFilePath() == null) {
                    cliArgs.setInputFilePath(matcher.group(1).trim());
                } else {
                    cliArgs.setOutputFilePath(matcher.group(1).trim());
                    cliArgs.setSubFolderToOutputFile(matcher.group(2));
                }
            } else {
                errorMessage = "В параметрах запуска неверно указано имя файла";
                throw new IllegalArgumentException(errorMessage);
            }
        }
        return cliArgs;
    }
}
