package song.sj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import song.sj.dto.member.SaveReviewDto;
import song.sj.service.ReviewService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{shopId}")
    public ResponseEntity<String> saveReview(@PathVariable("shopId") Long shopId, @ModelAttribute SaveReviewDto dto,
                                             @RequestParam("image")List<MultipartFile> files) {
        reviewService.saveReview(shopId, dto, files);
        return new ResponseEntity<>("리뷰 저장 성공!", HttpStatus.CREATED);
    }


}
