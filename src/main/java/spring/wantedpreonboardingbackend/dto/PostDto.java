package spring.wantedpreonboardingbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.wantedpreonboardingbackend.entity.Post;

import java.util.List;
import java.util.stream.Collectors;

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
    public static class Update {
        private String position;
        private int reward;
        private String description;
        private String skill;

        public Update(String position, int reward, String description, String skill) {
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

        public static Res of(Post post) {
            return Res.builder()
                    .companyId(post.getCompany().getId())
                    .position(post.getPosition())
                    .reward(post.getReward())
                    .description(post.getPosition())
                    .skill(post.getSkill())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class GetPost {
        private Long postId;
        private String companyName;
        private String country;
        private String location;
        private String position;
        private int reward;
        private String skill;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String description;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<Long> otherPosts;

        @Builder
        public GetPost(Long postId, String companyName, String country, String location, String position, int reward, String skill, String description, List<Long> otherPosts) {
            this.postId = postId;
            this.companyName = companyName;
            this.country = country;
            this.location = location;
            this.position = position;
            this.reward = reward;
            this.skill = skill;
            this.description = description;
            this.otherPosts = otherPosts;
        }

        public static GetPost getPostList(Post post) {
            return GetPost.builder()
                    .postId(post.getId())
                    .companyName(post.getCompany().getCompanyName())
                    .country(post.getCompany().getCountry())
                    .location(post.getCompany().getLocation())
                    .position(post.getPosition())
                    .reward(post.getReward())
                    .skill(post.getSkill())
                    .build();
        }

        public static GetPost getPost(Post post) {
            List<Post> postList = post.getCompany().getPostList();

            return GetPost.builder()
                    .postId(post.getId())
                    .companyName(post.getCompany().getCompanyName())
                    .country(post.getCompany().getCountry())
                    .location(post.getCompany().getLocation())
                    .position(post.getPosition())
                    .reward(post.getReward())
                    .skill(post.getSkill())
                    .description(post.getDescription())
                    .otherPosts(postList.stream()
                            .map(Post::getId)
                            .filter(id -> !id.equals(post.getId()))
                            .collect(Collectors.toList()))
                    .build();
        }
    }
}
