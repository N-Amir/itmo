package collection;

import data.City;

/**
 * Вспомогательный класс для работы с элементами коллекции.
 */
public class CollectionUtil {
    private static final Validator validator = new Validator();

    /**
     * Выводит информацию о городе.
     *
     * @param city город
     */
    public static void display(City city) {
        System.out.println("ID: " + city.getId());
        System.out.println("Название: " + city.getName());
        System.out.println("Координаты: x=" + city.getCoordinates().getX()
                + ", y=" + city.getCoordinates().getY());
        System.out.println("Дата создания: " + city.getCreationDate());
        System.out.println("Площадь: " + city.getArea());
        System.out.println("Население: " + city.getPopulation());
        System.out.println("Высота над уровнем моря: " + city.getMetersAboveSeaLevel());
        System.out.println("Плотность населения: " + city.getPopulationDensity());
        System.out.println("Телефонный код: " + city.getTelephoneCode());
        System.out.println("Климат: " + city.getClimate());
        System.out.println("Правитель: " + city.getGovernor());
        System.out.println("__________________________\n");
    }

    /**
     * Проверяет корректность города.
     *
     * @param city город
     * @return true, если город корректен
     */
    public boolean checkIfCorrect(City city) {
        return validator.checkCity(city);
    }
}
