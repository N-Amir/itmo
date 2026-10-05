import collection.CollectionManager;
import command.CommandManager;
import console.ConsoleManager;

import java.io.File;
import java.util.NoSuchElementException;

import static collection.Parser.loadFromXml;

/**
 * Главный класс приложения.
 */
public class Main {

    public static void main(String[] args) {
        try {
            ConsoleManager consoleManager = new ConsoleManager();

            if (args.length > 0) {
                String filePath = args[0];
                File file = new File(filePath);
                CollectionManager collectionManager = null;
                if (file.exists() && !file.isDirectory()) {
                    collectionManager = loadFromXml(filePath);
                } else {
                    System.out.println("Файл не найден.");
                }
                if (collectionManager != null) {
                    new CommandManager(collectionManager).setFileLink(filePath);
                    System.out.println("Коллекция загружена из файла " + filePath);
                } else {
                    consoleManager.fileRead();
                }
            } else {
                System.out.println("Не передан аргумент командной строки — имя файла.");
                consoleManager.fileRead();
            }

            while (CommandManager.getWork()) {
                CommandManager.existCommand();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Отказываюсь работать в таких условиях, пока!");
        }
    }
}
