package com.fatcorp.navigationcomposer

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController



@Composable
fun LoginDisplay(navController: NavHostController){
    val context = LocalContext.current
    val modifier = Modifier
    var loginValue by remember{
        mutableStateOf("")
    }
    var mdpValue by remember {
        mutableStateOf("")
    }

    fun checkLogin(): Boolean{
        if (loginValue == "azerty" && mdpValue == "azerty"){
            return true
        }else{
            return false;
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(text = "Authentification", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.Red)
        Spacer(
            modifier
                .height(75.dp)
                .fillMaxSize()
        )
        
        Text(text = "Votre login", fontSize = 15.sp)
        TextField(
            value = loginValue,
            onValueChange = { loginValue = it },
            modifier.padding(5.dp),
            placeholder = {
                Text(text = "Login...")
            }
        )
        Spacer(modifier.height(20.dp))
        Text(text = "Votre mot de passe", fontSize = 15.sp)
        TextField(
            value = mdpValue,
            onValueChange = { mdpValue = it },
            modifier.padding(5.dp),
            placeholder = {
                Text(text = "Mdp...")
            }
        )
        Button(
            onClick = {
                if (checkLogin()){
                    navController.navigate("Facture")
                    Toast.makeText(context, "Succès !", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(context, "Erreur d'identifiants", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = "Valider", fontSize = 17.sp)
        }
    }
}