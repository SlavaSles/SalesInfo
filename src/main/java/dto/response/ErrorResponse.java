package dto.response;

/**
 * Класс, в котором реализована структура ответа в случае возникновения ошибки во время выполнения операций search или
 * stat в соответствие со спецификацией API
 */
public class ErrorResponse extends Response {

    /**
     * Поле - тип ответа "error" {@link ResponseType}
     */
    private ResponseType type = ResponseType.error;

    /**
     * Поле - сообщение об ошибке
     */
    private String message;

    /**
     * Пустой конструктор класса
     */
    public ErrorResponse() {
    }

    /**
     * Конструктор класса с параметрами
     * @param type тип ответа "error"
     * @param message сообщение об ошибке
     */
    public ErrorResponse(ResponseType type, String message) {
        this.type = type;
        this.message = message;
    }

    /**
     * Функция получения типа ответа
     * @return возвращает тип ответа "error"
     */
    public ResponseType getType() {
        return type;
    }

    /**
     * Процедура присвоения типа ответа
     * @param type тип ответа
     */
    public void setType(ResponseType type) {
        this.type = type;
    }

    /**
     * Функция получения сообщения об ошибке
     * @return возвращает сообщение об ошибке
     */
    public String getMessage() {
        return message;
    }

    /**
     * Процедура присвоения сообщения об ошибке
     * @param message сообщение об ошибке
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
