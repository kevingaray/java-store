package StepsDefinitions;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalContext {
    private static final ThreadLocal<Map<String, Object>> threadLocalMap = ThreadLocal.withInitial(HashMap::new);

    public static void set(String key, Object value) {
        threadLocalMap.get().put(key, value);
    }

    public static Object get(String key) {
        return threadLocalMap.get().get(key);
    }

    public static void remove() {
        threadLocalMap.remove();
    }
}