package com.example.samuraitravel.controller;

import java.util.Arrays;
import java.util.List;

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

	 private ReviewService reviewService;
	 private HouseRepository houseRepository;
	
	public ReviewController(
			ReviewService reviewService,
			HouseRepository houseRepository) {
		this.reviewService = reviewService;
		this.houseRepository = houseRepository;
	}
    
    //レビュー投稿ページ1
    @GetMapping("/houses/{houseId}/review/input")
    public String inputReview(Model model) {
        ReviewInputForm form = new ReviewInputForm();
        form.setScores(Arrays.asList("★★★★★", "★★★★", "★★★", "★★", "★"));
        model.addAttribute("reviewInputForm", form);
        return "/houses/{houseId}/review/input";
    }

    //レビュー投稿ページ2
    @PostMapping("/houses/{houseId}/review/create")
    public String createReview(@ModelAttribute ReviewInputForm reviewInputForm) {
        reviewService.create(reviewInputForm);
        return "redirect:/houses/{houseId}/review/list"; // 投稿が成功したらリストページにリダイレクト
    }
    
    //レビュー一覧ページ@PathVariable(name = "id") Integer id
    @GetMapping("/houses/{houseId}/review/list")
    public String showReviewList(@PathVariable(name = "houseId") Integer houseId, Model model) {
    	
    	//リクエスト情報に設定されているHouseIDを条件に
    	//紐づくレビュー情報の一覧を取得する。
        List<Review> reviews = reviewService.findReviewsByHouseId(houseId);
        House house = houseRepository.getReferenceById(houseId);
        
        model.addAttribute("reviews", reviews);
        model.addAttribute("house", house);
        
        return "review/list";
    }
}