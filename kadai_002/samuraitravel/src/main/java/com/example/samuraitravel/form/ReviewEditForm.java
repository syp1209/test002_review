package com.example.samuraitravel.form;

import jakarta.validation.constraints.NotBlank;

public class ReviewEditForm {
    @NotBlank(message = "評価を５段階で選択してください")
    private String score;
    //DBのscoreカラムのレコードを取得する
    
    @NotBlank(message = "レビュー内容を編集できます")
    private String memo;
    //DBのmomoカラムのレコードを取得する
    
    
    //見本
    //@NotBlank(message = "チェックイン日とチェックアウト日を選択してください。")
    //private String fromCheckinDateToCheckoutDate;    
    
    //@NotNull(message = "宿泊人数を入力してください。")
    //@Min(value = 1, message = "宿泊人数は1人以上に設定してください。")
    //private Integer numberOfPeople; 

    // チェックイン日を取得する
    //public LocalDate getCheckinDate() {
    //    String[] checkinDateAndCheckoutDate = getFromCheckinDateToCheckoutDate().split(" から ");
    //    return LocalDate.parse(checkinDateAndCheckoutDate[0]);
    //}

    // チェックアウト日を取得する
    //public LocalDate getCheckoutDate() {
    //    String[] checkinDateAndCheckoutDate = getFromCheckinDateToCheckoutDate().split(" から ");
    //    return LocalDate.parse(checkinDateAndCheckoutDate[1]);
    //}
}
