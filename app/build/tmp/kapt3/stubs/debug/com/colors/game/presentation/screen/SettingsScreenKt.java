package com.colors.game.presentation.screen;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import com.colors.game.data.model.Language;
import com.colors.game.presentation.viewmodel.SettingsViewModel;
import com.colors.game.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0003\u001a \u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007\u001a\u0010\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0007H\u0003\u001a<\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u00a8\u0006\u0016"}, d2 = {"SettingsLanguageRow", "", "currentLanguage", "Lcom/colors/game/data/model/Language;", "onLanguageChange", "Lkotlin/Function1;", "label", "", "sublabel", "SettingsScreen", "onBack", "Lkotlin/Function0;", "viewModel", "Lcom/colors/game/presentation/viewmodel/SettingsViewModel;", "SettingsSectionHeader", "title", "SettingsToggleRow", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "checked", "", "onToggle", "app_debug"})
public final class SettingsScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void SettingsScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    com.colors.game.presentation.viewmodel.SettingsViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SettingsSectionHeader(java.lang.String title) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SettingsLanguageRow(com.colors.game.data.model.Language currentLanguage, kotlin.jvm.functions.Function1<? super com.colors.game.data.model.Language, kotlin.Unit> onLanguageChange, java.lang.String label, java.lang.String sublabel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SettingsToggleRow(java.lang.String label, java.lang.String sublabel, androidx.compose.ui.graphics.vector.ImageVector icon, boolean checked, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onToggle) {
    }
}