package data;

import error.InvalidInputException;

import jakarta.xml.bind.annotation.*;

/**
 * Класс координат города.
 * x > -132, не может быть null.
 * y > -486.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"x", "y"})
public class Coordinates {
    private Float x;
    private int y;

    public Coordinates() {
    }

    public Coordinates(Float x, int y) {
        if (x == null) {
            throw new InvalidInputException("Значение x не может быть null");
        }
        if (x <= -132) {
            throw new InvalidInputException("Значение x должно быть больше -132");
        }
        if (y <= -486) {
            throw new InvalidInputException("Значение y должно быть больше -486");
        }
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + '}';
    }
}
