package com.techshift.crm.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.techshift.crm.R
import com.techshift.crm.ui.theme.CRMTheme

@Composable
fun HomeScreen(paddingValues: PaddingValues) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.LightGray)

    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(12.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    AsyncImage(
                        modifier = Modifier.width(50.dp).height(50.dp).clip(CircleShape),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://www.gravatar.com/avatar/2c7d99fe281ecd3bcd65ab915bac6dd5?s=250")
                            .placeholder(R.drawable.ic_person_generic)
                            .error(R.drawable.ic_person_generic)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Profile Picture",
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column() {
                        Text(text = "Hello", fontSize = 16.sp)
                        Text(text = "Carlo Payabyab",
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                platformStyle = PlatformTextStyle(
                                    emojiSupportMatch =  EmojiSupportMatch.Default
                                )
                            ))
                    }
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification_bell),
                    contentDescription = "Notifications"
                )
            }

            Spacer(
                modifier = Modifier.height(26.dp)
            )

            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier.fillMaxWidth().height(150.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(12.dp),
                ) {
                    Text(text = ">>>>>>>>")
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    CRMTheme {
        HomeScreen(paddingValues = PaddingValues(0.dp))
    }
}
