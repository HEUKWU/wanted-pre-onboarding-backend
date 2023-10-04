package spring.wantedpreonboardingbackend.entity;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

import lombok.*;
import org.hibernate.annotations.*;
import spring.wantedpreonboardingbackend.dto.PostDto;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE post SET deleted = true WHERE id = ?")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Apply> applyList;

    private String position;

    private int reward;

    private String description;

    private String skill;

    private boolean deleted = Boolean.FALSE;

    public static Post of(Company company, PostDto.Req dto) {
        return Post.builder()
                .company(company)
                .position(dto.getPosition())
                .reward(dto.getReward())
                .description(dto.getDescription())
                .skill(dto.getSkill())
                .build();
    }

    public Post update(PostDto.Update dto) {
        this.position = dto.getPosition();
        this.reward = dto.getReward();
        this.description = dto.getDescription();
        this.skill = dto.getSkill();

        return this;
    }
}
