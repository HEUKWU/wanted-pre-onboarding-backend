package spring.wantedpreonboardingbackend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.wantedpreonboardingbackend.entity.Apply;

public class ApplyDto {

    @Getter
    @NoArgsConstructor
    public static class Req {
        private Long userId;
        private Long postId;
    }

    @Getter
    @NoArgsConstructor
    public static class Res {
        private Long userId;
        private Long postId;

        @Builder
        public Res(Long userId, Long postId) {
            this.userId = userId;
            this.postId = postId;
        }

        public static Res of(Apply apply) {
            return Res.builder()
                    .userId(apply.getUser().getId())
                    .postId(apply.getPost().getId())
                    .build();
        }
    }

}
