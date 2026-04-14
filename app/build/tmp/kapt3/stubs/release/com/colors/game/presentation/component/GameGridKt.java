package com.colors.game.presentation.component;

import androidx.compose.animation.core.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Modifier;
import com.colors.game.data.model.GameColor;
import com.colors.game.data.model.Position;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aZ\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007\u001a9\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0003\u00a2\u0006\u0002\u0010\u0015\u00a8\u0006\u0016"}, d2 = {"GameGrid", "", "rows", "", "cols", "cells", "", "Lcom/colors/game/data/model/GameColor;", "selectedGroup", "", "Lcom/colors/game/data/model/Position;", "animatingCells", "", "daltonicMode", "", "modifier", "Landroidx/compose/ui/Modifier;", "GridCell", "color", "isSelected", "waveDistance", "(Lcom/colors/game/data/model/GameColor;ZLjava/lang/Integer;ZLandroidx/compose/ui/Modifier;)V", "app_release"})
public final class GameGridKt {
    
    /**
     * Renders the NxM game grid.
     *
     * El grupo activo siempre parte de (0,0) — las celdas NO son tapeables.
     * El resaltado del grupo activo sigue existiendo con animación de pulso.
     */
    @androidx.compose.runtime.Composable()
    public static final void GameGrid(int rows, int cols, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.colors.game.data.model.GameColor> cells, @org.jetbrains.annotations.NotNull()
    java.util.Set<com.colors.game.data.model.Position> selectedGroup, @org.jetbrains.annotations.NotNull()
    java.util.Map<com.colors.game.data.model.Position, java.lang.Integer> animatingCells, boolean daltonicMode, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void GridCell(com.colors.game.data.model.GameColor color, boolean isSelected, java.lang.Integer waveDistance, boolean daltonicMode, androidx.compose.ui.Modifier modifier) {
    }
}