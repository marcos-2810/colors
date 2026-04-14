package com.colors.game.presentation.component;

import androidx.compose.animation.core.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import com.colors.game.data.model.GameColor;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a8\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0003\u001aF\u0010\u000b\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00102\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u00a8\u0006\u0011"}, d2 = {"ColorButton", "", "color", "Lcom/colors/game/data/model/GameColor;", "isActive", "", "daltonicMode", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "ColorPicker", "palette", "", "activeColor", "onColorSelected", "Lkotlin/Function1;", "app_release"})
public final class ColorPickerKt {
    
    /**
     * Horizontal row of color buttons.
     *
     * The currently-active color (same as selected group) is subtly dimmed
     * to indicate it's not a valid selection (tapping it is a no-op).
     *
     * Layout adapts to palette size — up to 8 colors fits comfortably on
     * a standard mobile screen in a single row.
     */
    @androidx.compose.runtime.Composable()
    public static final void ColorPicker(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.colors.game.data.model.GameColor> palette, @org.jetbrains.annotations.Nullable()
    com.colors.game.data.model.GameColor activeColor, boolean daltonicMode, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.colors.game.data.model.GameColor, kotlin.Unit> onColorSelected, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ColorButton(com.colors.game.data.model.GameColor color, boolean isActive, boolean daltonicMode, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, androidx.compose.ui.Modifier modifier) {
    }
}