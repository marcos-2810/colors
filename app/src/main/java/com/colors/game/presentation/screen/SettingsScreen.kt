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
import androidx.compose.runtime.collectAsState
import com.colors.game.data.model.Language
import com.colors.game.presentation.viewmodel.SettingsViewModel
import com.colors.game.ui.LocalStrings
import com.colors.game.ui.theme.*

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val settings by viewModel.settings.collectAsState()
    val strings  = LocalStrings.current

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
                Icon(Icons.Default.ArrowBack, contentDescription = strings.back, tint = Color.White)
            }
            Text(
                text     = strings.settings,
                style    = MaterialTheme.typography.headlineMedium,
                color    = Color.White,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Spacer(Modifier.height(12.dp))

        // Language
        SettingsSectionHeader(strings.languageLabel)
        SettingsLanguageRow(
            currentLanguage  = settings.language,
            onLanguageChange = viewModel::setLanguage,
            label            = strings.languageLabel,
            sublabel         = strings.languageDesc
        )

        Spacer(Modifier.height(20.dp))

        // Accessibility
        SettingsSectionHeader(strings.accessibility)
        SettingsToggleRow(
            label    = strings.colorBlindMode,
            sublabel = strings.colorBlindModeDesc,
            icon     = Icons.Default.Visibility,
            checked  = settings.daltonicMode,
            onToggle = viewModel::setDaltonicMode
        )

        Spacer(Modifier.height(20.dp))

        // Audio
        SettingsSectionHeader(strings.audio)
        SettingsToggleRow(
            label    = strings.sound,
            sublabel = strings.soundDesc,
            icon     = Icons.Default.VolumeUp,
            checked  = settings.soundEnabled,
            onToggle = viewModel::setSound
        )
        SettingsToggleRow(
            label    = strings.music,
            sublabel = strings.musicDesc,
            icon     = Icons.Default.MusicNote,
            checked  = settings.musicEnabled,
            onToggle = viewModel::setMusic
        )

        Spacer(Modifier.height(20.dp))

        // Haptics
        SettingsSectionHeader(strings.haptics)
        SettingsToggleRow(
            label    = strings.vibration,
            sublabel = strings.vibrationDesc,
            icon     = Icons.Default.Vibration,
            checked  = settings.vibrationEnabled,
            onToggle = viewModel::setVibration
        )

        Spacer(Modifier.height(32.dp))

        // Version info
        Text(
            text     = strings.versionInfo,
            style    = MaterialTheme.typography.bodyMedium,
            color    = OnSurfaceDim,
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
private fun SettingsLanguageRow(
    currentLanguage: Language,
    onLanguageChange: (Language) -> Unit,
    label: String,
    sublabel: String
) {
    Row(
        modifier          = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Language, contentDescription = null, tint = Primary, modifier = Modifier.size(24.dp))
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(label,    style = MaterialTheme.typography.titleMedium, color = Color.White)
            Text(sublabel, style = MaterialTheme.typography.bodyMedium,  color = OnSurfaceDim)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Language.entries.forEach { lang ->
                val selected = currentLanguage == lang
                val chipLabel = if (lang == Language.EN) "EN" else "ES"
                FilterChip(
                    selected = selected,
                    onClick  = { onLanguageChange(lang) },
                    label    = { Text(chipLabel, style = MaterialTheme.typography.labelLarge) },
                    shape    = RoundedCornerShape(8.dp),
                    colors   = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Primary,
                        selectedLabelColor     = Color.White
                    )
                )
            }
        }
    }
    HorizontalDivider(color = Color.White.copy(alpha = 0.06f))
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
        modifier          = Modifier.fillMaxWidth().padding(vertical = 4.dp),
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
