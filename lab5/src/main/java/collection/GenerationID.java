package collection;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

/**
 * Класс, генерирующий уникальные id для элементов коллекции.
 * Последний сгенерированный id сохраняется в файл {@code last_id.txt}.
 */
public class GenerationID {
    private static final String fileName = "last_id.txt";
    private static final HashSet<Integer> generatedIDs = new HashSet<>();
    private static int lastId = 0;

    static {
        readLastIDFromFile();
    }

    /**
     * Генерирует уникальный id &gt; 0.
     *
     * @return уникальный id
     */
    public static synchronized Integer generateID() {
        int id = lastId + 1;
        while (generatedIDs.contains(id)) {
            id++;
        }
        generatedIDs.add(id);
        lastId = id;
        saveLastIDToFile(id);
        return id;
    }

    /**
     * Регистрирует id, уже присутствующий в загруженной коллекции,
     * чтобы генератор не выдал его повторно.
     *
     * @param id занятый id
     */
    public static synchronized void register(Integer id) {
        if (id != null && id > 0) {
            generatedIDs.add(id);
            if (id > lastId) {
                lastId = id;
            }
        }
    }

    private static void readLastIDFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String lastIDString = reader.readLine();
            if (lastIDString != null) {
                lastId = Integer.parseInt(lastIDString.trim());
                generatedIDs.add(lastId);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Не удалось прочитать последнее значение ID, начинаем с 0.");
        }
    }

    private static void saveLastIDToFile(Integer id) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, StandardCharsets.UTF_8))) {
            writer.println(id);
        } catch (IOException e) {
            System.err.println("Не удалось сохранить последнее значение ID");
        }
    }
}
