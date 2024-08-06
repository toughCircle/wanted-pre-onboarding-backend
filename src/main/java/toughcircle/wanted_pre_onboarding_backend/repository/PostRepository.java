package toughcircle.wanted_pre_onboarding_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import toughcircle.wanted_pre_onboarding_backend.model.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 키워드를 사용하여 채용 공고를 검색합니다.
     *
     * 이 쿼리는 Post와 Company 엔티티를 조인하여 position, skill, 또는 companyName 필드에
     * 키워드가 포함된 모든 Post 엔티티를 검색합니다.
     *
     * @param keyword 검색 키워드
     * @return 검색된 Post 엔티티의 리스트
     */
    @Query("SELECT p FROM Post p JOIN p.company c WHERE " +
        "p.position LIKE %:keyword% OR " +
        "p.skill LIKE %:keyword% OR " +
        "c.companyName LIKE %:keyword%")
    List<Post> searchPostList(@Param("keyword") String keyword);
}
