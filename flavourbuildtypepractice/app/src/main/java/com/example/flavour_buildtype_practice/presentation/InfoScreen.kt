package com.example.flavour_buildtype_practice.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flavour_buildtype_practice.BuildConfig

@Composable
fun InfoScreen() {
    var flavour by remember {
        mutableStateOf("")
    }
    var buildType by remember {
        mutableStateOf("")
    }
    var url by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (BuildConfig.IS_PAID_VERSION) {
                Text(text = "This is paid version", modifier = Modifier.padding(bottom = 16.dp))
            } else {
                Text(text = "This is free version", modifier = Modifier.padding(bottom = 16.dp))
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Product Flavour: ",
                    color = Color.Blue,
                    fontSize = 20.sp
                )
                Text(
                    text = flavour,
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier.padding(top = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Build Type: ",
                    color = Color.Blue,
                    fontSize = 20.sp
                )
                Text(
                    text = buildType,
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier.padding(top = 36.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "BASE_URL: ",
                    color = Color.Blue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600)
                )
                Text(
                    text = url,
                    fontSize = 20.sp
                )
            }
            Button(onClick = {
                flavour = BuildConfig.FLAVOR
                buildType = BuildConfig.BUILD_TYPE
                url = BuildConfig.BASE_URL
            }, modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "Show Info",
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    InfoScreen()
}