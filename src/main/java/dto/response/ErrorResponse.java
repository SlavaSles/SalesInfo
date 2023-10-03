package dto.response;

public class ErrorResponse {
    ResponseType type = ResponseType.ERROR;
    String message;

    public ErrorResponse() {
    }

    public ErrorResponse(ResponseType type, String message) {
        this.type = type;
        this.message = message;
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
