package slot_calc.tool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class file_Writer {
    public static void slot_Setting_Writer_method
            (String slot_Name, long default_Stock, long add_Stock, long bet, double time, long numerator, long denominator) throws IOException {
        //スロットのデータをメソッドに輸送

        FileWriter slot_Setting = new FileWriter(slot_Name + ".txt"); //スロット名のファイルを制作

        PrintWriter slot_Setting_Writer = new PrintWriter(new BufferedWriter(slot_Setting)); //ファイル書き込みオブジェクトを生成

        try { //例外が起きたときに、エラー文を出す処理
            slot_Setting_Writer.println("スロット名 " + slot_Name);
            slot_Setting_Writer.println("初期ストック " + default_Stock + " 円");
            slot_Setting_Writer.println("追加ストック " + add_Stock + " 円");
            slot_Setting_Writer.println("一回転の金額 " + bet + " 円");
            slot_Setting_Writer.println("一回転の時間 " + time + " 秒");
            slot_Setting_Writer.println("確率 " + numerator + "/" + denominator);
            slot_Setting_Writer.println("");
            slot_Setting_Writer.println("確率をすぐ見つけたいときは、");
            slot_Setting_Writer.println("Control or CTRL + F か command + F で");
            slot_Setting_Writer.println("100%以上 のように還元率を検索してみてください");
            slot_Setting_Writer.println("もしくは 2/5 のように分数で検索してみてください");
            slot_Setting_Writer.println("");
            slot_Setting_Writer.println("");
            slot_Setting_Writer.println("");

        } catch (Exception e) { //エラーが起きたら文章を吐き出す
            System.out.println("なんかよー分からんエラー吐いたってさ");
            System.out.println("この下の文を見て勝手に解読しておくれ");
            System.out.println(e);

        } finally { //catchされても確実に動く
            slot_Setting_Writer.close();//書き込み終了処理
        }
    }
}
