package command;

import collection.CollectionManager;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * execute_script file_name : считать и исполнить скрипт из указанного файла.
 * Команды записаны так же, как в интерактивном режиме; составные команды
 * (insert, update, remove_greater, remove_lower) сопровождаются 12 строками с полями
 * элемента, где пустая строка означает null.
 */
public class ExecuteScriptCommand extends BaseCommand {
    private static final Set<String> COMPOSITE =
            Set.of("insert", "update", "remove_greater", "remove_lower");
    private static final ArrayList<String> cityList = new ArrayList<>();
    private static final ArrayList<String> filePaths = new ArrayList<>();
    private static boolean flag = false;

    public ExecuteScriptCommand(CollectionManager collection) {
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
            return;
        }
        String path = args[1];
        if (filePaths.contains(path)) {
            System.out.println("Файл содержит рекурсию");
            return;
        }
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Файл не найден или не может быть прочитан");
            return;
        }

        HashMap<String, BaseCommand> commandMap = CommandManager.getCommandMap();
        filePaths.add(path);
        flag = true;
        try {
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                BaseCommand cmd = commandMap.get(parts[0]);
                if (cmd == null) {
                    System.out.println("Команды " + parts[0] + " не существует");
                    continue;
                }
                if (COMPOSITE.contains(parts[0])) {
                    cityList.clear();
                    for (int j = 1; j <= 12; j++) {
                        cityList.add(i + j < lines.size() ? lines.get(i + j).trim() : "");
                    }
                    i += 12;
                }
                try {
                    cmd.setArgument(parts.length == 2 ? parts[1] : null);
                    cmd.execute(parts);
                } catch (JAXBException | IOException | RuntimeException e) {
                    System.out.println("Ошибка в скрипте: " + e.getMessage());
                }
            }
        } finally {
            filePaths.remove(path);
            if (filePaths.isEmpty()) {
                flag = false;
            }
        }
    }

    public static boolean getFlag() {
        return flag;
    }

    public static ArrayList<String> getCityList() {
        return cityList;
    }

    @Override
    public void getDescription() {
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла");
    }
}
