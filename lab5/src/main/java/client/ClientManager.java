package client;

import data.City;
import data.Climate;
import data.Coordinates;
import data.Human;
import error.InvalidInputException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс, создающий объекты {@link City} из консоли или скрипта.
 */
public class ClientManager {
    private final ReadManager readManager = new ReadManager();

    /**
     * Создаёт город, считывая данные из консоли.
     *
     * @return новый город
     */
    public City getCity() {
        String name = readManager.readName();
        Float x = readManager.readCoordinateX();
        int y = readManager.readCoordinateY();
        Long area = readManager.readArea();
        Integer population = readManager.readPopulation();
        Double metersAboveSeaLevel = readManager.readMetersAboveSeaLevel();
        int populationDensity = readManager.readPopulationDensity();
        Integer telephoneCode = readManager.readTelephoneCode();
        Climate climate = readManager.readClimate();
        Human governor = readManager.readGovernor().getHuman();
        return new City(name, new Coordinates(x, y), area, population,
                metersAboveSeaLevel, populationDensity, telephoneCode, climate, governor);
    }

    /**
     * Создаёт город из данных скрипта (12 строк, пустая строка — null).
     * Порядок: name, x, y, area, population, metersAboveSeaLevel, populationDensity,
     * telephoneCode, climate, governor.age, governor.height, governor.birthday.
     *
     * @param data список строк с полями
     * @return новый город или null при ошибке
     */
    public static City createCityFromScript(List<String> data) {
        try {
            if (data.size() != 12) {
                throw new InvalidInputException("Неправильное количество аргументов");
            }
            String name = data.get(0);
            float x = Float.parseFloat(data.get(1));
            int y = Integer.parseInt(data.get(2));
            long area = Long.parseLong(data.get(3));
            int population = Integer.parseInt(data.get(4));
            Double metersAboveSeaLevel = data.get(5).isEmpty() ? null : Double.parseDouble(data.get(5));
            int populationDensity = Integer.parseInt(data.get(6));
            int telephoneCode = Integer.parseInt(data.get(7));
            Climate climate = Climate.valueOf(data.get(8).toUpperCase());

            Human governor = null;
            if (!data.get(9).isEmpty()) {
                int age = Integer.parseInt(data.get(9));
                float height = Float.parseFloat(data.get(10));
                LocalDateTime birthday = data.get(11).isEmpty() ? null
                        : LocalDate.parse(data.get(11)).atStartOfDay();
                governor = new Human(age, height, birthday);
            }

            return new City(name, new Coordinates(x, y), area, population,
                    metersAboveSeaLevel, populationDensity, telephoneCode, climate, governor);
        } catch (RuntimeException e) {
            System.out.println("Неправильно введены данные: " + e.getMessage());
            return null;
        }
    }
}
