package com.colors.game.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.colors.game.data.model.AppSettings
import com.colors.game.presentation.viewmodel.SettingsViewModel
import com.colors.game.ui.theme.*

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val settings by viewModel.settings.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
            .padding(horizontal = 20.dp)
    ) {
        // Top bar
        Row(
            modifier          = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Atrás", tint = Color.White)
            }
            Text(
                text     = "Ajustes",
                style    = MaterialTheme.typography.headlineMedium,
                color    = Color.White,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Spacer(Modifier.height(12.dp))

        // Accessibility
        SettingsSectionHeader("Accesibilidad")
        SettingsToggleRow(
            label    = "Modo daltónico",
            sublabel = "Paleta de colores accesible",
            icon     = Icons.Default.Visibility,
            checked  = settings.daltonicMode,
            onToggle = viewModel::setDaltonicMode
        )

        Spacer(Modifier.height(20.dp))

        // Audio
        SettingsSectionHeader("Audio")
        SettingsToggleRow(
            label    = "Sonido",
            sublabel = "Efectos de sonido",
            icon     = Icons.Default.VolumeUp,
            checked  = settings.soundEnabled,
            onToggle = viewModel::setSound
        )
        SettingsToggleRow(
            label    = "Música",
            sublabel = "Música de fondo",
            icon     = Icons.Default.MusicNote,
            checked  = settings.musicEnabled,
            onToggle = viewModel::setMusic
        )

        Spacer(Modifier.height(20.dp))

        // Haptics
        SettingsSectionHeader("Táctil")
        SettingsToggleRow(
            label    = "Vibración",
            sublabel = "Respuesta háptica al jugar",
            icon     = Icons.Default.Vibration,
            checked  = settings.vibrationEnabled,
            onToggle = viewModel::setVibration
        )

        Spacer(Modifier.height(32.dp))

        // Version info
        Text(
            text  = "Colors v1.0 · 500 niveles",
            style = MaterialTheme.typography.bodyMedium,
            color = OnSurfaceDim,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun SettingsSectionHeader(title: String) {
    Text(
        text     = title.uppercase(),
        style    = MaterialTheme.typography.labelLarge,
        color    = OnSurfaceDim,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
    )
}

@Composable
private fun SettingsToggleRow(
    label: String,
    sublabel: String,
    icon: ImageVector,
    checked: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier          = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Primary, modifier = Modifier.size(24.dp))
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(label,    style = MaterialTheme.typography.titleMedium, color = Color.White)
            Text(sublabel, style = MaterialTheme.typography.bodyMedium,  color = OnSurfaceDim)
        }
        Switch(
            checked         = checked,
            onCheckedChange = onToggle,
            colors          = SwitchDefaults.colors(checkedTrackColor = Primary)
        )
    }
    HorizontalDivider(color = Color.White.copy(alpha = 0.06f))
}
