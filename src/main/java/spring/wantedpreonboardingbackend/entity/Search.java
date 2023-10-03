package spring.wantedpreonboardingbackend.entity;

import lombok.Getter;

@Getter
public class Search {
    private String search;

    public Search(String search) {
        this.search = search;
    }
}
