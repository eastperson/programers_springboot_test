package com.github.prgrms.reviews;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository {

    Optional<Review> findById(long id);

    List<Review> findAll();

    Long save(Review review);

}
