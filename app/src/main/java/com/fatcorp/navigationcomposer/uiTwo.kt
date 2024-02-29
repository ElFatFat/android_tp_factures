package com.fatcorp.navigationcomposer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun layoutTwo(navController: NavHostController){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Bienvenue sur LAYOUT TWO",
            fontSize = 17.sp,
            color = Color.Red,
        )
        Spacer(
            modifier = Modifier.height(65.dp)
        )
        ElevatedButton(onClick = {
            navController.navigate("Layout3")
        }) {
            Text(text = "GO TO LAYOUT 3", fontSize = 30.sp)
        }
    }
}