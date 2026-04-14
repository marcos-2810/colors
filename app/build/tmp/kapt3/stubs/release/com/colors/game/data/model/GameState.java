package com.colors.game.data.model;

import kotlinx.serialization.Serializable;

/**
 * Full in-progress game state. Serialized to DataStore for persistence.
 */
@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 O2\u00020\u0001:\u0002NOB\u00ab\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\n\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0017\u001a\u00020\u0015\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u00a2\u0006\u0002\u0010\u001aB\u0097\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\n\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0015\u00a2\u0006\u0002\u0010\u001bJ\u0016\u0010-\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00130\nH\u00c6\u0003J\t\u00104\u001a\u00020\u0015H\u00c6\u0003J\t\u00105\u001a\u00020\u0015H\u00c6\u0003J\t\u00106\u001a\u00020\u0015H\u00c6\u0003J\t\u00107\u001a\u00020\u0006H\u00c6\u0003J\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\t\u0010;\u001a\u00020\u0003H\u00c6\u0003J\t\u0010<\u001a\u00020\u0003H\u00c6\u0003J\t\u0010=\u001a\u00020\u0003H\u00c6\u0003J\t\u0010>\u001a\u00020\u0003H\u00c6\u0003J\u0006\u0010?\u001a\u00020\u0003J\u00b1\u0001\u0010@\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\n2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u0015H\u00c6\u0001J\u0013\u0010A\u001a\u00020\u00152\b\u0010B\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010C\u001a\u00020\u0003H\u00d6\u0001J\t\u0010D\u001a\u00020EH\u00d6\u0001J&\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020\u00002\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020LH\u00c1\u0001\u00a2\u0006\u0002\bMR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010#R\u0011\u0010\u0017\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010#R\u0011\u0010$\u001a\u00020\u00158F\u00a2\u0006\u0006\u001a\u0004\b$\u0010#R\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010#R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001fR\u0011\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001fR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\n\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001dR\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001f\u00a8\u0006P"}, d2 = {"Lcom/colors/game/data/model/GameState;", "", "seen1", "", "levelId", "difficulty", "Lcom/colors/game/data/model/Difficulty;", "rows", "cols", "currentCells", "", "Lcom/colors/game/data/model/GameColor;", "movesRemaining", "timeElapsedSeconds", "timeLimitSeconds", "maxMoves", "optimalMoves", "colorPalette", "selectedGroup", "Lcom/colors/game/data/model/Position;", "isPaused", "", "isCompleted", "isFailed", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILcom/colors/game/data/model/Difficulty;IILjava/util/List;IIIIILjava/util/List;Ljava/util/List;ZZZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ILcom/colors/game/data/model/Difficulty;IILjava/util/List;IIIIILjava/util/List;Ljava/util/List;ZZZ)V", "getColorPalette", "()Ljava/util/List;", "getCols", "()I", "getCurrentCells", "getDifficulty", "()Lcom/colors/game/data/model/Difficulty;", "()Z", "isOver", "getLevelId", "getMaxMoves", "getMovesRemaining", "getOptimalMoves", "getRows", "getSelectedGroup", "getTimeElapsedSeconds", "getTimeLimitSeconds", "cellAt", "row", "col", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "computeStars", "copy", "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_release", "$serializer", "Companion", "app_release"})
public final class GameState {
    private final int levelId = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.model.Difficulty difficulty = null;
    private final int rows = 0;
    private final int cols = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.colors.game.data.model.GameColor> currentCells = null;
    private final int movesRemaining = 0;
    private final int timeElapsedSeconds = 0;
    private final int timeLimitSeconds = 0;
    private final int maxMoves = 0;
    private final int optimalMoves = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.colors.game.data.model.GameColor> colorPalette = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.colors.game.data.model.Position> selectedGroup = null;
    private final boolean isPaused = false;
    private final boolean isCompleted = false;
    private final boolean isFailed = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.colors.game.data.model.GameState.Companion Companion = null;
    
