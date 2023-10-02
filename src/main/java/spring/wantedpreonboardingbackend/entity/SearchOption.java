package spring.wantedpreonboardingbackend.entity;

import lombok.Getter;

@Getter
public class SearchOption {
    private String companyName;
    private String country;
    private String location;
    private String position;
    private String skill;

    public SearchOption(String companyName, String country, String location, String position, String skill) {
        this.companyName = companyName;
        this.country = country;
        this.location = location;
        this.position = position;
        this.skill = skill;
    }
}
