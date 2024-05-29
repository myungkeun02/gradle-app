package org.myungkeun.app_gradle;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {
    private final PostRepository postRepository;

    @GetMapping
    public String getApiTest() {
        return "test";
    }

    @PostMapping
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
}
