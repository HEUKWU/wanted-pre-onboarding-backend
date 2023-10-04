package spring.wantedpreonboardingbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.wantedpreonboardingbackend.entity.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostDto {

    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Req {
        private Long companyId;
        private String position;
        private int reward;
        private String description;
        private String skill;
    }

    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Update {
        private String position;
        private int reward;
        private String description;
        private String skill;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Res {
        private Long companyId;
        private Long postId;
        private String position;
        private int reward;
        private String description;
        private String skill;

        public static Res of(Post post) {
            return Res.builder()
                    .postId(post.getId())
                    .position(post.getPosition())
                    .reward(post.getReward())
                    .description(post.getPosition())
                    .skill(post.getSkill())
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
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
