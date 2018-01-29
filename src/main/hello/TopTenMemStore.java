package hello;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class TopTenMemStore<K,V> extends LinkedHashMap<K,V> {

    private static int cacheSize=100;

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size()>100;
    }
}
