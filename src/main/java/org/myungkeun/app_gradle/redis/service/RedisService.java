package org.myungkeun.app_gradle.redis.service;

import java.time.Duration;


public interface RedisService {
    void setValue(String key, Object value);
    void setValues(String key, Object value, Duration duration);
    String getValue(String key);
    void delValue(String key);
}
