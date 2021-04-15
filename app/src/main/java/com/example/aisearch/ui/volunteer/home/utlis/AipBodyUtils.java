package com.example.aisearch.ui.volunteer.home.utlis;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import com.example.aisearch.ui.volunteer.home.zwactivity.Contacts;

public class AipBodyUtils {
    private static AipBodyAnalysis aipBodyAnalysis;
    public static AipBodyAnalysis getAipBodyAnalysisInstance(){
        if (aipBodyAnalysis==null){

            aipBodyAnalysis = new AipBodyAnalysis(Contacts.APP_IP,Contacts.APP_KEY,Contacts.SECRET_KEY);
            aipBodyAnalysis.setConnectionTimeoutInMillis(2000);
            aipBodyAnalysis.setSocketTimeoutInMillis(60000);
        }
        return aipBodyAnalysis;
    }
}

