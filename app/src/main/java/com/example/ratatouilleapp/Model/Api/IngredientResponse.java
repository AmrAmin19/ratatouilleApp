package com.example.ratatouilleapp.Model.Api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class IngredientResponse {
    @SerializedName("meals")
    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
