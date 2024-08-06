package toughcircle.wanted_pre_onboarding_backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Submission 엔티티는 채용공고 지원 내역에 대한 정보를 저장합니다.
 */
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Submission {

    /**
     * 지원 내역의 고유 ID입니다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long submissionId;

    /**
     * 해당 지원 내역의 생성 날짜를 나타냅니다.
     */
    private LocalDateTime submittedAt;

    /**
     * 지원자를 나타냅니다.
     */
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    /**
     * 채용공고를 나타냅니다.
     */
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

}
