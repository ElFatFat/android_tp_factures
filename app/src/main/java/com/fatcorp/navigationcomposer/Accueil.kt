package com.fatcorp.navigationcomposer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun AccueilDisplay(navController: NavController, login: String?, mdp: String?){
    if (login != null) {
        Text(text = login)
    }
    if (mdp != null) {
        Text(text = mdp)
    }

}