package toughcircle.wanted_pre_onboarding_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import toughcircle.wanted_pre_onboarding_backend.model.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p JOIN Company c ON p.companyId = c.companyId WHERE " +
        "p.position LIKE %:keyword% OR " +
        "p.skill LIKE %:keyword% OR " +
        "c.companyName LIKE %:keyword%")
    List<Post> searchPostList(@Param("keyword") String keyword);
}
