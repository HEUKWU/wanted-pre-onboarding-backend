package spring.wantedpreonboardingbackend.entity;

import javax.persistence.*;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import spring.wantedpreonboardingbackend.dto.PostDto;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE post SET deleted = true WHERE id = ?")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "post")
    private List<Apply> applyList;

    private String position;

    private int reward;

    private String description;

    private String skill;

    private boolean deleted = Boolean.FALSE;

    public Post(Company company, PostDto.Req dto) {
        this.company = company;
        this.position = dto.getPosition();
        this.reward = dto.getReward();
        this.description = dto.getDescription();
        this.skill = dto.getSkill();
    }

    public Post update(PostDto.Update dto) {
        this.position = dto.getPosition();
        this.reward = dto.getReward();
        this.description = dto.getDescription();
        this.skill = dto.getSkill();

        return this;
    }
}
