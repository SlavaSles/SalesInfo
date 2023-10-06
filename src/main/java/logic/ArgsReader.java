package logic;

import dto.response.ResponseType;
import errors.ErrorMessages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsReader {
    private final String FILENAME_REGEX = "^((\\w+[\\-_\\w]*/)*\\w+[\\-_\\w]*\\.\\w+)$";
    private String[] args;
    private CliArgs cliArgs;

    public ArgsReader(String[] args) {
        this.args = args;
        this.cliArgs = new CliArgs();
    }

    public CliArgs argsParser() throws IllegalArgumentException  {
        String errorMessage = "";
        if (args.length != 3) {
            errorMessage = ErrorMessages.ERROR_MESSAGE_CODE_01;
            throw new IllegalArgumentException(errorMessage);
        }
        args[0] = args[0].toLowerCase();
        switch (args[0]) {
            case "search":
                cliArgs.setType(ResponseType.search);
                break;
            case "stat":
                cliArgs.setType(ResponseType.stat);
                break;
            default:
                errorMessage = ErrorMessages.ERROR_MESSAGE_CODE_02;
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
                }
            } else {
                errorMessage = ErrorMessages.ERROR_MESSAGE_CODE_03;
                throw new IllegalArgumentException(errorMessage);
            }
        }
        return cliArgs;
    }
}
