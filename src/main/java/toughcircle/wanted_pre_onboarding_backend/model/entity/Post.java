package toughcircle.wanted_pre_onboarding_backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Post 채용공고에 대한 정보를 저장합니다.
 */
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    /**
     * 채용공고의 고유 ID입니다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    /**
     * 채용 포지션을 나타냅니다.
     */
    private String position;

    /**
     * 채용 보상금을 나타냅니다.
     */
    private int compensation;

    /**
     * 채용공고의 내용을 나타냅니다.
     */
    private String content;

    /**
     * 필요한 기술 스택을 나타냅니다.
     */
    private String skill;

    /**
     * 채용공고가 속한 회사입니다.
     */
    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;

    /**
     * 해당 채용공고에 대한 지원 내역 리스트입니다.
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Submission> submissionList = new ArrayList<>();

}
