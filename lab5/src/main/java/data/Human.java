package data;

import error.InvalidInputException;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

/**
 * Класс правителя города.
 * age > 0, height > 0, birthday может быть null.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Human {
    private int age;
    private Float height;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime birthday;

    public Human() {
    }

    public Human(int age, Float height, LocalDateTime birthday) {
        if (age <= 0) {
            throw new InvalidInputException("Возраст должен быть больше 0");
        }
        if (height == null) {
            throw new InvalidInputException("Рост не может быть null");
        }
        if (height <= 0) {
            throw new InvalidInputException("Рост должен быть больше 0");
        }
        this.age = age;
        this.height = height;
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public Float getHeight() {
        return height;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Human{age=" + age + ", height=" + height + ", birthday=" + birthday + '}';
    }
}
