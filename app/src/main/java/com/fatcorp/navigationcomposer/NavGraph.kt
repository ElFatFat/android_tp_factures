package com.fatcorp.navigationcomposer

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Nav(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Login"){
        composable(route = "Facture"){
            FactureDisplay(navController)
        }
        composable(route = "login"){
            LoginDisplay(navController)
        }
        composable(route = "result/{sum}",
            arguments = listOf(
                navArgument(name="sum"){
                    type = NavType.StringType
                }
            )
        ){
            backstackEntry ->
            ResultDisplay(
                navController,
                sum = backstackEntry.arguments?.getString("sum"),
            )
        }
    }
}