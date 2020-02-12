package slot_calc.tool;

public class safe_Scanner {
    public static long long_Scanner() {
        long safe_Long_Number;

        while (true){
            try {
                safe_Long_Number = new java.util.Scanner(System.in).nextLong(); //ありとあらゆる例外を止める
            } catch (Exception e) {                                        //自作の例外を作ろうと思ったけど、
                System.out.println("数字を入力してください");                 //分からなかったため保留。
                continue;
            }

            if (0 > safe_Long_Number) { //-1以下だったらループ
                System.out.println("0以上の数字を入力してください");
                continue;
            }
            break;
        }
        return safe_Long_Number; //入力した数字を返還
    }

    public static double double_Scanner() {
        double safe_Double_Number;

        while (true){
            try {
                safe_Double_Number = new java.util.Scanner(System.in).nextDouble(); //ありとあらゆる例外を止める
            } catch (Exception e) {                                        //自作の例外を作ろうと思ったけど、
                System.out.println("数字を入力してください");                 //分からなかったため保留。
                continue;
            }

            if (0.0 > safe_Double_Number) { //-1以下だったらループ
                System.out.println("0.0以上の数字を入力してください");
                continue;
            }
            break;
        }
        return safe_Double_Number; //入力した数字を返還
    }
}
