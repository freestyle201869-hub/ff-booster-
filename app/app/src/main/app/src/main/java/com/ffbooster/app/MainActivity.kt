package com.ffbooster.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FFBoosterApp() }
    }
}

private val RedColor = Color(0xFFE50914)
private val BlackColor = Color(0xFF000000)
private val DarkGray = Color(0xFF151515)

@Composable
fun FFBoosterApp() {
    val colors = darkColorScheme(
        primary = RedColor,
        onPrimary = Color.White,
        background = BlackColor,
        onBackground = Color.White,
        surface = DarkGray,
        onSurface = Color.White
    )
    MaterialTheme(colorScheme = colors) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val nav = rememberNavController()
                NavHost(navController = nav, startDestination = "home") {
                    composable("home") { HomeScreen(nav) }
                    composable("sensitivity") { SimpleScreen("الحساسية", nav) }
                    composable("smart") { SimpleScreen("الحساسية الذكية", nav) }
                    composable("boost") { SimpleScreen("تحسين الأداء", nav) }
                    composable("ping") { SimpleScreen("اختبار البنغ", nav) }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(nav: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "FF BOOSTER",
            color = RedColor,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "إعدادات الحساسية وتحليل الاتصال",
            color = Color.White,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        MenuButton("الحساسية") { nav.navigate("sensitivity") }
        MenuButton("الحساسية الذكية") { nav.navigate("smart") }
        MenuButton("تحسين الأداء") { nav.navigate("boost") }
        MenuButton("اختبار البنغ") { nav.navigate("ping") }
    }
}

@Composable
fun MenuButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = RedColor)
    ) {
        Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SimpleScreen(title: String, nav: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = RedColor,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "هذه الصفحة قيد التطوير",
            color = Color.White,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
        MenuButton("رجوع") { nav.popBackStack() }
    }
}
