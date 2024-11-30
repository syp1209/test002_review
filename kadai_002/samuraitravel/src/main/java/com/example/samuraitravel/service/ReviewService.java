package com.example.samuraitravel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewInputForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.repository.UserRepository;

@Service
//@Transactionalを書くところ
public class ReviewService {
	//reviewテーブルの値をを利用・登録する
	//HouseテーブルのIDを利用する
	//userテーブルのIDを利用する
	
	
	private final ReviewRepository reviewRepository;
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;
	


	public ReviewService(ReviewRepository reviewRepository, HouseRepository houseRepository,
			UserRepository userRepository) {
		this.reviewRepository = reviewRepository;
		this.houseRepository = houseRepository;
		this.userRepository = userRepository;
	}

	//新規レビューをDBに保存 //ReviewInputForm ←新規投稿のForm
	@Transactional
	//レビューの登録処理を行うcreate()メソッドを定義　※コントローラから呼び出して使う
	//参照　13章 民宿の登録機能を作成しよう
	public void create(ReviewInputForm reviewInputForm) {
	    Review review = new Review();
	    House house = houseRepository.getReferenceById(reviewInputForm.getHouseId());
	    User user = userRepository.getReferenceById(reviewInputForm.getUserId());

	    review.setHouse(house);
	    review.setUser(user);
	    review.setScore(reviewInputForm.getScore());
	    review.setContent(reviewInputForm.getContent());

	    reviewRepository.save(review);
	}
	
	//レビュー一覧ページに表示するデータを取得する
    public List<Review> findReviewsByHouseId(Long houseId) {
        return reviewRepository.findByHouseIdOrderByCreatedAtDesc(houseId);
    }
    
    


}