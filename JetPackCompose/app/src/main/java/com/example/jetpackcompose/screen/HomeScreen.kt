package com.example.jetpackcompose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xFFBB86FC)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_quiz), // Replace with your quiz icon resource
                contentDescription = "Quiz Icon",
                modifier = Modifier.size(64.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LanguageCard(
            imageRes = R.drawable.ic_cpp, // Replace with your C++ icon resource
            languageName = "C++"
        )

        Spacer(modifier = Modifier.height(8.dp))

        LanguageCard(
            imageRes = R.drawable.ic_java, // Replace with your Java icon resource
            languageName = "Java"
        )

        Spacer(modifier = Modifier.height(8.dp))

        LanguageCard(
            imageRes = R.drawable.ic_python, // Replace with your Python icon resource
            languageName = "Python"
        )
    }
}

@Composable
fun LanguageCard(imageRes: Int, languageName: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        background = Color.White,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "$languageName Icon",
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = languageName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6200EE) // Purple color
            )
        }
    }
}