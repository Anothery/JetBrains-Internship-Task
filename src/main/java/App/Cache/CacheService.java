package App.Cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

/**
 * CacheService создает новый кэш (при его отсутствии) на основе конф. файла ehcache.xml
 * и позволяет взаимодействовать с ним с помощью методов get/put
 */

public class CacheService {
    private static CacheService instance = new CacheService();
    private final Cache<String, String> answersCache;

    public static CacheService getInstance(){
        return instance;
    }

    private CacheService(){
        XmlConfiguration xmlConfig = new XmlConfiguration(CacheService.class.getResource("/ehcache.xml"));
        CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        cacheManager.init();
        answersCache = cacheManager.getCache("answersCache", String.class, String.class);
    }

    public String get(String id) {
        return answersCache.get(id);
    }

    public void put(String id, String value) {answersCache.put(id, value);
    }

}