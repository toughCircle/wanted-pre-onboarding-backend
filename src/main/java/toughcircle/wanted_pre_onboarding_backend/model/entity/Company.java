package toughcircle.wanted_pre_onboarding_backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Company 엔티티는 회사에 대한 정보를 저장합니다.
 */
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    /**
     * 회사의 고유 ID입니다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    /**
     * 회사 이름을 나타냅니다.
     */
    private String companyName;
    /**
     * 회사가 위치한 국가를 나타냅니다.
     */
    private String nation;
    /**
     * 회사가 위치한 지역을 나타냅니다.
     */
    private String region;

    /**
     * 회사가 게시한 채용 공고 리스트입니다.
     */
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Post> postList = new ArrayList<>();
}
