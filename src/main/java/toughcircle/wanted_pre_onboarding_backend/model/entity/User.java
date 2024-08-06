package toughcircle.wanted_pre_onboarding_backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * User 엔티티는 사용자에 대한 정보를 저장합니다.
 */
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 사용자의 고유 ID입니다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * 사용자의 이름을 나타냅니다.
     */
    private String username;

    /**
     * 사용자가 채용공고에 지원한 내역을 나타냅니다.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Submission> submissionList = new ArrayList<>();

}
