package com.springboot.simple.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

public class CustomCacheManager implements CacheManager {

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisCache<K,V>();
        }
}
