package data;

import error.InvalidInputException;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Класс города, хранимый в коллекции.
 * <ul>
 *     <li>id — не null, &gt; 0, уникальный, генерируется автоматически при добавлении в коллекцию</li>
 *     <li>name — не null, не пустая строка</li>
 *     <li>coordinates — не null</li>
 *     <li>creationDate — не null, генерируется автоматически</li>
 *     <li>area — не null, &gt; 0</li>
 *     <li>population — не null, &gt; 0</li>
 *     <li>metersAboveSeaLevel — может быть null</li>
 *     <li>populationDensity — &gt; 0</li>
 *     <li>telephoneCode — не null, &gt; 0, ≤ 100000</li>
 *     <li>climate — не null</li>
 *     <li>governor — может быть null</li>
 * </ul>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class City implements Comparable<City> {
    private Integer id;
    private String name;
    private Coordinates coordinates;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate creationDate;

    private Long area;
    private Integer population;
    private Double metersAboveSeaLevel;
    private int populationDensity;
    private Integer telephoneCode;
    private Climate climate;
    private Human governor;

    public City() {
    }

    public City(String name, Coordinates coordinates, Long area, Integer population,
                Double metersAboveSeaLevel, int populationDensity, Integer telephoneCode,
                Climate climate, Human governor) {
        if (name == null || name.isBlank()) {
            throw new InvalidInputException("Имя не может быть пустым");
        }
        if (coordinates == null) {
            throw new InvalidInputException("Координаты не могут быть null");
        }
        if (area == null || area <= 0) {
            throw new InvalidInputException("Площадь должна быть больше 0");
        }
        if (population == null || population <= 0) {
            throw new InvalidInputException("Население должно быть больше 0");
        }
        if (populationDensity <= 0) {
            throw new InvalidInputException("Плотность населения должна быть больше 0");
        }
        if (telephoneCode == null || telephoneCode <= 0 || telephoneCode > 100000) {
            throw new InvalidInputException("Телефонный код должен быть в диапазоне (0; 100000]");
        }
        if (climate == null) {
            throw new InvalidInputException("Климат не может быть null");
        }
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.populationDensity = populationDensity;
        this.telephoneCode = telephoneCode;
        this.climate = climate;
        this.governor = governor;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Coordinates getCoordinates() { return coordinates; }
    public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }
    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
    public Long getArea() { return area; }
    public void setArea(Long area) { this.area = area; }
    public Integer getPopulation() { return population; }
    public void setPopulation(Integer population) { this.population = population; }
    public Double getMetersAboveSeaLevel() { return metersAboveSeaLevel; }
    public void setMetersAboveSeaLevel(Double metersAboveSeaLevel) { this.metersAboveSeaLevel = metersAboveSeaLevel; }
    public int getPopulationDensity() { return populationDensity; }
    public void setPopulationDensity(int populationDensity) { this.populationDensity = populationDensity; }
    public Integer getTelephoneCode() { return telephoneCode; }
    public void setTelephoneCode(Integer telephoneCode) { this.telephoneCode = telephoneCode; }
    public Climate getClimate() { return climate; }
    public void setClimate(Climate climate) { this.climate = climate; }
    public Human getGovernor() { return governor; }
    public void setGovernor(Human governor) { this.governor = governor; }

    /**
     * Сортировка по умолчанию — по возрастанию населения, затем по площади.
     * (Сравнение по id не подходит: у нового, ещё не добавленного элемента id ещё нет.)
     */
    @Override
    public int compareTo(City o) {
        int cmp = Integer.compare(this.population, o.population);
        return cmp != 0 ? cmp : Long.compare(this.area, o.area);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", area=" + area +
                ", population=" + population +
                ", metersAboveSeaLevel=" + metersAboveSeaLevel +
                ", populationDensity=" + populationDensity +
                ", telephoneCode=" + telephoneCode +
                ", climate=" + climate +
                ", governor=" + governor +
                '}';
    }
}
