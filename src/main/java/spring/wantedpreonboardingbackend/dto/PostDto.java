package spring.wantedpreonboardingbackend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.wantedpreonboardingbackend.entity.Company;
import spring.wantedpreonboardingbackend.entity.Post;

public class PostDto {

    @Getter
    @NoArgsConstructor
    public static class Req {
        private Long companyId;
        private String position;
        private int reward;
        private String description;
        private String skill;

        public Req(Long companyId, String position, int reward, String description, String skill) {
            this.companyId = companyId;
            this.position = position;
            this.reward = reward;
            this.description = description;
            this.skill = skill;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Res {
        private Long companyId;
        private String position;
        private int reward;
        private String description;
        private String skill;

        @Builder
        public Res(Long companyId, String position, int reward, String description, String skill) {
            this.companyId = companyId;
            this.position = position;
            this.reward = reward;
            this.description = description;
            this.skill = skill;
        }

        public static Res of(Long companyId, Post post) {
            return Res.builder()
                    .companyId(companyId)
                    .position(post.getPosition())
                    .reward(post.getReward())
                    .description(post.getPosition())
                    .skill(post.getSkill())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class PostList {
        private Long postId;
        private String companyName;
        private String country;
        private String location;
        private String position;
        private int reward;
        private String skill;

        @Builder
        public PostList(Long postId, String companyName, String country, String location, String position, int reward, String skill) {
            this.postId = postId;
            this.companyName = companyName;
            this.country = country;
            this.location = location;
            this.position = position;
            this.reward = reward;
            this.skill = skill;
        }

        public static PostList of(Post post) {
            return PostList.builder()
                    .postId(post.getId())
                    .companyName(post.getCompany().getCompanyName())
                    .country(post.getCompany().getCountry())
                    .location(post.getCompany().getLocation())
                    .position(post.getPosition())
                    .reward(post.getReward())
                    .skill(post.getSkill())
                    .build();
        }
    }
}
