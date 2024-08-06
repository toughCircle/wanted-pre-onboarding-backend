package toughcircle.wanted_pre_onboarding_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toughcircle.wanted_pre_onboarding_backend.model.dto.AddPostRequest;
import toughcircle.wanted_pre_onboarding_backend.model.dto.PostDetailResponse;
import toughcircle.wanted_pre_onboarding_backend.model.dto.PostListResponse;
import toughcircle.wanted_pre_onboarding_backend.model.dto.UpdatePostRequest;
import toughcircle.wanted_pre_onboarding_backend.service.PostService;

/**
 * PostController는 채용 공고와 관련된 CRUD 작업을 처리하는 REST 컨트롤러입니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    /**
     * 새로운 채용 공고를 등록합니다.
     *
     * @param request 채용 공고 정보를 포함한 요청 객체
     */
    @PostMapping
    private void createPost(@RequestBody AddPostRequest request) {

        postService.createPost(request);
    }

    /**
     * 기존 채용 공고를 수정합니다.
     *
     * @param postId 수정할 채용 공고의 ID
     * @param request 수정할 내용을 포함한 요청 객체
     */
    @PatchMapping("/{postId}")
    private void updatePost(@PathVariable("postId") Long postId, @RequestBody UpdatePostRequest request) {

        postService.updatePost(postId, request);
    }

    /**
     * 채용 공고를 삭제합니다.
     *
     * @param postId 삭제할 채용 공고의 ID
     */
    @DeleteMapping("/{postId}")
    private void deletePost(@PathVariable("postId") Long postId) {

        postService.deletePost(postId);
    }

    /**
     * 모든 채용 공고 리스트를 조회합니다.
     *
     * @return 모든 채용 공고의 리스트를 포함한 응답 객체
     */
    @GetMapping
    private PostListResponse getPosts() {

        return postService.getPosts();
    }

    /**
     * 키워드를 사용하여 채용 공고를 검색합니다.
     *
     * @param keyword 검색 키워드
     * @return 검색된 채용 공고의 리스트를 포함한 응답 객체
     */
    @GetMapping(params = "keyword")
    private PostListResponse searchPosts(@RequestParam("search") String keyword) {

        return postService.searchPosts(keyword);
    }

    /**
     * 특정 채용 공고의 상세 페이지를 조회합니다.
     *
     * @param postId 조회할 채용 공고의 ID
     * @return 채용 공고의 상세 정보를 포함한 응답 객체
     */
    @GetMapping("/{postId}")
    private PostDetailResponse getPost(@PathVariable("postId") Long postId) {

        return postService.getPost(postId);
    }

    /**
     * 특정 채용 공고에 지원합니다.
     *
     * @param postId 지원할 채용 공고의 ID
     * @param userId 지원하는 사용자의 ID
     */
    @PostMapping("/{postId}/submit/{userId}")
    private void submitPost(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {

        postService.submitJobApplication(postId, userId);
    }
}
