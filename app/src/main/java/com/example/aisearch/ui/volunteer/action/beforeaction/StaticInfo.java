package com.example.aisearch.ui.volunteer.action.beforeaction;

public  class  StaticInfo {
    public static GatherLocation gatherLocation;

    public static GatherLocation getGatherLocation() {
        return gatherLocation;
    }

    public static void setGatherLocation(GatherLocation gatherLocation) {
        StaticInfo.gatherLocation = gatherLocation;
    }
}