    public GameState(int levelId, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.Difficulty difficulty, int rows, int cols, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.colors.game.data.model.GameColor> currentCells, int movesRemaining, int timeElapsedSeconds, int timeLimitSeconds, int maxMoves, int optimalMoves, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.colors.game.data.model.GameColor> colorPalette, @org.jetbrains.annotations.NotNull()
    java.util.List<com.colors.game.data.model.Position> selectedGroup, boolean isPaused, boolean isCompleted, boolean isFailed) {
        super();
    }
    
    public final int getLevelId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.data.model.Difficulty getDifficulty() {
        return null;
    }
    
    public final int getRows() {
        return 0;
    }
    
    public final int getCols() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.colors.game.data.model.GameColor> getCurrentCells() {
        return null;
    }
    
    public final int getMovesRemaining() {
        return 0;
    }
    
    public final int getTimeElapsedSeconds() {
        return 0;
    }
    
    public final int getTimeLimitSeconds() {
        return 0;
    }
    
    public final int getMaxMoves() {
        return 0;
    }
    
    public final int getOptimalMoves() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.colors.game.data.model.GameColor> getColorPalette() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.colors.game.data.model.Position> getSelectedGroup() {
        return null;
    }
    
    public final boolean isPaused() {
        return false;
    }
    
    public final boolean isCompleted() {
        return false;
    }
    
    public final boolean isFailed() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.data.model.GameColor cellAt(int row, int col) {
        return null;
    }
    
    public final boolean isOver() {
        return false;
    }
    
    /**
     * Star rating based on performance:
     * 3 stars: solved using ≤ optimalMoves + 1 moves
     * 2 stars: solved using ≤ maxMoves * 0.7 moves
     * 1 star:  completed within the move limit
     */
    public final int computeStars() {
        return 0;
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component10() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.colors.game.data.model.GameColor> component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.colors.game.data.model.Position> component12() {
        return null;
    }
    
    public final boolean component13() {
        return false;
    }
    
    public final boolean component14() {
        return false;
    }
    
    public final boolean component15() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.data.model.Difficulty component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.colors.game.data.model.GameColor> component5() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.data.model.GameState copy(int levelId, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.Difficulty difficulty, int rows, int cols, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.colors.game.data.model.GameColor> currentCells, int movesRemaining, int timeElapsedSeconds, int timeLimitSeconds, int maxMoves, int optimalMoves, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.colors.game.data.model.GameColor> colorPalette, @org.jetbrains.annotations.NotNull()
    java.util.List<com.colors.game.data.model.Position> selectedGroup, boolean isPaused, boolean isCompleted, boolean isFailed) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void write$Self$app_release(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.GameState self, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.encoding.CompositeEncoder output, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.descriptors.SerialDescriptor serialDesc) {
    }
    
    /**
     * Full in-progress game state. Serialized to DataStore for persistence.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u00d6\u0001\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VX\u00d6\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"com/colors/game/data/model/GameState.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/colors/game/data/model/GameState;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "app_release"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.colors.game.data.model.GameState> {
        @org.jetbrains.annotations.NotNull()
        public static final com.colors.game.data.model.GameState.$serializer INSTANCE = null;
        
        private $serializer() {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.KSerializer<?>[] childSerializers() {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.colors.game.data.model.GameState deserialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Decoder decoder) {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.descriptors.SerialDescriptor getDescriptor() {
            return null;
        }
        
        @java.lang.Override()
        public void serialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Encoder encoder, @org.jetbrains.annotations.NotNull()
        com.colors.game.data.model.GameState value) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.KSerializer<?>[] typeParametersSerializers() {
            return null;
        }
    }
    
    /**
     * Full in-progress game state. Serialized to DataStore for persistence.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/colors/game/data/model/GameState$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/colors/game/data/model/GameState;", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.colors.game.data.model.GameState> serializer() {
            return null;
        }
    }
}