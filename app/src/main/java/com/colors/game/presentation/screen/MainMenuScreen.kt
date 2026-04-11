package com.colors.game.presentation.screen

import android.app.Activity
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.colors.game.presentation.viewmodel.MainMenuViewModel
import com.colors.game.ui.theme.*

@Composable
fun MainMenuScreen(
    onPlayNext: (Int) -> Unit,
    onResume: (Int) -> Unit,
    onSelectLevel: () -> Unit,
    onSettings: () -> Unit,
    onExit: () -> Unit,
    viewModel: MainMenuViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF1A0533), Color(0xFF0D1B2A))
    )

    // Decorative rotating logo
    val infiniteRotation = rememberInfiniteTransition(label = "logoRotate")
    val rotation by infiniteRotation.animateFloat(
        initialValue   = 0f,
        targetValue    = 360f,
        animationSpec  = infiniteRepeatable(tween(20_000, easing = LinearEasing)),
        label          = "logoRotation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier            = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Logo
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .rotate(rotation)
                    .background(Primary.copy(alpha = 0.2f), RoundedCornerShape(28.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("●", fontSize = 56.sp)
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text  = "Colors",
                style = MaterialTheme.typography.displayLarge,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text  = "${uiState.totalCompleted} / 500 niveles completados",
                style = MaterialTheme.typography.bodyMedium,
                color = OnSurfaceDim
            )

            Spacer(Modifier.height(24.dp))

            // Continue
            MenuButton(
                text    = "Continuar",
                icon    = Icons.Default.PlayArrow,
                color   = Primary,
                onClick = { onPlayNext(uiState.nextAvailableLevelId) }
            )

            // Resume (only visible if there's an in-progress game)
            if (uiState.resumableLevelId != null) {
                MenuButton(
                    text    = "Reanudar nivel ${uiState.resumableLevelId}",
                    icon    = Icons.Default.Restore,
                    color   = Color(0xFF00897B),
                    onClick = { onResume(uiState.resumableLevelId!!) }
                )
            }

            // Level selector
            MenuButton(
                text    = "Niveles",
                icon    = Icons.Default.GridView,
                color   = Color(0xFF5E35B1),
                onClick = onSelectLevel
            )

            // Settings
            MenuButton(
                text    = "Ajustes",
                icon    = Icons.Default.Settings,
                color   = Color(0xFF37474F),
                onClick = onSettings
            )

            // Exit
            MenuButton(
                text    = "Salir",
                icon    = Icons.Default.ExitToApp,
                color   = Color(0xFF424242),
                onClick = {
                    (context as? Activity)?.finish()
                }
            )
        }
    }
}

@Composable
private fun MenuButton(
    text: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Button(
        onClick  = onClick,
        modifier = Modifier.fillMaxWidth().height(56.dp),
        colors   = ButtonDefaults.buttonColors(containerColor = color),
        shape    = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(12.dp))
        Text(text, style = MaterialTheme.typography.titleMedium, color = Color.White)
    }
}
