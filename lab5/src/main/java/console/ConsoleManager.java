package console;

import collection.CollectionManager;
import command.CommandManager;

import java.io.File;
import java.util.Scanner;

import static collection.Parser.loadFromXml;

/**
 * Класс для работы с консолью. Использует один общий {@link Scanner} на {@code System.in},
 * чтобы не терять буферизованный ввод.
 */
public class ConsoleManager implements ReaderWriter {
    private static final Scanner SCANNER = new Scanner(System.in);

    public ConsoleManager() {
    }

    @Override
    public Long readLong() {
        return Long.valueOf(readLine());
    }

    @Override
    public String readLine() {
        return SCANNER.nextLine().trim();
    }

    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }

    @Override
    public void write(String text) {
        System.out.print(text);
    }

    @Override
    public String getValidatedValue(String message) {
        write(message);
        while (true) {
            String userInput = readLine();
            if (!userInput.isBlank()) {
                return userInput;
            }
        }
    }

    /**
     * Запрашивает у пользователя имя файла до тех пор, пока не будет загружена корректная коллекция.
     */
    public void fileRead() {
        while (true) {
            System.out.println("Введите название файла:");
            String path = readLine();
            File file = new File(path);
            if (!file.exists() || file.isDirectory()) {
                System.out.println("Файл не найден, попробуйте снова");
                continue;
            }
            CollectionManager collectionManager = loadFromXml(path);
            if (collectionManager == null) {
                continue;
            }
            new CommandManager(collectionManager).setFileLink(path);
            return;
        }
    }
}
