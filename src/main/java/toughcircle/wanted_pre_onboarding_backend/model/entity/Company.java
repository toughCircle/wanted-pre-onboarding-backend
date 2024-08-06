package toughcircle.wanted_pre_onboarding_backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    private String companyName;
    private String nation;
    private String region;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Post> postList = new ArrayList<>();
}
