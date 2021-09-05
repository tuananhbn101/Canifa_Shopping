package com.example.canifa_shop.Helper;

import java.text.DecimalFormat;

public class Function {
    public  static String permissionAdmin = "Admin";
    public  static String permissionEmployee = "Employee";
    public static String decimalFormatMoney(long money){
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        return decimalFormat.format(money);
    }
    public static int pointsPlus(int money){
        return money*5/100;
    }
    public static String typeCustomer(int point){
        if(point<=100000&&point<200000){
            return "Đồng";
        }else if(point>=200000&&point<5000000){
            return "Bạc";
        } else if(point>=500000&&point<10000000){
            return "Vàng";
        } else if (point>=1000000){
            return "Kim cương";
        }
        return "Mới";
    }
    public static String voucher(String type){
        if(type.equals("Đồng")){
            return "10";
        }else if(type.equals("Bạc")){
            return "15";
        }else if(type.equals("Vàng")){
            return "20";
        }else if(type.equals("Kim cương")){
            return "30";
        }
        return "";
    }

}
