package toughcircle.wanted_pre_onboarding_backend.model.dto;

import lombok.Data;

@Data
public class PostDto {

    private Long postId;
    private String companyName;
    private String nation;
    private String region;
    private String position;
    private int compensation;
    private String skill;

}
