package com.example.uidesign01compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
fun DashBoard() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(painter = painterResource(id = R.drawable.top_background), contentDescription = null)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 36.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 24.dp)
            ) {
                Text(
                    text = "Hello", style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 16.sp,
                        color = Color(0xffffffff),
                        fontStyle = FontStyle.Normal,
                    )
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "Md. Faisal Ahammed", style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 16.sp,
                        color = Color(0xffffffff),
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight(700)
                    )
                )
            }

            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .size(100.dp)
                    .padding(start = 16.dp)
            )
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .padding(top = 190.dp)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        color = Color(0xffC8EFD6),
                        shape = RoundedCornerShape(size = 15.dp)
                    )
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(48.dp),
                    painter = painterResource(id = R.drawable.video_call),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = "Video Call",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp,
                        color = Color(0xff199b33),
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight(700)
                    )
                )
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        color = Color(0xffC8EFD6),
                        shape = RoundedCornerShape(size = 15.dp)
                    )
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(48.dp),
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = "Notification",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp,
                        color = Color(0xff199b33),
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight(700)
                    )
                )
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        color = Color(0xffC8EFD6),
                        shape = RoundedCornerShape(size = 15.dp)
                    )
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(48.dp),
                    painter = painterResource(id = R.drawable.voice_call),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = "Voice Call",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp,
                        color = Color(0xff199b33),
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight(700)
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun ViewDashBoard() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        DashBoard()
    }
}