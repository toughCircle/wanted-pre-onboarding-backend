package toughcircle.wanted_pre_onboarding_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toughcircle.wanted_pre_onboarding_backend.model.dto.AddPostRequest;
import toughcircle.wanted_pre_onboarding_backend.model.dto.PostDetailResponse;
import toughcircle.wanted_pre_onboarding_backend.model.dto.PostListResponse;
import toughcircle.wanted_pre_onboarding_backend.model.dto.UpdatePostRequest;
import toughcircle.wanted_pre_onboarding_backend.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    // 채용공고 등록
    @PostMapping
    private void createPost(@RequestBody AddPostRequest request) {

        postService.createPost(request);
    }

    // 채용공고 수정
    @PatchMapping("/{postId}")
    private void updatePost(@PathVariable("postId") Long postId, @RequestBody UpdatePostRequest request) {

        postService.updatePost(postId, request);
    }

    // 채용공고 삭제
    @DeleteMapping("/{postId}")
    private void deletePost(@PathVariable("postId") Long postId) {

        postService.deletePost(postId);
    }

    // 모든 채용공고 리스트 조회
    @GetMapping
    private PostListResponse getPostList() {

        return postService.getPosts();
    }

    // 채용공고 리스트 조회 [검색]
    @GetMapping
    private PostListResponse searchPosts(@RequestParam("search") String keyword) {

        return postService.searchPosts(keyword);
    }

    // 채용공고 상세 페이지 조회
    @GetMapping("/{postId}")
    private PostDetailResponse getPost(@PathVariable("postId") Long postId) {

        return postService.getPost(postId);
    }

    // 채용공고 지원
    @PostMapping("/{postId}/submit/{userId}")
    private void submitPost(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {

        postService.submitJobApplication(postId, userId);
    }
}
