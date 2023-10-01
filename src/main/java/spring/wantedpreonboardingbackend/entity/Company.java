package spring.wantedpreonboardingbackend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String country;

    private String location;

    @OneToMany(mappedBy = "company")
    private List<Post> postList;

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCountry() {
        return country;
    }

    public String getLocation() {
        return location;
    }

    public List<Post> getPostList() {
        return postList;
    }
}
