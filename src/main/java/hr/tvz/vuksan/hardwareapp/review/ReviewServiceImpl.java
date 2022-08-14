package hr.tvz.vuksan.hardwareapp.review;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream().map(this::toReviewDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findAllByCode(long code) {
        return reviewRepository.findReviewsByHardware_Code(code).stream().map(this::toReviewDTO)
                .collect(Collectors.toList());
    }

    private ReviewDTO toReviewDTO(final Review review){
        return new ReviewDTO(review.getTitle(), review.getDescription(), review.getRating());
    }
}
