package uk.gov.justice.digital.common;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Utils {

    public final static String SECRET_ID_KEY = "secretId";

    public static Optional<String> getOptionalString(Map<String, Object> event, String key) {
        return Optional.ofNullable(event.get(key)).map(obj -> (String) obj);
    }

    @SuppressWarnings({"unchecked", "unused"})
    public static Map<String, String> getConfig(Map<String, Object> event, String key) {
        return (Map<String, String>) event.getOrDefault(key, Collections.emptyMap());
    }

    @SuppressWarnings({"unused"})
    public static <T, O> T getOrThrow(Map<String, O> obj, String key, Class<T> type) {
        return Optional.ofNullable(type.cast(obj.get(key)))
                .orElseThrow(() -> new NoSuchElementException("Required key [" + key + "] is missing"));
    }

    private Utils() { }
}
