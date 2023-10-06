package dto.response;

/**
 * Значения Enum в перечислении указаны строчными буквами, чтобы соответствовать требуемому регистру ответа.
 * Можно было пойти другим путем:
 * 1. Не использовать Enum в программе, а указывать напрямую типы ответов в нужном формате в классах Response.
 * 2. Изменять регистр полей после их конвертации в JSON файлы, но это неудобно.
 */

public enum ResponseType {
    search,
    stat,
    error
}
