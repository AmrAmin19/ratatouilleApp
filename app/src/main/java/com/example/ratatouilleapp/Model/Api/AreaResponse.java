package com.example.ratatouilleapp.Model.Api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AreaResponse {
    @SerializedName("meals")
    private List<Area> areas;

    public List<Area> getAreas() {
        return areas;
    }
}