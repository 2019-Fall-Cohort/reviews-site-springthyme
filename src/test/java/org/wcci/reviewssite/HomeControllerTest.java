package org.wcci.reviewssite;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class HomeControllerTest {
	@Mock
	Model model;
	@Mock
	ReviewStorage mockStorage;

	@InjectMocks
	HomeController underTest;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void displayAllReviews() throws Exception {
		String viewName = underTest.getAllReviews(model);
		assertThat(viewName, is("reviews"));
	}

	@Test
	public void displayAllReviewsAddReviewsToTheModel() throws Exception {
		Review mockReview = mock(Review.class);
		when(mockStorage.findAllTheReviews()).thenReturn(Collections.singletonList(mockReview));
		underTest.getAllReviews(model);
		verify(model).addAttribute("reviews", Collections.singletonList(mockReview));
	}

}
