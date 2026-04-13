package com.colors.game.data.model;

import kotlinx.serialization.Serializable;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0087\u0081\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B%\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012\u00a8\u0006\u0014"}, d2 = {"Lcom/colors/game/data/model/Difficulty;", "", "displayName", "", "colorPalette", "", "Lcom/colors/game/data/model/GameColor;", "timeLimitSeconds", "", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/List;I)V", "getColorPalette", "()Ljava/util/List;", "getDisplayName", "()Ljava/lang/String;", "getTimeLimitSeconds", "()I", "EASY", "MEDIUM", "HARD", "Companion", "app_debug"})
public enum Difficulty {
    /*public static final*/ EASY /* = new EASY(null, null, 0) */,
    /*public static final*/ MEDIUM /* = new MEDIUM(null, null, 0) */,
    /*public static final*/ HARD /* = new HARD(null, null, 0) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String displayName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.colors.game.data.model.GameColor> colorPalette = null;
    private final int timeLimitSeconds = 0;
    @org.jetbrains.annotations.NotNull()
    public static final com.colors.game.data.model.Difficulty.Companion Companion = null;
    
    Difficulty(java.lang.String displayName, java.util.List<? extends com.colors.game.data.model.GameColor> colorPalette, int timeLimitSeconds) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDisplayName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.colors.game.data.model.GameColor> getColorPalette() {
        return null;
    }
    
    public final int getTimeLimitSeconds() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.colors.game.data.model.Difficulty> getEntries() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/colors/game/data/model/Difficulty$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/colors/game/data/model/Difficulty;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.colors.game.data.model.Difficulty> serializer() {
            return null;
        }
    }
}