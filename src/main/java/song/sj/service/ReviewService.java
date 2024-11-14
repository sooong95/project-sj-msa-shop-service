package song.sj.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import song.sj.dto.member.SaveReviewDto;
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
        review.addReview(memberService.getMemberFromJwt(), shopRepository.findById(shopId).orElseThrow());

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

    public void updateReview(Long id, SaveReviewDto dto) {

        Review review = reviewRepository.findById(id).orElseThrow();
        review.changeReviewTitle(dto.getReviewTitle());
        review.changeContent(dto.getContent());
        review.changeGrade(dto.getGrade());
    }

    public void addReviewImages(Long reviewId, List<MultipartFile> files) {

        addReviewImages(files, reviewRepository.findById(reviewId).orElseThrow());
    }

    public void deleteReviewImages(Long reviewImageId) {

        ReviewImages reviewImages = reviewImageRepository.findById(reviewImageId).orElseThrow(() ->
                new EntityNotFoundException("이미지를 찾을 수 없습니다."));

        Review review = reviewImages.getReview();
        review.deleteReviewImages(reviewImages);

        reviewImageRepository.delete(reviewImages);
    }

    public void deleteReview(Long reviewId, Long shopId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 리뷰 입니다."));
        log.info("리뷰 내용={}", review.getReviewTitle());
        /*review.getReviewImagesList().forEach(image -> deleteReviewImages(image.getId()));*/
        review.getReviewImagesList().forEach(reviewImageRepository::delete);

        log.info("shop={}", shopRepository.findById(shopId).orElseThrow());

        review.deleteReview(memberService.getMemberFromJwt(), shopRepository.findById(shopId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 shop 입니다.")));
        reviewRepository.delete(review);
        log.info("리뷰 확인={}", review.getReviewTitle());
    }
}
