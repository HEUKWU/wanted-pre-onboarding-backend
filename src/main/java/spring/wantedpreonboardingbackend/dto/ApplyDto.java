package spring.wantedpreonboardingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.wantedpreonboardingbackend.entity.Apply;

public class ApplyDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req {
        private Long userId;
        private Long postId;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Res {
        private Long userId;
        private Long postId;
        private String companyName;
        private String country;
        private String location;
        private String position;
        private int reward;
        private String skill;
        private String description;

        public static Res of(Apply apply) {
            return Res.builder()
                    .userId(apply.getUser().getId())
                    .postId(apply.getPost().getId())
                    .companyName(apply.getPost().getCompany().getCompanyName())
                    .country(apply.getPost().getCompany().getCountry())
                    .location(apply.getPost().getCompany().getLocation())
                    .position(apply.getPost().getPosition())
                    .reward(apply.getPost().getReward())
                    .skill(apply.getPost().getSkill())
                    .description(apply.getPost().getDescription())
                    .build();
        }
    }

}
