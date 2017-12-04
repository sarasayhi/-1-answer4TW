package com.Marissa.FAQ.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil<T> {
  /*  @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ValueOperations valueOperations;

    public void add(String key, T entity) {
        add(key, entity, 3600L);
    }

    public void addList(String key, List<T> list) {
        addList(key, list, 3600L);
    }

    public void add(String key, T entity, Long time) {
        try {
            valueOperations.set(key, JSON.toJSONString(entity), time, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addList(String key, List<T> list, Long time) {
        try {
            valueOperations.set(key, JSON.toJSONString(list), time, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T get(String key, Class<T> c) {
        try {
            T entity = null;
            String json = (String) valueOperations.get(key);
//            if (StringUtils.isNotEmpty(json)) {
//                entity = JSON.parseObject(json, c);
//            }
            return entity;
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getList(String key, Class<T> c) {
        try {
            List<T> list = null;
            String json = (String) valueOperations.get(key);
//            if (StringUtils.isNotEmpty(json)) {
//                list = JSONObject.parseArray(json, c);
//            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(String key) {
        try {
            if (key.contains("*")) {
                Set<String> set = redisTemplate.keys(key);
                Iterator<String> it = set.iterator();
                while (it.hasNext())
                    redisTemplate.delete(it.next());
            } else
                redisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}