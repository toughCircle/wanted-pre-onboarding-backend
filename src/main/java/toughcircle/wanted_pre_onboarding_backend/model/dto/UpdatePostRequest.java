package toughcircle.wanted_pre_onboarding_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequest {

    private String position;
    private int compensation;
    private String content;
    private String skill;

}
