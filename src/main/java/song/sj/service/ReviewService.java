package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import song.sj.dto.member.SaveReviewDto;
import song.sj.entity.Member;
import song.sj.entity.Review;
import song.sj.entity.ReviewImages;
import song.sj.repository.ReviewImageRepository;
import song.sj.repository.ReviewRepository;
import song.sj.repository.ShopRepository;
import song.sj.service.image.ImageFile;
import song.sj.service.toEntity.ToReviews;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ImageFile imageFile;
    private final MemberService memberService;
    private final ShopRepository shopRepository;

    public void saveReview(Long shopId, SaveReviewDto dto, List<MultipartFile> files) {
        Review reviewEntity = ToReviews.toReviewsEntity(dto);
        Review review = reviewRepository.save(reviewEntity);

        addReviewImages(files, review);
        review.addReviews(memberService.getMemberFromJwt(), shopRepository.findById(shopId).orElseThrow());

    }

    private void addReviewImages(List<MultipartFile> files, Review review) {

        try {
            for (MultipartFile file : files) {
                ReviewImages reviewImages = imageFile.serverFile(file, ReviewImages.class);
                reviewImageRepository.save(reviewImages);
                review.addReviewImages(reviewImages);
            }
        } catch (IOException e) {
            log.info("addReviewImages error={}", e.getMessage());
        }
    }

}
