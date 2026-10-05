package command;

import collection.CollectionManager;
import error.InvalidInputException;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

/**
 * Класс, управляющий командами.
 */
public class CommandManager {
    private static boolean isWorking = true;
    private static HashMap<String, BaseCommand> commandMap = new HashMap<>();
    private static String fileLink;
    private static final Scanner SCANNER = new Scanner(System.in);

    public CommandManager(CollectionManager collection) {
        commandMap = new HashMap<>();
        commandMap.put("help", new HelpCommand());
        commandMap.put("info", new InfoCommand(collection));
        commandMap.put("show", new ShowCommand(collection));
        commandMap.put("insert", new InsertCommand(collection));
        commandMap.put("update", new UpdateIdCommand(collection));
        commandMap.put("remove_key", new RemoveKeyCommand(collection));
        commandMap.put("clear", new ClearCommand(collection));
        commandMap.put("save", new SaveCommand(collection));
        commandMap.put("execute_script", new ExecuteScriptCommand(collection));
        commandMap.put("exit", new ExitCommand());
        commandMap.put("remove_greater", new RemoveGreaterCommand(collection));
        commandMap.put("remove_lower", new RemoveLowerCommand(collection));
        commandMap.put("remove_greater_key", new RemoveGreaterKeyCommand(collection));
        commandMap.put("count_greater_than_telephone_code", new CountGreaterThanTelephoneCodeCommand(collection));
        commandMap.put("print_field_ascending_telephone_code", new PrintFieldAscendingTelephoneCodeCommand(collection));
        commandMap.put("print_field_descending_telephone_code", new PrintFieldDescendingTelephoneCodeCommand(collection));
    }

    public static HashMap<String, BaseCommand> getCommandMap() {
        return commandMap;
    }

    /**
     * Читает и выполняет одну команду пользователя.
     */
    public static void existCommand() {
        try {
            System.out.println("Введите команду: ");
            String command = SCANNER.nextLine().trim();
            if (command.isEmpty()) return;

            String[] commandArg = command.split("\\s+");
            String argument = commandArg.length == 2 ? commandArg[1] : null;

            BaseCommand cmd = commandMap.get(commandArg[0]);
            if (cmd != null) {
                cmd.setArgument(argument);
                cmd.execute(commandArg);
            } else {
                System.out.println("Команды " + commandArg[0] + " не существует");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Завершение работы...");
            isWorking = false;
            System.exit(0);
        } catch (JAXBException | IOException | InvalidInputException e) {
            System.out.println("Ошибка выполнения команды: " + e.getMessage());
        }
    }

    public static boolean getWork() {
        return isWorking;
    }

    public static String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        CommandManager.fileLink = fileLink;
    }
}
