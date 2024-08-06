package toughcircle.wanted_pre_onboarding_backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import toughcircle.wanted_pre_onboarding_backend.model.dto.*;
import toughcircle.wanted_pre_onboarding_backend.model.entity.Company;
import toughcircle.wanted_pre_onboarding_backend.model.entity.Post;
import toughcircle.wanted_pre_onboarding_backend.model.entity.Submission;
import toughcircle.wanted_pre_onboarding_backend.model.entity.User;
import toughcircle.wanted_pre_onboarding_backend.repository.CompanyRepository;
import toughcircle.wanted_pre_onboarding_backend.repository.PostRepository;
import toughcircle.wanted_pre_onboarding_backend.repository.SubmissionRepository;
import toughcircle.wanted_pre_onboarding_backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // 목 객체 자동 초기화, 예외 처리
class PostServiceTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private SubmissionRepository submissionRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void testCreatePost() {
        // given
        Company company = new Company();
        company.setCompanyId(1L);
        company.setCompanyName("원티드랩");
        company.setNation("한국");
        company.setRegion("서울");

        Post post1 = new Post();
        post1.setPostId(1L);
        post1.setCompany(company);
        post1.setPosition("백엔드 주니어 개발자");
        post1.setCompensation(1500000);
        post1.setSkill("Python");
        post1.setContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...");

        company.setPostList(List.of(post1));

