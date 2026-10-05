package console;

/**
 * Интерфейс для чтения/записи в консоль.
 */
public interface ReaderWriter {
    Long readLong();
    String readLine();
    void writeLine(String text);
    void write(String text);
    String getValidatedValue(String message);
}
