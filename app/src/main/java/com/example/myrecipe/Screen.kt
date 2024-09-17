package com.example.myrecipe

sealed class Screen(val route : String){//sealed is used when type is confirmed to one of the subclass
    object RecipeScreen:Screen("recipescreen")
    object DetailScreen:Screen("detailscreen")

}