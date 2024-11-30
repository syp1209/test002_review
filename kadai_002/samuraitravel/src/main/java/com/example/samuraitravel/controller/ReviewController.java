package com.example.samuraitravel.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.samuraitravel.form.ReviewInputForm;
import com.example.samuraitravel.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/input")
    public String inputReview(Model model) {
        ReviewInputForm form = new ReviewInputForm();
        form.setScores(Arrays.asList("★★★★★", "★★★★", "★★★", "★★", "★"));
        model.addAttribute("reviewInputForm", form);
        return "review/input";
    }

    @PostMapping("/create")
    public String createReview(@ModelAttribute ReviewInputForm reviewInputForm) {
        reviewService.create(reviewInputForm);
        return "redirect:/review/list"; // 投稿が成功したらリストページにリダイレクト
    }
}