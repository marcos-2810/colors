package com.colors.game.presentation.screen;

import androidx.compose.animation.*;
import androidx.compose.animation.core.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import com.colors.game.ui.AppStrings;
import com.colors.game.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0003\u001a\b\u0010\u0002\u001a\u00020\u0001H\u0003\u001a\b\u0010\u0003\u001a\u00020\u0001H\u0003\u001a\b\u0010\u0004\u001a\u00020\u0001H\u0003\u001a\u0016\u0010\u0005\u001a\u00020\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u00a8\u0006\r"}, d2 = {"IllustrationExpand", "", "IllustrationPickColor", "IllustrationTopLeft", "IllustrationWin", "TutorialOverlay", "onComplete", "Lkotlin/Function0;", "buildTutorialPages", "", "Lcom/colors/game/presentation/screen/TutorialPage;", "strings", "Lcom/colors/game/ui/AppStrings;", "app_release"})
public final class TutorialOverlayKt {
    
    @androidx.compose.runtime.Composable()
    public static final void TutorialOverlay(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onComplete) {
    }
    
    private static final java.util.List<com.colors.game.presentation.screen.TutorialPage> buildTutorialPages(com.colors.game.ui.AppStrings strings) {
        return null;
    }
    
    /**
     * Page 1: top-left corner always active
     */
    @androidx.compose.runtime.Composable()
    private static final void IllustrationTopLeft() {
    }
    
    /**
     * Page 2: color picker with animated selection
     */
    @androidx.compose.runtime.Composable()
    private static final void IllustrationPickColor() {
    }
    
    /**
     * Page 3: group expansion animation
     */
    @androidx.compose.runtime.Composable()
    private static final void IllustrationExpand() {
    }
    
    /**
     * Page 4: completed board with win animation
     */
    @androidx.compose.runtime.Composable()
    private static final void IllustrationWin() {
    }
}