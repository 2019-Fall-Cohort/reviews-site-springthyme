package org.wcci.reviewssite;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
	public Iterable<Review> findAllByOrderByIdDesc();
}
