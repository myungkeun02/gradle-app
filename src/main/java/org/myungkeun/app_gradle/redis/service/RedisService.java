package org.myungkeun.app_gradle.redis.service;

import java.time.Duration;


public interface RedisService {
    void setValues(String key, String value, Duration duration);
    String getValue(String key);
    void delValue(String key);
}
