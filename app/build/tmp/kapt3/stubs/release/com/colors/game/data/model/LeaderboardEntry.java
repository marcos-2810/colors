package com.colors.game.data.model;

/**
 * A single row in a leaderboard ranking.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\tH\u00c6\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010\u00a8\u0006\u001c"}, d2 = {"Lcom/colors/game/data/model/LeaderboardEntry;", "", "rank", "", "playerId", "", "playerName", "score", "isCurrentPlayer", "", "(ILjava/lang/String;Ljava/lang/String;IZ)V", "()Z", "getPlayerId", "()Ljava/lang/String;", "getPlayerName", "getRank", "()I", "getScore", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "app_release"})
public final class LeaderboardEntry {
    private final int rank = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String playerId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String playerName = null;
    
    /**
     * Raw score: moves used (lower = better) or seconds elapsed (lower = better).
     */
    private final int score = 0;
    
    /**
     * True when this entry belongs to the currently signed-in player.
     */
    private final boolean isCurrentPlayer = false;
    
    public LeaderboardEntry(int rank, @org.jetbrains.annotations.NotNull()
    java.lang.String playerId, @org.jetbrains.annotations.NotNull()
    java.lang.String playerName, int score, boolean isCurrentPlayer) {
        super();
    }
    
    public final int getRank() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPlayerId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPlayerName() {
        return null;
    }
    
    /**
     * Raw score: moves used (lower = better) or seconds elapsed (lower = better).
     */
    public final int getScore() {
        return 0;
    }
    
    /**
     * True when this entry belongs to the currently signed-in player.
     */
    public final boolean isCurrentPlayer() {
        return false;
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.data.model.LeaderboardEntry copy(int rank, @org.jetbrains.annotations.NotNull()
    java.lang.String playerId, @org.jetbrains.annotations.NotNull()
    java.lang.String playerName, int score, boolean isCurrentPlayer) {
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
}