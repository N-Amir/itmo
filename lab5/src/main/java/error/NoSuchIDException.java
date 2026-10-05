package error;

/**
 * Исключение, выбрасываемое при отсутствии элемента с заданным id.
 */
public class NoSuchIDException extends RuntimeException {
    public NoSuchIDException(String message) {
        super(message);
    }
}
