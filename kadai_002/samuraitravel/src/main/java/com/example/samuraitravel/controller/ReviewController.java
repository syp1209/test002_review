package com.example.samuraitravel.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewInputForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.UserRepository;
import com.example.samuraitravel.service.ReviewService;

@Controller

public class ReviewController {

	 private ReviewService reviewService;
	 private HouseRepository houseRepository;
	 private UserRepository userRepository;

	
	public ReviewController(
			ReviewService reviewService,
			HouseRepository houseRepository,
			UserRepository userRepository
			) {
		this.reviewService = reviewService;
		this.houseRepository = houseRepository;
		this.userRepository = userRepository;
	}
    
    //レビュー投稿ページ
	@PostMapping("/houses/{houseId}/review/list/input")
    public String inputReview(@ModelAttribute) {
    	//Scoresドロップダウンリストの作成
        ReviewInputForm form = new ReviewInputForm();
        form.setScores(Arrays.asList("★★★★★", "★★★★", "★★★", "★★", "★"));
        
        //Scoreにドロップダウンを設定したReviewInputFormをInput.htmlに渡す
        model.addAttribute("reviewInputForm", form);
        
        
        return "/houses/{houseId}/review/list/input";
    }

	
    //レビュー投稿ページの「投稿」ボタンクリック→DB登録→レビュー一覧へリダイレクト
    @PostMapping("/houses/{houseId}/review/list/input/create")
    public String createReview(@PathVariable(name = "houseId") Integer houseId,
								@RequestParam(name = "score", required = false) String score,
								@RequestParam(name = "content", required = false) String content,
								@AuthenticationPrincipal User
								Model model) 
    {
    	
    	House house = houseRepository.getReferenceById(houseId);
    	User user = userRepository.getReferenceById(userId);
        
    	
    	reviewService.create(score,content,houseId,userId);

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