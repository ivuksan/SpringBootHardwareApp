package hr.tvz.vuksan.hardwareapp.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAll();
    List<Review> findReviewsByHardware_Code(long id);
}
