package collection;

import data.City;
import data.Climate;
import data.Coordinates;
import data.Human;

/**
 * Класс для проверки корректности полей объектов.
 */
public class Validator {

    public boolean checkId(Integer id) {
        return id != null && id > 0;
    }

    public boolean checkName(String name) {
        return name != null && !name.isBlank();
    }

    public boolean checkCoordinates(Coordinates coordinates) {
        if (coordinates == null) return false;
        if (coordinates.getX() == null || coordinates.getX() <= -132) return false;
        return coordinates.getY() > -486;
    }

    public boolean checkArea(Long area) {
        return area != null && area > 0;
    }

    public boolean checkPopulation(Integer population) {
        return population != null && population > 0;
    }

    public boolean checkPopulationDensity(int populationDensity) {
        return populationDensity > 0;
    }

    public boolean checkTelephoneCode(Integer telephoneCode) {
        return telephoneCode != null && telephoneCode > 0 && telephoneCode <= 100000;
    }

    public boolean checkClimate(Climate climate) {
        return climate != null;
    }

    public boolean checkGovernor(Human governor) {
        if (governor == null) return true;
        return governor.getAge() > 0 && governor.getHeight() != null && governor.getHeight() > 0;
    }

    public boolean checkCity(City city) {
        if (city == null) return false;
        return checkId(city.getId())
                && checkName(city.getName())
                && checkCoordinates(city.getCoordinates())
                && city.getCreationDate() != null
                && checkArea(city.getArea())
                && checkPopulation(city.getPopulation())
                && checkPopulationDensity(city.getPopulationDensity())
                && checkTelephoneCode(city.getTelephoneCode())
                && checkClimate(city.getClimate())
                && checkGovernor(city.getGovernor());
    }
}
