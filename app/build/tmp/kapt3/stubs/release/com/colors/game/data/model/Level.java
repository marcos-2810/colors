package com.colors.game.data.model;

import kotlinx.serialization.Serializable;

/**
 * Immutable definition of a level. Stored/cached once generated.
 *
 * Grid is stored as a flat list, row-major order:
 *  index = row * cols + col
 */
@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 <2\u00020\u0001:\u0002;<Bs\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0002\u0010\u0012BY\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0006H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003Jo\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\u000f\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00100\u001a\u00020\u0003H\u00d6\u0001J\t\u00101\u001a\u000202H\u00d6\u0001J&\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u00002\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000209H\u00c1\u0001\u00a2\u0006\u0002\b:R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017\u00a8\u0006="}, d2 = {"Lcom/colors/game/data/model/Level;", "", "seen1", "", "id", "difficulty", "Lcom/colors/game/data/model/Difficulty;", "rows", "cols", "initialCells", "", "Lcom/colors/game/data/model/GameColor;", "maxMoves", "timeLimitSeconds", "colorPalette", "optimalMoves", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILcom/colors/game/data/model/Difficulty;IILjava/util/List;IILjava/util/List;ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ILcom/colors/game/data/model/Difficulty;IILjava/util/List;IILjava/util/List;I)V", "getColorPalette", "()Ljava/util/List;", "getCols", "()I", "getDifficulty", "()Lcom/colors/game/data/model/Difficulty;", "getId", "getInitialCells", "getMaxMoves", "getOptimalMoves", "getRows", "getTimeLimitSeconds", "cellAt", "row", "col", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_release", "$serializer", "Companion", "app_release"})
public final class Level {
    private final int id = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.model.Difficulty difficulty = null;
    private final int rows = 0;
    private final int cols = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.colors.game.data.model.GameColor> initialCells = null;
    private final int maxMoves = 0;
    private final int timeLimitSeconds = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.colors.game.data.model.GameColor> colorPalette = null;
    private final int optimalMoves = 0;
    @org.jetbrains.annotations.NotNull()
    public static final com.colors.game.data.model.Level.Companion Companion = null;
    
    public Level(int id, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.Difficulty difficulty, int rows, int cols, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.colors.game.data.model.GameColor> initialCells, int maxMoves, int timeLimitSeconds, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.colors.game.data.model.GameColor> colorPalette, int optimalMoves) {
        super();
    }
    
    public final int getId() {
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
    public final java.util.List<com.colors.game.data.model.GameColor> getInitialCells() {
        return null;
    }
    
    public final int getMaxMoves() {
        return 0;
    }
    
    public final int getTimeLimitSeconds() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.colors.game.data.model.GameColor> getColorPalette() {
        return null;
    }
    
    public final int getOptimalMoves() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.data.model.GameColor cellAt(int row, int col) {
        return null;
    }
    
    public final int component1() {
        return 0;
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.colors.game.data.model.GameColor> component8() {
        return null;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.data.model.Level copy(int id, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.Difficulty difficulty, int rows, int cols, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.colors.game.data.model.GameColor> initialCells, int maxMoves, int timeLimitSeconds, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.colors.game.data.model.GameColor> colorPalette, int optimalMoves) {
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
    com.colors.game.data.model.Level self, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.encoding.CompositeEncoder output, @org.jetbrains.annotations.NotNull()
    kotlinx.serialization.descriptors.SerialDescriptor serialDesc) {
    }
    
    /**
     * Immutable definition of a level. Stored/cached once generated.
     *
     * Grid is stored as a flat list, row-major order:
     *  index = row * cols + col
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u00d6\u0001\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VX\u00d6\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"com/colors/game/data/model/Level.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/colors/game/data/model/Level;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "app_release"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.colors.game.data.model.Level> {
        @org.jetbrains.annotations.NotNull()
        public static final com.colors.game.data.model.Level.$serializer INSTANCE = null;
        
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
        public com.colors.game.data.model.Level deserialize(@org.jetbrains.annotations.NotNull()
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
        com.colors.game.data.model.Level value) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.KSerializer<?>[] typeParametersSerializers() {
            return null;
        }
    }
    
    /**
     * Immutable definition of a level. Stored/cached once generated.
     *
     * Grid is stored as a flat list, row-major order:
     *  index = row * cols + col
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/colors/game/data/model/Level$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/colors/game/data/model/Level;", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.colors.game.data.model.Level> serializer() {
            return null;
        }
    }
}