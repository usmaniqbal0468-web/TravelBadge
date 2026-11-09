package com.example.travelbadge

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

private const val TAG = "MainActivity"

// ---- Colour palette (change it to make the badge truly yours) ----
private val Navy = Color(0xFF0F2A43)
private val Cream = Color(0xFFFFF8F0)
private val Coral = Color(0xFFFF6B57)
private val SoftSand = Color(0xFFF3E6D6)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                TravelBadgeScreen()
            }
        }
    }
}

@Composable
fun TravelBadgeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Navy)
            .systemBarsPadding()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Cream),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                HeaderSection(
                    destination = "Lisbon",
                    country = "Portugal",
                    tagline = "MY FAVORITE DESTINATION"
                )
                // space reserved for the avatar that overlaps the banner
                Spacer(modifier = Modifier.height(56.dp))

                TravelerInfo(
                    name = "M Usman Iqbal",
                    title = "Certified Wanderer  •  Level 7"
                )

                Spacer(modifier = Modifier.height(16.dp))

                StatsRow()

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    InfoRow("📍", "Lisbon,Portugal")
                    InfoRow("📅", "Last visit: April 2026")
                    InfoRow("❤️", "Loves: beaches, temples ")
                    InfoRow("📞", "+351 912 000 678")
                    InfoRow("🔗", "@usman_468")
                    InfoRow("✉️", "usman468@gmail.com")
                }

                Spacer(modifier = Modifier.height(16.dp))
                BarcodeFooter()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

/** Banner photo + gradient + destination text + overlapping round avatar */
@Composable
fun HeaderSection(destination: String, country: String, tagline: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        // 1) Banner image (Image composable #1)
        Image(
            painter = painterResource(id = R.drawable.destination),
            contentDescription = "Landscape of $destination",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

        // 2) Dark gradient so the text is readable
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xCC000000))
                    )
                )
        )

        // 3) Destination text over the banner
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 24.dp, bottom = 16.dp)
        ) {
            Text(
                text = tagline,
                color = Coral,
                fontSize = 11.sp,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = destination,
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(text = country, color = Color.White, fontSize = 16.sp)
        }

        // 4) Weather chip, top-right
        Text(
            text = "☀️ 18°C  Sunny",
            color = Navy,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White.copy(alpha = 0.9f))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        )

        // 5) Round profile photo (Image composable #2), overlapping banner and body
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-24).dp, y = 48.dp)
                .zIndex(1f)
                .size(96.dp)
                .clip(CircleShape)
                .border(4.dp, Cream, CircleShape)
        )
    }
}

@Composable
fun TravelerInfo(name: String, title: String) {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Text(
            text = name,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Navy
        )
        Text(text = title, fontSize = 14.sp, color = Coral, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun StatsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StatChip("🌸", "Best season", "Spring", Modifier.weight(1f))
        StatChip("✈️", "Flight time", "14 h", Modifier.weight(1f))
        StatChip("🔁", "Visits", "3x", Modifier.weight(1f))
    }
}

@Composable
fun StatChip(emoji: String, label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(SoftSand)
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = emoji, fontSize = 20.sp)
        Text(text = value, fontWeight = FontWeight.Bold, color = Navy, fontSize = 16.sp)
        Text(text = label, color = Navy.copy(alpha = 0.7f), fontSize = 11.sp, textAlign = TextAlign.Center)
    }
}

/** One info line: an emoji "icon" + text (no icon library needed) */
@Composable
fun InfoRow(emoji: String, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = emoji,
            fontSize = 18.sp,
            modifier = Modifier.width(28.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 14.sp, color = Navy)
    }
}

/** Decorative boarding-pass style barcode */
@Composable
fun BarcodeFooter() {
    val widths = listOf(2, 4, 1, 3, 2, 5, 1, 2, 4, 1, 3, 2, 1, 4, 2, 3, 1, 5, 2, 1, 3, 2, 4, 1)
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.height(36.dp)
        ) {
            widths.forEach { w ->
                Box(
                    modifier = Modifier
                        .width(w.dp)
                        .fillMaxHeight()
                        .background(Navy)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "TRAVEL BADGE  №0001",
            fontSize = 10.sp,
            letterSpacing = 3.sp,
            color = Navy.copy(alpha = 0.6f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TravelBadgePreview() {
    MaterialTheme {
        TravelBadgeScreen()
    }
}
