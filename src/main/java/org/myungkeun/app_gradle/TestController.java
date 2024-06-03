package org.myungkeun.app_gradle;

import lombok.RequiredArgsConstructor;
import org.myungkeun.app_gradle.redis.service.RedisService;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {
    private final PostRepository postRepository;
    private final RedisService redisService;
    private static final Logger logger = Logger.getLogger(TestController.class.getName());

    @GetMapping
    public String getApiTest() {
        return "test";
    }

    @PostMapping("/db")
    public Post dbPostApiTest(@RequestBody PostRequest postRequest) {
        try {
            Post post = Post.builder()
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .description(postRequest.getDescription())
                    .build();
            return postRepository.save(post);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/redis")
    public Post redisPostApiTest(@RequestBody PostRequest postRequest) {
        try {
            Post post = Post.builder()
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .description(postRequest.getDescription())
                    .build();
            Post request = postRepository.save(post);
            redisService.setValues(String.valueOf(request.getId()), request.getTitle(), Duration.ofSeconds(30));
            return request;
        } catch (Exception e) {
            logger.severe("redisPostApiTest에서 오류 발생: " + e.getMessage());
            throw e;
        }
    }
}
