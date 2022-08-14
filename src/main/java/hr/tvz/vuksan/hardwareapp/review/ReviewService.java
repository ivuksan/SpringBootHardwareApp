package hr.tvz.vuksan.hardwareapp.review;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> findAll();
    List<ReviewDTO> findAllByCode(long code);
}
