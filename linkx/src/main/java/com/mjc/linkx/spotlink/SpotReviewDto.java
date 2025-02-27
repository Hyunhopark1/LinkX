package com.mjc.linkx.spotlink;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpotReviewDto {
    private Long id;              // 리뷰 ID
    private Long spotId;          // 스팟 ID (리뷰가 연결된 스팟)
    private Long userId;          // 작성자 사용자 ID
    private String reviewTitle;   // 리뷰 제목
    private String reviewContent; // 리뷰 내용
    private int reviewStar;       // 리뷰 별점
    private int reviewLike;       // 리뷰 좋아요 수
    private String userNickName;  // 작성자 닉네임
    private LocalDateTime reviewDate; // 리뷰 작성 날짜
    private boolean canDelete;    // 삭제 가능 여부 (추가)
}
