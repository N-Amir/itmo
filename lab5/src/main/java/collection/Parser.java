package collection;

import error.IncorrectCollectionException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Класс для сериализации/десериализации коллекции в/из XML.
 * Чтение — через {@link FileReader}, запись — через {@link PrintWriter}.
 */
public final class Parser {
    private static String fileName;

    private Parser() {
    }

    /**
     * Сохраняет коллекцию в XML-файл, из которого она была загружена.
     *
     * @param collectionManager менеджер коллекции
     */
    public static void saveToXml(CollectionManager collectionManager) {
        if (fileName == null) {
            System.out.println("Имя файла не задано, сохранение невозможно");
            return;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, StandardCharsets.UTF_8))) {
            JAXBContext jaxbContext = JAXBContext.newInstance(CollectionManager.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(collectionManager, writer);
            System.out.println("Коллекция сохранена в файл " + fileName);
        } catch (JAXBException | IOException e) {
            System.out.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    /**
     * Загружает коллекцию из XML-файла и проверяет её.
     *
     * @param fileName путь к файлу
     * @return менеджер коллекции или null, если файл недоступен или данные неверны
     */
    public static CollectionManager loadFromXml(String fileName) {
        Parser.fileName = fileName;
        try (FileReader reader = new FileReader(fileName, StandardCharsets.UTF_8)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(CollectionManager.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            CollectionManager manager = (CollectionManager) unmarshaller.unmarshal(reader);
            manager.checkCollection();
            return manager;
        } catch (IncorrectCollectionException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (JAXBException e) {
            for (Throwable t = e; t != null; t = t.getCause()) {
                if (t instanceof IncorrectCollectionException) {
                    System.out.println(t.getMessage());
                    return null;
                }
            }
            System.out.println("С файлом что-то не так, либо он пуст. В коллекции нет исходных данных");
            return new CollectionManager();
        } catch (FileNotFoundException e) {
            System.out.println("Указанный файл не найден");
            return null;
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
            return null;
        }
    }
}
