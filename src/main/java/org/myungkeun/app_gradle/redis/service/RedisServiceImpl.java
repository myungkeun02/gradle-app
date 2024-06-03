package org.myungkeun.app_gradle.redis.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void setValues(@NonNull final String key,@NonNull final String value,@NonNull final Duration duration) {
        try {
            redisTemplate.opsForValue().set(key, value, duration);
        } catch (Exception e) {
            // 예외 로그 기록
            e.printStackTrace();
        }
    }

    @Override
    public String getValue(String key) {
        try {
            ValueOperations<String, String> values = redisTemplate.opsForValue();
            return (String) values.get(key);
        } catch (Exception e) {
            // 예외 로그 기록
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void delValue(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            // 예외 로그 기록
            e.printStackTrace();
        }
    }
}
