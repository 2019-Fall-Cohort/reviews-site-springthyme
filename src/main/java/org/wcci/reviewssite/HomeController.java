package org.wcci.reviewssite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryStorage categoryStorage;
	@Autowired
	private ReviewStorage reviewStorage;

	
	@GetMapping("/")
		public String singleReview(Model model) {
			Review review = new Review("First Review", "Gone With The Wind", "James Doe", new Category("Fiction"),
			"So good I wanna slap my mama");
			model.addAttribute("review", review);
			return "review";
		}
	
	@GetMapping("/all_reviews")
	public String getAllReviews(Model model) {
		model.addAttribute("reviews", reviewStorage.findAllTheReviews());
		return "reviews";
	}
	
	@GetMapping("/add_review")
	public String getAddReview(Model model) {		
		model.addAttribute("categories", categoryStorage.findAllTheCategories()); 
		return "add_review";
	}

	@GetMapping("/reviews/{id}")
		public String singleReview(@PathVariable Long id, Model model) {
			
			Review review = reviewStorage.findReview(id);
			model.addAttribute("review", review );
			return "review";
		}

	
	
	@PostMapping("/add")
	public String addReview1(String reviewTitle, String bookTitle, String userName, String categoryName, String reviewBody) {
		Category category = new Category(categoryName);
		categoryStorage.addCategory(category);
		
		System.out.println("PostMapping before adding review has bookTitle: " + bookTitle + " and reviewBody: " + reviewBody);
		
		Review reviewToAdd = new Review(reviewTitle, bookTitle, userName, category, reviewBody);
		reviewStorage.addReview(reviewToAdd);
		Long reviewId = reviewToAdd.getId();
		
		System.out.println("PostMapping has bookTitle: " + reviewToAdd.getBookTitle() + " and reviewBody: " + reviewToAdd.getReviewBody());
		
		return "redirect:/all_reviews";
	}
	
	
}

