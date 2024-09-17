package com.example.myrecipe

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {// connecting dataside to ui side
  private  val _categorieState = mutableStateOf(RecipeState())                              // global variable
// we can use mutablestateof with all type of data types ..we can use data class also with mutable state  of
    val categoriesState: State<RecipeState> = _categorieState // public variable which will be access to other classes
// we use state bcz we want to change or update the list
    init {
        fetchCategories()
    }


     private fun fetchCategories(){
         viewModelScope.launch {// Coroutine allow to run routine into background
      try {
          val response = recipeService.getCategories()
          _categorieState.value=_categorieState.value.copy(
              list = response.categories
              ,loading =false,
              error = null
          )

  }catch (e:Exception){
       _categorieState.value = _categorieState.value.copy(

           loading = false,
           error = "Error fetching Categories ${e.message}"
       )
  }


         }
     }



    data class RecipeState // object type data class
        (
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null // string with question mark make it nullable
    )
}
