package com.example.aisearch.util.httpokutil;


public class UserHttpUtils extends GenericHttpUtils {

    public static final String userBaseUrl = "http://c.3g.163.com/nc/video/list/VAP4BFR16/y/0-10.html";


    //获得单个User对象
    public static String getUser(String url){
        String user;
        user = fromJson(getResponse(userBaseUrl+url),String.class);
        return user;
    }



//    public static List<Hotel> getHotelList(String url){
//        List<Hotel> hotelList;
//        hotelList = fromJson(getResponse(userBaseUrl+url),new TypeToken<List<Hotel>>(){}.getType());
//        return hotelList;
//    }
//
//
//    public static List<Coupon> getCouponList(String url){
//        List<Coupon> couponList;
//        couponList = fromJson(getResponse(userBaseUrl+url),new TypeToken<List<Coupon>>(){}.getType());
//        return couponList;
//    }
//
//
//    public static List<Contacts> getContactsList(String url){
//        List<Contacts> contactsList;
//        contactsList = fromJson(getResponse(userBaseUrl+url),new TypeToken<List<Contacts>>(){}.getType());
//        return contactsList;
//    }
//
//
//    public static List<MailingAddress> getMailingAddressList(String url){
//        List<MailingAddress> mailingAddressList;
//        mailingAddressList = fromJson(getResponse(userBaseUrl+url),new TypeToken<List<MailingAddress>>(){}.getType());
//        return mailingAddressList;
//    }
//
//
//    public static List<Receipt> getReceiptList(String url){
//        List<Receipt> receiptList;
//        receiptList = fromJson(getResponse(userBaseUrl+url),new TypeToken<List<Receipt>>(){}.getType());
//        return receiptList;
//    }


}
