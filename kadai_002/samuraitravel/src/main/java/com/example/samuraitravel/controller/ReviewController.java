package com.example.samuraitravel.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.samuraitravel.form.ReviewInputForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.repository.UserRepository;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	//民宿のIDを使用するため、リポジトリを定義
	private HouseRepository houseRepository;
	//ユーザIDを使用するため、リポジトリを定義
	private UserRepository userRepository;
	//レビューのテーブル情報を使用する
	private ReviewRepository reviewRepository;
    
    
	
	//ページ表示
	//レビュー一覧ページへデータ表示
	@PostMapping("/list")
	public String showReviw(@RequestParam String scr ,@RequestParam String mem) {
		return "review/list";
		
	}
	
	
	//レビュー投稿ページの表示
		
	  @PostMapping("/input")
	  public String inputReviw(@RequestParam String scr ,@RequestParam String mem) {

		//scoreをドロップダウンリストで表示する
		  ReviewInputForm form = new ReviewInputForm();
	    form.setScores(Arrays.asList("★★★★★", "★★★★", "★★★", "★★","★"));
	   
	   
	    return "review/input";//レビュー投稿ページを表示する
	  }

	   
	  
	 //@PostMapping("/dropdown")
	 // public String submitForm(@ModelAttribute ReviewDropdownForm dropdownForm, Model model) {
	 //   model.addAttribute("selectedValue", dropdownForm.getSelectedValue());
	 //   return "result";
	  //}
	//レビュー編集ページの表示
		//入力値でDBを更新
	

}
