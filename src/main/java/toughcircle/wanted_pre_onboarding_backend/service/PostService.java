package toughcircle.wanted_pre_onboarding_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toughcircle.wanted_pre_onboarding_backend.model.dto.*;
import toughcircle.wanted_pre_onboarding_backend.model.entity.Company;
import toughcircle.wanted_pre_onboarding_backend.model.entity.Post;
import toughcircle.wanted_pre_onboarding_backend.model.entity.Submission;
import toughcircle.wanted_pre_onboarding_backend.model.entity.User;
import toughcircle.wanted_pre_onboarding_backend.repository.CompanyRepository;
import toughcircle.wanted_pre_onboarding_backend.repository.PostRepository;
import toughcircle.wanted_pre_onboarding_backend.repository.SubmissionRepository;
import toughcircle.wanted_pre_onboarding_backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final CompanyRepository companyRepository;
    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;

    // 생성
    @Transactional
    public void createPost(AddPostRequest request) {

        Company company = companyRepository.findById(request.getCompanyId())
            .orElseThrow(() -> new RuntimeException("Company not found with companyId: " + request.getCompanyId()));

        Post post = new Post();
        post.setSkill(request.getSkill());
        post.setCompensation(request.getCompensation());
        post.setContent(request.getContent());
        post.setPosition(request.getPosition());
        post.setCompany(company);

        postRepository.save(post);

    }

    // 수정
    @Transactional
    public void updatePost(Long postId, UpdatePostRequest request) {

        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post not found with postId: " + postId));

        post.setPosition(request.getPosition());
        post.setCompensation(request.getCompensation());
        post.setContent(request.getContent());
        post.setSkill(request.getSkill());

        postRepository.save(post);
    }

    // 삭제
    @Transactional
    public void deletePost(Long postId) {
        if (postRepository.findById(postId).isEmpty()) {
            throw new RuntimeException("Post not found whit postId: " + postId);
        }
        postRepository.deleteById(postId);

    }

    // 모든 리스트 조회
    public PostListResponse getPosts() {

        List<Post> postList = postRepository.findAll();
        List<PostDto> list = postList.stream().map(this::convertToDto).toList();

        return new PostListResponse(list);
    }

    // 조회 [검색]
    public PostListResponse searchPosts(String keyword) {

        List<Post> postList = postRepository.searchPostList(keyword);

        List<PostDto> list = postList.stream().map(this::convertToDto).toList();

        return new PostListResponse(list);
    }

    // 특정 데이터 조회 [채용내용 포함]
    public PostDetailResponse getPost(Long postId) {

        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post not found with postId: " + postId));

        return getPostDetailResponse(post);
    }

    // submission 데이터 생성
    @Transactional
    public void submitJobApplication(Long postId, Long userId) {

        Optional<Submission> optional = submissionRepository.findByUser_userIdAndPost_postId(userId, postId);

        if (optional.isPresent()) {
            throw new RuntimeException("Submission is already exist");
        } else {
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));

            Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with postId: " + postId));

            Submission submission = new Submission();
            submission.setPost(post);
            submission.setUser(user);

            submissionRepository.save(submission);
        }
    }

    // DTO 변환 [채용내용 포함 상세 페이지]
    private static PostDetailResponse getPostDetailResponse(Post post) {
        PostDetailResponse response = new PostDetailResponse();

        response.setPostId(post.getPostId());
        response.setCompanyName(post.getCompany().getCompanyName());
        response.setNation(post.getCompany().getNation());
        response.setRegion(post.getCompany().getRegion());
        response.setPosition(post.getPosition());
        response.setSkill(post.getSkill());
        response.setCompensation(post.getCompensation());
        response.setContent(post.getContent());

        response.setOtherPosts(
            post.getCompany().getPostList().stream()
                .map(Post::getPostId)
                .toList());

        return response;
    }

    // DTO 변환 [채용내용 미포함 목록]
    private PostDto convertToDto(Post post) {

        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setCompanyName(post.getCompany().getCompanyName());
        postDto.setNation(post.getCompany().getNation());
        postDto.setRegion(post.getCompany().getRegion());
        postDto.setPosition(post.getPosition());
        postDto.setSkill(post.getSkill());
        postDto.setCompensation(post.getCompensation());

        return postDto;
    }


}
