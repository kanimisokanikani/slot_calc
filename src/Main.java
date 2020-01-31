import java.util.concurrent.TimeUnit;

public class Main {
    public static void main (String [] args) throws InterruptedException {

        /////////////
        // 変数作成 //
        /////////////

        long default_Stock;    //初期ストック
        long add_Stock;        //一回転で増えるストック
        long bet;              //一回の金額
        long number_Of_Times;  //回数
        double time = 0.0;     //一回転の時間
        double reduction_Rate; //還元率  ％表記に使う

        //計算専用
        long stock_Calc;                //add_Stockを足したストック
        long bet_Calc;                  //一回の金額
        double time_Calc;                 //一回転の時間
        long number_Of_Times_calc = 0;  //回数


        /////////////
        // 設定入力 //
        /////////////

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


        //////////
        // 計算 //
        /////////

        for (int i = 0; i < number_Of_Times; i++){
            ++number_Of_Times_calc; //回転数を増やす (1回 2回 3回...)

            stock_Calc = default_Stock + add_Stock * number_Of_Times_calc; //受け取るお金を制作 初期ストック + 追加ストック × 回数
            bet_Calc = bet * number_Of_Times_calc; //使ったお金を制作 一回転の金額 × 回数
            time_Calc = time * number_Of_Times_calc ; //かかった時間を制作 一回転の時間 × 回数

            reduction_Rate = (stock_Calc + 0.0) / (bet_Calc + 0.0); //還元率を計算 受け取るお金 ÷ 使ったお金
            reduction_Rate *= 100.0; //％表記にするため100倍に

            System.out.print(number_Of_Times_calc + "回転で当選した場合 還元率は " + reduction_Rate + " ％で、");
            System.out.println("掛かる時間は " + time_Calc + " 秒です");

            TimeUnit.MILLISECONDS.sleep(1);
        }
    }
}
