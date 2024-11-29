package com.example.samuraitravel.form;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewInputForm {
    @NotBlank(message = "評価を５段階で選択してください")
    private String score;
    
    @NotBlank(message = "レビュー内容を入力してください")
    private String content;

    //ドロップダウンリスト
	  private String selectedValue;
	  private List<String> scores;

	  // ゲッターとセッター
	  public String getSelectedScore() {
	    return selectedValue;
	  }

	  public void setSelectedScore(String selectedValue) {
	    this.selectedValue = selectedValue;
	  }

	  public List<String> getScores() {
	    return scores;
	  }

	  public void setScores(List<String> scores) {
	    this.scores = scores;
	  }
}
