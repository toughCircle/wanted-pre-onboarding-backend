package toughcircle.wanted_pre_onboarding_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailResponse {

    private Long postId;
    private String companyName;
    private String nation;
    private String region;
    private String position;
    private int compensation;
    private String content;
    private String skill;
    private List<Long> otherPosts;

}
