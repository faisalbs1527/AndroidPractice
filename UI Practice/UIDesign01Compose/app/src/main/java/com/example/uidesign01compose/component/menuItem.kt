package com.example.uidesign01compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uidesign01compose.R

@Composable
fun TopItem(title: String, image: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(8.dp)
                .size(48.dp),
            painter = painterResource(id = image),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = title,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp,
                color = Color(0xff199b33),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight(700)
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomItem(title: String, image: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedCard(
            onClick = { /*TODO*/ },
            border = CardDefaults.outlinedCardBorder(false),
            colors = CardDefaults.outlinedCardColors(containerColor = Color(0xFFC8EFD6))
        ) {
            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .size(64.dp),
                painter = painterResource(id = image),
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
            text = title,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 14.sp,
                color = Color.Black,
                fontStyle = FontStyle.Normal
            )
        )
    }
}

@Preview
@Composable
private fun ShowTopItem() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopItem("Voice Call", R.drawable.voice_call)
            Spacer(modifier = Modifier.size(40.dp))
            BottomItem("Inbox", R.drawable.ic_1)
        }
    }
}