package spring.wantedpreonboardingbackend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
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

    private String skill;

    public Long getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public List<Apply> getApplyList() {
        return applyList;
    }

    public String getPosition() {
        return position;
    }

    public int getReward() {
        return reward;
    }

    public String getSkill() {
        return skill;
    }
}
