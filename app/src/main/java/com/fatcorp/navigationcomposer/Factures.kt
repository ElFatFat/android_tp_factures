package com.fatcorp.navigationcomposer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Switch
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

var sum: String = "Unknown";

@Composable
fun FactureDisplay(navController: NavHostController){
    var modifier = Modifier
    var quantityValue by remember{
        mutableStateOf("")
    }
    var unitValue by remember {
        mutableStateOf("")
    }
    var htValue by remember {
        mutableStateOf("")
    }
    var tvaValue by remember {
        mutableStateOf("")
    }
    var remiseValue by remember {
        mutableStateOf("")
    }
    var fideleValue by remember {
        mutableStateOf(false)
    }
    fun resetFields(){
        quantityValue = ""
        unitValue = ""
        htValue = ""
        tvaValue = ""
        remiseValue = ""
        fideleValue = false
        sum = "Unknown"
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(text = "FACTURE", fontSize = 40.sp, fontWeight = FontWeight.Black, color = Color.Black)
        Spacer(modifier.height(50.dp))
        Row {
            TextField(
                value = quantityValue,
                onValueChange = { newValue ->
                    quantityValue = newValue
                    htValue = montantHorsTaxe(quantityValue.toIntOrNull(), unitValue.toFloatOrNull()).toString()
                },
                label = { Text(text = "Quantité")}
            )
        }
        Spacer(
            modifier
                .height(10.dp)
                .fillMaxSize())
        Row {
            TextField(
                value = unitValue,
                onValueChange = { newValue ->
                    unitValue = newValue
                    htValue = montantHorsTaxe(quantityValue.toIntOrNull(), unitValue.toFloatOrNull()).toString()
                },
                label = { Text(text = "Prix unitaire")}

            )
        }
        Spacer(
            modifier
                .height(10.dp)
                .fillMaxSize())
        Row {
            if (quantityValue == "" || unitValue == ""){
                htValue = "0";
            }else{
                htValue = (quantityValue.toFloat() * unitValue.toFloat()).toString();
            }
            Text(text = "Montant Hors-Taxes : " + htValue + "€")
        }
        Spacer(
            modifier
                .height(10.dp)
                .fillMaxSize())
        Row {
            TextField(
                value = tvaValue,
                onValueChange = { newValue ->
                    tvaValue = newValue
                    sum = bigCalcul(htValue.toFloatOrNull(), tvaValue.toFloatOrNull(), remiseValue.toFloatOrNull(), fideleValue).toString()
                    Log.d("test", "Sum" + sum)
                    Log.d("test", bigCalcul(htValue.toFloatOrNull(), tvaValue.toFloatOrNull(), remiseValue.toFloatOrNull(), fideleValue).toString())
                },
                label = { Text(text = "TVA")},
                placeholder = { Text(text = "20, 15, 10, etc...")}
            )
        }
        Spacer(
            modifier
                .height(10.dp)
                .fillMaxSize()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )
        {

            Text(text = "Affilié")
            RadioButton(
                fideleValue,
                onClick = { fideleValue = !fideleValue;
                    sum = bigCalcul(htValue.toFloatOrNull(), tvaValue.toFloatOrNull(), remiseValue.toFloatOrNull(), fideleValue).toString()
                }
            )
            Text(text = "Non-affilié")
            RadioButton(
                !fideleValue,
                onClick = { fideleValue = !fideleValue;
                    sum = bigCalcul(htValue.toFloatOrNull(), tvaValue.toFloatOrNull(), remiseValue.toFloatOrNull(), fideleValue).toString()
                }
            )

        }
        Spacer(
            modifier
                .height(10.dp)
                .fillMaxSize())
        Row {
            TextField(
                value = remiseValue,
                onValueChange = { newValue ->
                    remiseValue = newValue
                    sum = bigCalcul(htValue.toFloatOrNull(), tvaValue.toFloatOrNull(), remiseValue.toFloatOrNull(), fideleValue).toString()
                },
                label = {Text(text = "Remise")},
                placeholder = { Text(text = "20, 15, 10, etc...")}

            )
        }
        Spacer(
            modifier
                .height(10.dp)
                .fillMaxSize())
        
        Row {
            Button(onClick = { resetFields() }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
            )) {
                Text(text = "Reset", fontSize = 17.sp)
            }
            Spacer(modifier.width(30.dp))
            Button(
                onClick =
                {
                    navController.navigate("result/$sum")

                }) {
                Text(text = "Valider", fontSize = 17.sp)

            }
        }

    }
}

fun montantHorsTaxe(quantity: Int? = 0, unit: Float? = 0.0f, montantHT: Float? = 0.0f, tauxTVA: Float? = 0.0f, remise: Float? = 0.0f, fidele: Boolean = false): Any {
    Log.d("montantHorsTaxe", "quantity: $quantity, unit: $unit")
    bigCalcul(montantHT, tauxTVA, remise, fidele)
    if(quantity == null || unit == null)
        return 0;
    return quantity * unit;
}

fun bigCalcul(montantHT: Float? = 0.0f, tauxTVA: Float? = 0.0f, remise: Float? = 0.0f, fidele: Boolean = false): Any {
    Log.d("bigCalcul", "montantHT: $montantHT, tauxTVA: $tauxTVA, remise: $remise, fidele: $fidele")
    if(montantHT == null || tauxTVA == null || remise == null)
        return 0;
    return if(fidele)
        montantHT * (1 + tauxTVA / 100) * (1 - remise / 100) * 0.95;
    else
        montantHT * (1 + tauxTVA / 100) * (1 - remise / 100);
}

