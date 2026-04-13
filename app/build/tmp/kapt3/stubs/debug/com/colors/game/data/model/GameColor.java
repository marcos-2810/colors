package com.colors.game.data.model;

import kotlinx.serialization.Serializable;

/**
 * All playable colors in the game.
 * Each color has a normal variant and a colorblind-safe (daltonism) variant.
 * Difficulty controls which subset of colors is available per level.
 */
@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0087\u0081\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\n\u0010\bj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0014"}, d2 = {"Lcom/colors/game/data/model/GameColor;", "", "(Ljava/lang/String;I)V", "toComposeColor", "Landroidx/compose/ui/graphics/Color;", "daltonicMode", "", "toComposeColor-vNxB06k", "(Z)J", "toHighlightColor", "toHighlightColor-vNxB06k", "RED", "GREEN", "BLUE", "YELLOW", "BROWN", "PURPLE", "ORANGE", "PINK", "Companion", "app_debug"})
public enum GameColor {
    /*public static final*/ RED /* = new RED() */,
    /*public static final*/ GREEN /* = new GREEN() */,
    /*public static final*/ BLUE /* = new BLUE() */,
    /*public static final*/ YELLOW /* = new YELLOW() */,
    /*public static final*/ BROWN /* = new BROWN() */,
    /*public static final*/ PURPLE /* = new PURPLE() */,
    /*public static final*/ ORANGE /* = new ORANGE() */,
    /*public static final*/ PINK /* = new PINK() */;
    @org.jetbrains.annotations.NotNull()
    public static final com.colors.game.data.model.GameColor.Companion Companion = null;
    
    GameColor() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.colors.game.data.model.GameColor> getEntries() {
        return null;
    }
    
    /**
     * All playable colors in the game.
     * Each color has a normal variant and a colorblind-safe (daltonism) variant.
     * Difficulty controls which subset of colors is available per level.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/colors/game/data/model/GameColor$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/colors/game/data/model/GameColor;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.colors.game.data.model.GameColor> serializer() {
            return null;
        }
    }
}