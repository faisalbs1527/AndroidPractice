package com.example.uidesign01compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uidesign01compose.R
import com.example.uidesign01compose.component.BottomItem
import com.example.uidesign01compose.component.TopItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoard() {
    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.top_background),
                contentDescription = null
            )
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
                        text = "Md. Faisal Ahammed",
                        style = MaterialTheme.typography.bodySmall.copy(
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

                TopItem(
                    "Video Call", R.drawable.video_call, modifier = Modifier
                        .weight(0.33f)
                        .padding(8.dp)
                        .size(100.dp)
                        .background(
                            color = Color(0xffC8EFD6),
                            shape = RoundedCornerShape(size = 15.dp)
                        )
                )
                TopItem(
                    "Notification", R.drawable.notification, modifier = Modifier
                        .weight(0.33f)
                        .padding(8.dp)
                        .size(100.dp)
                        .background(
                            color = Color(0xffC8EFD6),
                            shape = RoundedCornerShape(size = 15.dp)
                        )
                )
                TopItem(
                    "Voice Call", R.drawable.voice_call, modifier = Modifier
                        .weight(0.33f)
                        .padding(8.dp)
                        .size(100.dp)
                        .background(
                            color = Color(0xffC8EFD6),
                            shape = RoundedCornerShape(size = 15.dp)
                        )
                )
            }
        }
        SearchBar(
            query = "",
            onQueryChange = {},
            onSearch = {},
            active = false,
            onActiveChange = {},
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .fillMaxWidth()
                .height(50.dp),
            placeholder = {
                Text(
                    text = "Search For...",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF959795)
                    )
                )
            },
            tonalElevation = 15.dp,
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.search_icon), contentDescription = "",
                    modifier = Modifier.size(42.dp)
                )
            }

        ) {

        }

        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF55985D),
                            Color(0xFF80BB8A),
                            Color(0xFF49A45B)
                        )
                    ),
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.weight(.6f)
                        .padding(vertical = 48.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "To Get Unlimited", style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 14.sp,
                            color = Color(0xffffffff),
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight(700)
                        )
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = "Upgrade Your Account",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 14.sp,
                            color = Color(0xffffffff),
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight(700)
                        )
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "",
                    modifier = Modifier
                        .weight(.4f)
                        .padding(vertical = 6.dp, horizontal = 6.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .fillMaxWidth()
        ) {
            BottomItem(title = "Inbox", image = R.drawable.ic_1, modifier = Modifier
                .weight(.25F)
                .padding(8.dp))
            BottomItem(title = "Maps", image = R.drawable.ic_2, modifier = Modifier
                .weight(.25F)
                .padding(8.dp))
            BottomItem(title = "Chats", image = R.drawable.ic_3, modifier = Modifier
                .weight(.25F)
                .padding(8.dp))
            BottomItem(title = "Report", image = R.drawable.ic_4, modifier = Modifier
                .weight(.25F)
                .padding(8.dp))
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            BottomItem(title = "Calendar", image = R.drawable.ic_5, modifier = Modifier
                .weight(.25F)
                .padding(8.dp))
            BottomItem(title = "Tips", image = R.drawable.ic_6, modifier = Modifier
                .weight(.25F)
                .padding(8.dp))
            BottomItem(title = "Setting", image = R.drawable.ic_7, modifier = Modifier
                .weight(.25F)
                .padding(8.dp))
            BottomItem(title = "More", image = R.drawable.ic_8, modifier = Modifier
                .weight(.25F)
                .padding(8.dp))
        }
    }
}

@Preview
@Composable
private fun ViewDashBoard() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        DashBoard()
    }
}