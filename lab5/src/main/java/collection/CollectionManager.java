package collection;

import data.City;
import data.LocalDateAdapter;
import error.IncorrectCollectionException;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.*;

/**
 * Класс, управляющий коллекцией {@link LinkedHashMap}.
 * Ключ — Integer, значение — {@link City}.
 */
@XmlRootElement(name = "collectionManager")
@XmlSeeAlso({City.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class CollectionManager implements ICollectionManager {

    @XmlElement(name = "entries")
    @XmlJavaTypeAdapter(CityMapAdapter.class)
    private LinkedHashMap<Integer, City> collection = new LinkedHashMap<>();

    @XmlElement(name = "creation_date_collection")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate date = LocalDate.now();

    @XmlTransient
    private final CollectionUtil collectionUtil = new CollectionUtil();

    public CollectionManager() {
    }

    public CollectionManager(LinkedHashMap<Integer, City> collection) {
        this.collection = collection;
    }

    public LinkedHashMap<Integer, City> getCollection() {
        return collection;
    }

    public void setCollection(LinkedHashMap<Integer, City> collection) {
        this.collection = collection;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public void info() {
        System.out.println("Тип коллекции: " + collection.getClass().getSimpleName());
        System.out.println("Дата инициализации: " + date);
        System.out.println("Количество элементов: " + collection.size());
    }

    @Override
    public void show() {
        if (collection.isEmpty()) {
            System.out.println("В коллекции нет объектов, доступных для просмотра");
        } else {
            collection.forEach((key, city) -> {
                System.out.println("Ключ: " + key);
                CollectionUtil.display(city);
            });
        }
    }

    @Override
    public void insert(Integer key, City city) {
        if (city == null) {
            System.out.println("Элемент не создан, команда не выполнена");
            return;
        }
        if (collection.containsKey(key)) {
            System.out.println("Элемент с таким ключом уже существует");
            return;
        }
        city.setId(GenerationID.generateID());
        collection.put(key, city);
        System.out.println("Команда выполнена");
    }

    @Override
    public void updateId(Integer id, City newCity) {
        if (newCity == null) {
            System.out.println("Элемент не создан, команда не выполнена");
            return;
        }
        for (City city : collection.values()) {
            if (city.getId().equals(id)) {
                city.setName(newCity.getName());
                city.setCoordinates(newCity.getCoordinates());
                city.setArea(newCity.getArea());
                city.setPopulation(newCity.getPopulation());
                city.setMetersAboveSeaLevel(newCity.getMetersAboveSeaLevel());
                city.setPopulationDensity(newCity.getPopulationDensity());
                city.setTelephoneCode(newCity.getTelephoneCode());
                city.setClimate(newCity.getClimate());
                city.setGovernor(newCity.getGovernor());
                System.out.println("Команда выполнена");
                return;
            }
        }
        System.out.println("Элемента с таким id не существует");
    }

    @Override
    public void removeKey(Integer key) {
        if (collection.remove(key) != null) {
            System.out.println("Элемент удалён из коллекции");
        } else {
            System.out.println("Элемента с таким ключом не существует");
        }
    }

    @Override
    public void clear() {
        collection.clear();
        System.out.println("Коллекция очищена");
    }

    @Override
    public void removeGreater(City city) {
        if (city == null) {
            System.out.println("Элемент не создан, команда не выполнена");
            return;
        }
        boolean removed = collection.values().removeIf(c -> c.compareTo(city) > 0);
        System.out.println(removed ? "Элементы удалены" : "Нет элементов, превышающих заданный");
    }

    @Override
    public void removeLower(City city) {
        if (city == null) {
            System.out.println("Элемент не создан, команда не выполнена");
            return;
        }
        boolean removed = collection.values().removeIf(c -> c.compareTo(city) < 0);
        System.out.println(removed ? "Элементы удалены" : "Нет элементов, меньших заданного");
    }

    @Override
    public void removeGreaterKey(Integer key) {
        boolean removed = collection.keySet().removeIf(k -> k > key);
        System.out.println(removed ? "Элементы удалены" : "Нет элементов с ключом больше заданного");
    }

    @Override
    public void countGreaterThanTelephoneCode(Integer telephoneCode) {
        long count = collection.values().stream()
                .filter(c -> c.getTelephoneCode() > telephoneCode)
                .count();
        System.out.println("Количество элементов: " + count);
    }

    @Override
    public void printFieldAscendingTelephoneCode() {
        List<Integer> codes = new ArrayList<>();
        for (City city : collection.values()) {
            codes.add(city.getTelephoneCode());
        }
        Collections.sort(codes);
        System.out.println(codes);
    }

    @Override
    public void printFieldDescendingTelephoneCode() {
        List<Integer> codes = new ArrayList<>();
        for (City city : collection.values()) {
            codes.add(city.getTelephoneCode());
        }
        codes.sort(Collections.reverseOrder());
        System.out.println(codes);
    }

    /**
     * Проверяет корректность всех элементов коллекции (поля, уникальность id)
     * и регистрирует их id в генераторе.
     *
     * @throws IncorrectCollectionException если данные некорректны
     */
    public void checkCollection() {
        if (collection == null) {
            collection = new LinkedHashMap<>();
        }
        if (date == null) {
            date = LocalDate.now();
        }
        Set<Integer> ids = new HashSet<>();
        for (City city : collection.values()) {
            if (!collectionUtil.checkIfCorrect(city) || !ids.add(city.getId())) {
                throw new IncorrectCollectionException(
                        "Исходные данные в коллекции неверны, исправьте файл и попробуйте ещё раз");
            }
        }
        ids.forEach(GenerationID::register);
    }
}
