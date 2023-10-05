package logic;

import dto.response.ResponseType;

public class CliArgs {
    private ResponseType type;
    private String inputFilePath;
    private String outputFilePath;
    private String subFolderToOutputFile; //    Может не понадобиться.

    public CliArgs() {
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public String getSubFolderToOutputFile() {
        return subFolderToOutputFile;
    }

    public void setSubFolderToOutputFile(String subFolderToOutputFile) {
        this.subFolderToOutputFile = subFolderToOutputFile;
    }

    @Override
    public String toString() {
        return "CliArgs{" +
                "type=" + type +
                ", inputFilePath='" + inputFilePath + '\'' +
                ", outputFilePath='" + outputFilePath + '\'' +
                ", subFolderToOutputFile='" + subFolderToOutputFile + '\'' +
                '}';
    }
}
