package org.wcci.reviewssite;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
	public Iterable<Tag> findAllByOrderByTagNameAsc();
}
