package com.hmsapp.controller;

import com.hmsapp.entity.Property;
import com.hmsapp.entity.Reviews;
import com.hmsapp.entity.User;
import com.hmsapp.repository.PropertyRepository;
import com.hmsapp.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final PropertyRepository propertyRepository;
    private final ReviewsRepository reviewsRepository;

    @Autowired
    public ReviewController(PropertyRepository propertyRepository, ReviewsRepository reviewsRepository) {
        this.propertyRepository = propertyRepository;
        this.reviewsRepository = reviewsRepository;
    }

    @PostMapping
    public String addReview(
            @RequestBody Reviews review,
            @RequestParam long propertyId,
            @AuthenticationPrincipal User user
    ) {

    Optional<Property> property=propertyRepository.findById(propertyId);
     Reviews reviewsStatus = reviewsRepository.findByPropertyAndUser(property, user);

     if(reviewsStatus!=null) {
         review.setPropertyId(propertyId);
         review.setUser(user);
         reviewsRepository.save(review);
         return "added...";
     }
       return "review already given";
    }

    @GetMapping("/user/reviews")
    public List<Reviews>viewMyReviews(
            @AuthenticationPrincipal User user
    ){
        return reviewsRepository.findByUser(user);
    }
}
