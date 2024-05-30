package org.myungkeun.app_gradle;

import lombok.RequiredArgsConstructor;
import org.myungkeun.app_gradle.redis.service.RedisService;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {
    private final PostRepository postRepository;
    private final RedisService redisService;
    @GetMapping
    public String getApiTest() {
        return "test";
    }

    @PostMapping("/db")
    public Post dbPostApiTest(
            @RequestBody PostRequest postRequest
    ) {

        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .description(postRequest.getDescription())
                .build();
        return postRepository.save(post);
    }

    @PostMapping("/redis")
    public Post redisPostApiTest(
            @RequestBody PostRequest postRequest
    ) {
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .description(postRequest.getDescription())
                .build();
        Post request = postRepository.save(post);
        redisService.setValues(String.valueOf(request.getId()), request, Duration.ofMillis(300000));
        return request;
    }
}
