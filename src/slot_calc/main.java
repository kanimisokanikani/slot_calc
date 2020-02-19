package slot_calc;

import slot_calc.tool.safe_Scanner;
import slot_calc.tool.file_Writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class main {
    public static void main (String [] args) throws IOException {

        /////////////
        // 変数作成 //
        /////////////

        String slot_Name;               //スロットの名前

        long default_Stock;             //初期ストック
        long add_Stock;                 //一回転で増えるストック
        long bet;                       //一回の金額

        long numerator;                 //分子
        long denominator;               //分母

        double time = 0.0;              //一回転の時間
        double reduction_Rate;          //還元率  ％表記に使う

        double chance;                  //％表示するための確率
        double rotations_Number_To_Win; //当選までの平均回数


        //計算専用
        double stock_Calc;           //add_Stockを足したストック
        double bet_Calc;             //一回の金額

        double seconds_Time_Calc;    //当選までの時間 (秒)
        double minutes_Time_Calc;    //当選までの時間 (分)
        double hours_Time_Calc;      //当選までの時間 (時間)
        double days_Time_Calc;       //当選までの時間 (日)

        double secondly_Pay_Calc;    //秒給
        double minutely_Pay_Calc;    //分給
        double hourly_Pay_Calc;      //時給
        double daily_Pay_Calc;       //日給

        double numerator_calc = 0;   //計算専用の分子
        double denominator_calc = 0; //計算専用の分母


        /////////////
        // 設定入力 //
        /////////////

        System.out.println("スロット名を入力してください");

        while (true) {
            slot_Name = new Scanner(System.in).nextLine();
            if (slot_Name.equals("")) { //何も入力されていない場合ループ
                continue;
            }
            break;
        }

        System.out.println("初期ストックを入力してください");
        default_Stock = safe_Scanner.long_Scanner();

        System.out.println("一回転で増えるストックを入力してください");
        add_Stock = safe_Scanner.long_Scanner();

        System.out.println("一回の金額を入力してください");
        bet = safe_Scanner.long_Scanner();

        System.out.println("一回転の時間を少数で入力してください (1秒 の場合は 1.0)");
        time = safe_Scanner.double_Scanner();

        System.out.println("分子を入力してください (確率が 2/33 の場合 2)");
        System.out.println("?" + "/" + "?");
        numerator = safe_Scanner.long_Scanner();

        System.out.println("分母を入力してください (確率が 2/33 の場合 33)");
        System.out.println(numerator + "/" + "?");
        denominator = safe_Scanner.long_Scanner();


        ///////////////////
        // txtファイル生成 //
        ///////////////////

        file_Writer.slot_Setting_Writer_method (slot_Name, default_Stock, add_Stock, bet, time, numerator, denominator);
        //ファイル名.txtの生成をメソッドに外注


        //////////
        // 計算 //
        /////////

        for (long numerator_loop_calc = 0; numerator_loop_calc < numerator; numerator_loop_calc++){ //分子数ループ

            //////////////////
            // 分子さんの仕事場
            //////////////////

            ++numerator_calc; //分子を増やす (1/? 2/? 3/?...)


            for (long denominator_loop_calc = 0; denominator_loop_calc < denominator; denominator_loop_calc++){ //分母数ループ

                //////////////////////////////
                // 分母さんの仕事場 (計算ババア)
                //////////////////////////////

                ++denominator_calc; //分母を増やす (?/1 ?/2 ?/3...)

                chance = numerator_calc / denominator_calc * 100; //確率(％表記)を計算 分子 ÷ 分母 × 100

                rotations_Number_To_Win = 100 / chance; //当選までの平均回転数を制作 100 ÷ 確率(％表記)

                stock_Calc = default_Stock + add_Stock * rotations_Number_To_Win; //受け取るお金を制作 初期ストック + 追加ストック × 当選までの回数
                bet_Calc = bet * rotations_Number_To_Win; //使ったお金を制作 一回転の金額 × 当選までの回数

                seconds_Time_Calc = time * rotations_Number_To_Win ; //かかった時間(秒)を制作 一回転の時間 × 回数
                minutes_Time_Calc = seconds_Time_Calc / 60 ; //かかった時間(分)を制作 かかった秒数 ÷ 60
                hours_Time_Calc = minutes_Time_Calc / 60 ; //かかった時間(時間)を制作 かかった秒数 ÷ 60
                days_Time_Calc = hours_Time_Calc / 24 ; //かかった時間(日)を制作 かかった秒数 ÷ 24

                secondly_Pay_Calc = (stock_Calc - bet_Calc) / seconds_Time_Calc; //秒給を計算
                minutely_Pay_Calc = secondly_Pay_Calc * 60; //分給を計算
                hourly_Pay_Calc = minutely_Pay_Calc * 60; //時給を計算
                daily_Pay_Calc = hourly_Pay_Calc * 24; //日給を計算

                reduction_Rate = stock_Calc / bet_Calc; //還元率を計算 受け取るお金 ÷ 使ったお金
                reduction_Rate *= 100.0; //％表記にするため100倍に


                ///////////////////
                // 保存職人の仕事場
                ///////////////////

                FileWriter slot_Result = new FileWriter(slot_Name + ".txt",true);

                PrintWriter slot_Result_Writer = new PrintWriter(new BufferedWriter(slot_Result));

                try {
                    slot_Result_Writer.println("確率が " + (long)numerator_calc + "/" + (long)denominator_calc + " の場合");
                    slot_Result_Writer.println("還元率は " + reduction_Rate + " ％");
                    slot_Result_Writer.println("確率は " + chance + " ％");
                    slot_Result_Writer.println("当選までの平均回転数は " + rotations_Number_To_Win + " 回");
                    slot_Result_Writer.println("獲得金額は " + stock_Calc + " 円");
                    slot_Result_Writer.println("投資額は " + bet_Calc + " 円");
                    slot_Result_Writer.println("秒給は " + secondly_Pay_Calc + " 円");
                    slot_Result_Writer.println("分給は " + minutely_Pay_Calc + " 円");
                    slot_Result_Writer.println("時給は " + hourly_Pay_Calc + " 円");
                    slot_Result_Writer.println("日給は " + daily_Pay_Calc + " 円");
                    slot_Result_Writer.println("当選までに掛かる秒数は " + seconds_Time_Calc + " 秒");
                    slot_Result_Writer.println("当選までに掛かる分数は " + minutes_Time_Calc + " 分");
                    slot_Result_Writer.println("当選までに掛かる時間は " + hours_Time_Calc + " 時間");
                    slot_Result_Writer.println("当選までに掛かる日数は " + days_Time_Calc + " 日");
                    slot_Result_Writer.println("");
                    slot_Result_Writer.println("");
                    slot_Result_Writer.println("");

                } catch (Exception e) {
                    System.out.println("なんかよー分からんエラー吐いたってさ");
                    System.out.println("この下の文を見て勝手に解読しておくれ");
                    System.out.println(e);

                } finally { //catchされても確実に動く
                    slot_Result_Writer.close();
                }
            }

            //////////////////////////////
            // たまに働くリセット職人の仕事場
            //////////////////////////////

            denominator_calc = 0; //分母分ループしたら分母をリセットする
        }
        System.out.println("結果を全てファイルに保存しました");
        System.out.println("プログラムを終了します...");
    }
}