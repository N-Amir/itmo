package collection;

import data.City;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Адаптер, представляющий {@link LinkedHashMap} в XML как список
 * {@code <entry key="..."><city>...</city></entry>}.
 */
public class CityMapAdapter extends XmlAdapter<CityMapAdapter.Entries, LinkedHashMap<Integer, City>> {

    /** Обёртка над списком записей. */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Entries {
        @XmlElement(name = "entry")
        public List<Entry> entry = new ArrayList<>();
    }

    /** Одна запись: ключ + город. */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Entry {
        @XmlAttribute(name = "key")
        public Integer key;

        @XmlElement(name = "city")
        public City city;
    }

    @Override
    public LinkedHashMap<Integer, City> unmarshal(Entries v) {
        LinkedHashMap<Integer, City> map = new LinkedHashMap<>();
        if (v != null && v.entry != null) {
            for (Entry e : v.entry) {
                if (e.key == null || e.city == null || map.containsKey(e.key)) {
                    throw new error.IncorrectCollectionException(
                            "Исходные данные в коллекции неверны (ключ отсутствует или повторяется)");
                }
                map.put(e.key, e.city);
            }
        }
        return map;
    }

    @Override
    public Entries marshal(LinkedHashMap<Integer, City> v) {
        Entries result = new Entries();
        if (v != null) {
            v.forEach((k, c) -> {
                Entry e = new Entry();
                e.key = k;
                e.city = c;
                result.entry.add(e);
            });
        }
        return result;
    }
}
