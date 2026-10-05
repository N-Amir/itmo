package data;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

/**
 * Адаптер для маршалинга/анмаршалинга {@link LocalDateTime} в XML.
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    @Override
    public LocalDateTime unmarshal(String v) {
        return v == null ? null : LocalDateTime.parse(v.trim());
    }

    @Override
    public String marshal(LocalDateTime v) {
        return v == null ? null : v.toString();
    }
}
