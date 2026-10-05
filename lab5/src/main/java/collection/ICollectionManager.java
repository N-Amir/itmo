package collection;

import data.City;

/**
 * Интерфейс менеджера коллекции.
 */
public interface ICollectionManager {
    void info();
    void show();
    void insert(Integer key, City city);
    void updateId(Integer id, City city);
    void removeKey(Integer key);
    void clear();
    void removeGreater(City city);
    void removeLower(City city);
    void removeGreaterKey(Integer key);
    void countGreaterThanTelephoneCode(Integer telephoneCode);
    void printFieldAscendingTelephoneCode();
    void printFieldDescendingTelephoneCode();
}
