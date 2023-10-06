package dto.response;

public class ErrorResponse extends Response {
    ResponseType type = ResponseType.error;
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
