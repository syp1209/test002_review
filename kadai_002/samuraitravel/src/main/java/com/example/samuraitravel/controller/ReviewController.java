package com.example.samuraitravel.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewInputForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.service.ReviewService;

@Controller

public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    //レビュー投稿ページ1
    @GetMapping("/houses/__${house.getId()}__/review/input")
    public String inputReview(Model model) {
        ReviewInputForm form = new ReviewInputForm();
        form.setScores(Arrays.asList("★★★★★", "★★★★", "★★★", "★★", "★"));
        model.addAttribute("reviewInputForm", form);
        return "/houses/__${house.getId()}__/review/input";
    }

    //レビュー投稿ページ2
    @PostMapping("/houses/__${house.getId()}__/review/create")
    public String createReview(@ModelAttribute ReviewInputForm reviewInputForm) {
        reviewService.create(reviewInputForm);
        return "redirect:/houses/__${house.getId()}__/review/list"; // 投稿が成功したらリストページにリダイレクト
    }
    
    //レビュー一覧ページ@PathVariable(name = "id") Integer id
    @GetMapping("/houses/__${house.getId()}__/review/list")
    //public String showReviewList(@RequestParam("houseId") Long houseId, Model model) {
    public String showReviewList(@PathVariable(name = "id") Integer id, Model model) {
    	House house = HouseRepository.getReferenceById(id);
    	
        List<Review> reviews = reviewService.findReviewsByHouseId(houseId); 
        model.addAttribute("reviews", reviews);
        
        return "review/list";
    }
}