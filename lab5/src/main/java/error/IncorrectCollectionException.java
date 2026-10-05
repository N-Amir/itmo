package error;

/**
 * Исключение, выбрасываемое при некорректных данных в исходной коллекции.
 */
public class IncorrectCollectionException extends RuntimeException {
    public IncorrectCollectionException(String message) {
        super(message);
    }
}
