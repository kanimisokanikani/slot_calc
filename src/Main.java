import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main (String [] args) throws IOException, InterruptedException {

        /////////////
        // 変数作成 //
        /////////////

        String slot_Name;
        long default_Stock;    //初期ストック
        long add_Stock;        //一回転で増えるストック
        long bet;              //一回の金額
        long number_Of_Times;  //回数
        double time = 0.0;     //一回転の時間
        double reduction_Rate; //還元率  ％表記に使う

        //計算専用
        long stock_Calc;                //add_Stockを足したストック
        long bet_Calc;                  //一回の金額
        double seconds_Time_Calc;       //当選までの時間 (秒)
        double minutes_Time_Calc;       //当選までの時間 (分)
        double hours_Time_Calc;         //当選までの時間 (時間)
        double secondly_Pay_Calc;       //秒給
        double minutely_Pay_Calc;       //分給
        double hourly_Pay_Calc;         //時給
        long number_Of_Times_Calc = 0;  //回数


        /////////////
        // 設定入力 //
        /////////////

        System.out.println("スロット名を入力してください");

        while (true) {
            slot_Name = new java.util.Scanner(System.in).nextLine();
            if (slot_Name.equals("")) { //何も入力されていない場合ループ
                continue;
            }
            break;
        }

        System.out.println("初期ストックを入力してください");
        default_Stock = new java.util.Scanner(System.in).nextLong();

        System.out.println("一回転で増えるストックを入力してください");
        add_Stock = new java.util.Scanner(System.in).nextLong();

        System.out.println("一回の金額を入力してください");
        bet = new java.util.Scanner(System.in).nextLong();

        System.out.println("一回転の時間を少数で入力してください (1秒の場合は1.0)");
        time = new java.util.Scanner(System.in).nextDouble();

        System.out.println("回す回数を入力してください");
        number_Of_Times = new java.util.Scanner(System.in).nextLong();


        ///////////////////
        // txtファイル生成 //
        ///////////////////

        FileWriter slot_Setting = new FileWriter(slot_Name); //スロット名のファイルを制作

        PrintWriter slot_Setting_Writer = new PrintWriter(new BufferedWriter(slot_Setting)); //ファイル書き込みオブジェクトを生成

        slot_Setting_Writer.println("スロット名 " + slot_Name);
        slot_Setting_Writer.println("初期ストック " + default_Stock + " 円");
        slot_Setting_Writer.println("追加ストック " + add_Stock + " 円");
        slot_Setting_Writer.println("一回転の金額 " + bet + " 円");
        slot_Setting_Writer.println("一回転の時間 " + time + " 秒");
        slot_Setting_Writer.println("回転数 " + time + " 回");
        slot_Setting_Writer.println("");
        slot_Setting_Writer.println("");
        slot_Setting_Writer.println("");
        slot_Setting_Writer.close();


        //////////
        // 計算 //
        /////////

        for (int i = 0; i < number_Of_Times; i++){

            //////////////////
            // 計算職人の仕事場
            //////////////////

            ++number_Of_Times_Calc; //回転数を増やす (1回 2回 3回...)

            stock_Calc = default_Stock + add_Stock * number_Of_Times_Calc; //受け取るお金を制作 初期ストック + 追加ストック × 回数
            bet_Calc = bet * number_Of_Times_Calc; //使ったお金を制作 一回転の金額 × 回数

            seconds_Time_Calc = time * number_Of_Times_Calc ; //かかった時間(秒)を制作 一回転の時間 × 回数
            minutes_Time_Calc = seconds_Time_Calc / 60 ; //かかった時間(分)を制作 かかった秒数 ÷ 60
            hours_Time_Calc = minutes_Time_Calc / 60 ; //かかった時間(時間)を制作 かかった秒数 ÷ 60

            secondly_Pay_Calc = (stock_Calc - bet_Calc) / seconds_Time_Calc; //秒給を計算
            minutely_Pay_Calc = secondly_Pay_Calc * 60; //分給を計算
            hourly_Pay_Calc = minutely_Pay_Calc * 60; //時給を計算

            reduction_Rate = (stock_Calc + 0.0) / (bet_Calc + 0.0); //還元率を計算 受け取るお金 ÷ 使ったお金
            reduction_Rate *= 100.0; //％表記にするため100倍に


            ///////////////////
            // 保存職人の仕事場
            ///////////////////

            FileWriter slot_Result = new FileWriter(slot_Name,true);

            PrintWriter slot_Result_Writer = new PrintWriter(new BufferedWriter(slot_Result));

            slot_Result_Writer.println(number_Of_Times_Calc + "回転で当選した場合");
            slot_Result_Writer.println("還元率は " + reduction_Rate + " ％");
            slot_Result_Writer.println("獲得金額は " + stock_Calc + " 円");
            slot_Result_Writer.println("投資額は " + bet_Calc + " 円");
            slot_Result_Writer.println("秒給は " + secondly_Pay_Calc + " 円");
            slot_Result_Writer.println("分給は " + minutely_Pay_Calc + " 円");
            slot_Result_Writer.println("時給は " + hourly_Pay_Calc + " 円");
            slot_Result_Writer.println("掛かる秒数は " + seconds_Time_Calc + " 秒");
            slot_Result_Writer.println("掛かる分数は " + minutes_Time_Calc + " 分");
            slot_Result_Writer.println("掛かる時間は " + hours_Time_Calc + " 時間");
            slot_Result_Writer.println("");
            slot_Result_Writer.close();
        }
        System.out.println("結果を全てファイルに保存しました");
        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println("プログラムを終了します...");
        TimeUnit.MILLISECONDS.sleep(2000);
        System.exit(0);
    }
}
