package com.example.sunrise_system.myageapp;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sunrise-system on 2017/12/26.
 */

public class Year_Check_Sub {

    public Year_Check_Sub() {

    }

    //西暦年月日チェック
    public static int[] check1(int[] data) {
        Date now = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(now);
        int year = cl.get(Calendar.YEAR);
        if (data[0] >= 1868 && data[0] <= year) {

            //入力された数値が未来かどうかのチェックメソッドを呼び出す
            mirai(data);

            //うるう年かどうかを判定する計算メソッドを呼び出す
            uru(data);

            //うるう年の場合はうるう年用の処理に移行
            if (data[5] == 1) {
                urutsuki(data);

                //平年の場合は月日のチェックに移行
            } else if (data[5] == 0) {
                table(data);
            }

            //どちらにも該当しない場合入力年のエラー
        } else {
            data[8] = 1;
        }
        return data;
    }


    //和暦年月日チェック
    public static int[] check2(int[] data) {
        Date now = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(now);
        int year = cl.get(Calendar.YEAR);
        switch (data[3]) {


            case 1://明治の場合
                if ((data[4] == 0) || (data[4] > 45) || (data[4] == 45 && (data[1] >= 7 && data[2] > 30))) {
                    //System.out.println("明治エラー");
                    data[7] = 1;
                } else {
                    data[0] = data[4] + 1867;
                }
                break;


            case 2://大正の場合
                if ((data[4] == 0) || (data[4] == 1 && (data[1] <= 7 && data[2] < 30))
                        || (data[4] == 15 && data[1] >= 12 && data[2] > 25) || (data[4] > 15)) {
                    //System.out.println("大正エラー");
                    data[7] = 1;
                } else {
                    data[0] = data[4] + 1911;
                }
                break;


            case 3://昭和の場合
                if ((data[4] == 0) || (data[4] == 1 && (data[1] <= 12 && data[2] < 25))
                        || (data[4] == 64 && data[1] >= 1 && data[2] > 7) || (data[4] > 64)) {
                    //System.out.println("昭和エラー");
                    data[7] = 1;
                } else {
                    data[0] = data[4] + 1925;
                }
                break;


            case 4://平成の場合
                int heisei = data[4] + 1988;
                if ((heisei > year || heisei == 0) || (heisei == 1989 && (data[1] == 1 && data[2] < 8))) {
                    //System.out.println("平成エラー");
                    data[7] = 1;
                } else {
                    data[0] = data[4] + 1988;
                }
                break;

            default:
                break;
        }
        return check1(data);
    }


    //閏年計算
    public static int[] uru(int[] data) {
        if (data[0] % 4 == 0 && data[0] % 100 != 0 || data[0] % 400 == 0) {
            data[5] = 1; //閏年なので閏年sw = 1をセット
        } else {
            data[5] = 0; //平年なので閏年sw = 0をセット
        }
        return data;
    }


    //閏年だった場合の月日判定の処理
    public static int[] urutsuki(int[] data) {

        //該当する場合日付の入力エラー
        if ((data[1] == 2) && (data[2] <= 0 || data[2] > 29)) {
            data[11] = 1;

            //該当する場合は日付エラー無し
        } else if ((data[1] == 2) && (data[2] >= 1 || data[2] <= 29)) {
            data[11] = 0;

            //2月ではない場合は通常の日付チェックへ移行
        } else {
            table(data);

        }
        return data;
    }


    //入力された月日が正しいかチェック
    public static int[] table(int[] data) {
        int month = data[1];
        int day = data[2];

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day <= 0 || day > 31) {
                data[11] = 1;
            }

        } else {
            if (month == 2) {
                if (day <= 0 || day > 28) {
                    data[11] = 1;
                }

            } else {
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day <= 0 || day > 30) {
                        data[11] = 1;
                    }

                } else {
                    data[10] = 1;
                }
            }
        }
        return data;
    }


    //入力された数値が未来かどうかをチェック
    public static int[] mirai(int[] data) {
        Date now = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(now);
        table(data);
        int year = cl.get(Calendar.YEAR);
        int month = cl.get(Calendar.MONTH) + 1;
        int day = cl.get(Calendar.DAY_OF_MONTH);

        //該当する場合未来の数値が入力されているためエラー
        if (data[0] > year || data[0] == year && (data[1] > month || data[1] == month && data[2] > day)) {
            data[9] = 1;
        }
        return data;
    }
}