        AddPostRequest request = new AddPostRequest();
        request.setCompanyId(1L);
        request.setPosition("Developer");
        request.setCompensation(1000000);
        request.setContent("Job Content");
        request.setSkill("Java");

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(postRepository.save(any(Post.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        postService.createPost(request);

        // then
        verify(postRepository, times(1)).save(any(Post.class));

        Optional<Post> savedPost = postRepository.findById(1L);
        if (savedPost.isPresent()) {
            assertEquals("Developer", savedPost.get().getPosition());
            assertEquals(100000, savedPost.get().getCompensation());
            assertEquals("Job Content", savedPost.get().getContent());
            assertEquals("Java", savedPost.get().getSkill());
            assertEquals(company, savedPost.get().getCompany());
        }

    }

    @Test
    void testUpdatePost() {
        // given
        Company company = new Company();
        company.setCompanyId(1L);
        company.setCompanyName("원티드랩");
        company.setNation("한국");
        company.setRegion("서울");
        company.setPostList(new ArrayList<>());

        Post post1 = new Post();
        post1.setPostId(1L);
        post1.setCompany(company);
        post1.setPosition("백엔드 주니어 개발자");
        post1.setCompensation(1500000);
        post1.setSkill("Python");
        post1.setContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...");

        company.getPostList().add(post1);

        Long postId = 1L;

        UpdatePostRequest request = new UpdatePostRequest();
        request.setPosition("프론트엔드 주니어 개발자");
        request.setCompensation(1000000);
        request.setSkill("JavaScript");
        request.setContent("원티드랩에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은...");

        when(postRepository.findById(postId)).thenReturn(Optional.of(post1));
        when(postRepository.save(any(Post.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        postService.updatePost(postId, request);

        //then
        verify(postRepository, times(1)).save(any(Post.class));

        Optional<Post> savedPost = postRepository.findById(1L);
        savedPost.ifPresent(post -> {
            assertEquals("프론트엔드 주니어 개발자", post.getPosition());
            assertEquals(1000000, post.getCompensation());
            assertEquals("원티드랩에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은...", post.getContent());
            assertEquals("JavaScript", post.getSkill());
            assertEquals(company.getCompanyId(), post.getCompany().getCompanyId());
        });
    }

    @Test
    void testDeletePost() {
        // given
        Company company = new Company();
        company.setCompanyId(1L);
        company.setCompanyName("원티드랩");
        company.setNation("한국");
        company.setRegion("서울");
        company.setPostList(new ArrayList<>());

        Post post1 = new Post();
        post1.setPostId(1L);
        post1.setCompany(company);
        post1.setPosition("백엔드 주니어 개발자");
        post1.setCompensation(1500000);
        post1.setSkill("Python");
        post1.setContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...");

        company.setPostList(List.of(post1));

        when(postRepository.findById(1L)).thenReturn(Optional.of(post1));

        Long postId = 1L;

        // when
        postService.deletePost(postId);

        // then
        verify(postRepository, times(1)).deleteById(postId);
    }

    @Test
    void testGetPosts() {
        // given
        Company company1 = new Company();
        company1.setCompanyId(1L);
        company1.setCompanyName("원티드랩");
        company1.setNation("한국");
        company1.setRegion("서울");

        Company company2 = new Company();
        company2.setCompanyId(2L);
        company2.setCompanyName("네이버");
        company2.setNation("한국");
        company2.setRegion("서울");

        Post post1 = new Post();
        post1.setPostId(1L);
        post1.setCompany(company1);
        post1.setPosition("백엔드 주니어 개발자");
        post1.setCompensation(1500000);
        post1.setSkill("Python");
        post1.setContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...");

        Post post2 = new Post();
        post2.setPostId(2L);
        post2.setCompany(company1);
        post2.setPosition("프론트엔드 주니어 개발자");
        post2.setCompensation(1500000);
        post2.setSkill("JavaScript");
        post2.setContent("원티드랩에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은...");

        company1.setPostList(Arrays.asList(post1, post2));

        Post post3 = new Post();
        post3.setPostId(3L);
        post3.setCompany(company2);
        post3.setPosition("백엔드 주니어 개발자");
        post3.setCompensation(1500000);
        post3.setSkill("Java");
        post3.setContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...");

        company2.setPostList(List.of(post3));

        when(postRepository.findAll()).thenReturn(Arrays.asList(post1, post2, post3));

        // when
        PostListResponse response = postService.getPosts();

        // then
        assertEquals(3, response.getPostList().size());

        assertEquals("원티드랩", response.getPostList().get(0).getCompanyName());
        assertEquals("백엔드 주니어 개발자", response.getPostList().get(0).getPosition());
        assertEquals("Python", response.getPostList().get(0).getSkill());

        assertEquals("원티드랩", response.getPostList().get(1).getCompanyName());
        assertEquals("프론트엔드 주니어 개발자", response.getPostList().get(1).getPosition());
        assertEquals("JavaScript", response.getPostList().get(1).getSkill());

        assertEquals("네이버", response.getPostList().get(2).getCompanyName());
        assertEquals("백엔드 주니어 개발자", response.getPostList().get(2).getPosition());
        assertEquals("Java", response.getPostList().get(2).getSkill());
    }


    @Test
    void testSearchPosts() {
        // given
        Company company1 = new Company();
        company1.setCompanyId(1L);
        company1.setCompanyName("원티드랩");
        company1.setNation("한국");
        company1.setRegion("서울");

        Company company2 = new Company();
        company2.setCompanyId(2L);
        company2.setCompanyName("네이버");
        company2.setNation("한국");
        company2.setRegion("서울");

        Post post1 = new Post();
        post1.setPostId(1L);
        post1.setCompany(company1);
        post1.setPosition("백엔드 주니어 개발자");
        post1.setCompensation(1500000);
        post1.setSkill("Python");
        post1.setContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...");

        Post post2 = new Post();
        post2.setPostId(2L);
        post2.setCompany(company1);
        post2.setPosition("프론트엔드 주니어 개발자");
        post2.setCompensation(1500000);
        post2.setSkill("JavaScript");
        post2.setContent("원티드랩에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은...");

        company1.setPostList(Arrays.asList(post1, post2));

        Post post3 = new Post();
        post3.setPostId(3L);
        post3.setCompany(company2);
        post3.setPosition("백엔드 주니어 개발자");
        post3.setCompensation(1500000);
        post3.setSkill("Java");
        post3.setContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...");

        company2.setPostList(List.of(post3));

        String keyword = "백엔드";
        when(postRepository.searchPostList(keyword)).thenReturn(Arrays.asList(post1, post3));

        // when
        PostListResponse response = postService.searchPosts(keyword);

        assertEquals(2, response.getPostList().size());

        assertEquals(1L, response.getPostList().get(0).getPostId());
        assertEquals(3L, response.getPostList().get(1).getPostId());

    }

    @Test
    void testGetPost() {
        // given
        Company company = new Company();
        company.setCompanyId(1L);
        company.setCompanyName("원티드랩");
        company.setNation("한국");
        company.setRegion("서울");
        company.setPostList(new ArrayList<>());

        Post post1 = new Post();
        post1.setPostId(1L);
        post1.setCompany(company);
        post1.setPosition("백엔드 주니어 개발자");
        post1.setCompensation(1500000);
        post1.setSkill("Python");
        post1.setContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...");

        company.setPostList(List.of(post1));

        when(postRepository.findById(1L)).thenReturn(Optional.of(post1));

        Long postId = 1L;

        // when
        PostDetailResponse response = postService.getPost(postId);

        // then
        assertEquals(post1.getContent(), response.getContent());
        assertEquals(1, response.getOtherPosts().size());
    }

    @Test
    void testSubmitJobApplication() {
        // given
        User user = new User();
        user.setUserId(1L);
        user.setUsername("User");

        Company company = new Company();
        company.setCompanyId(1L);
        company.setCompanyName("원티드랩");
        company.setNation("한국");
        company.setRegion("서울");
        company.setPostList(new ArrayList<>());

        Post post1 = new Post();
        post1.setPostId(1L);
        post1.setCompany(company);
        post1.setPosition("백엔드 주니어 개발자");
        post1.setCompensation(1500000);
        post1.setSkill("Python");
        post1.setContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...");

        company.setPostList(List.of(post1));

        Long userId = 1L;
        Long postId = 1L;

        when(submissionRepository.findByUser_userIdAndPost_postId(userId, postId)).thenReturn(Optional.empty());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.findById(postId)).thenReturn(Optional.of(post1));
        when(submissionRepository.save(any(Submission.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        postService.submitJobApplication(postId, userId);

        // then
        verify(submissionRepository, times(1)).save(any(Submission.class));
        Optional<Submission> savedSubmit = submissionRepository.findById(1L);
        if (savedSubmit.isPresent()) {
            assertEquals(postId, savedSubmit.get().getPost().getPostId());
            assertEquals(userId, savedSubmit.get().getUser().getUserId());
        }

    }
}