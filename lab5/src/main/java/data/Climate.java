package data;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * Перечисление климатических зон города.
 */
@XmlEnum(String.class)
public enum Climate {
    @XmlEnumValue("rain_forest")
    RAIN_FOREST,
    @XmlEnumValue("humid_subtropical")
    HUMIDSUBTROPICAL,
    @XmlEnumValue("oceanic")
    OCEANIC,
    @XmlEnumValue("desert")
    DESERT
}
