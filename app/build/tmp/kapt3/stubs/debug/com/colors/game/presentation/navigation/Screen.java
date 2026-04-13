package com.colors.game.presentation.navigation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/colors/game/presentation/navigation/Screen;", "", "route", "", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "Game", "LevelSelector", "MainMenu", "Settings", "Lcom/colors/game/presentation/navigation/Screen$Game;", "Lcom/colors/game/presentation/navigation/Screen$LevelSelector;", "Lcom/colors/game/presentation/navigation/Screen$MainMenu;", "Lcom/colors/game/presentation/navigation/Screen$Settings;", "app_debug"})
public abstract class Screen {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    
    private Screen(java.lang.String route) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/colors/game/presentation/navigation/Screen$Game;", "Lcom/colors/game/presentation/navigation/Screen;", "()V", "routeFor", "", "levelId", "", "app_debug"})
    public static final class Game extends com.colors.game.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.colors.game.presentation.navigation.Screen.Game INSTANCE = null;
        
        private Game() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String routeFor(int levelId) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/colors/game/presentation/navigation/Screen$LevelSelector;", "Lcom/colors/game/presentation/navigation/Screen;", "()V", "app_debug"})
    public static final class LevelSelector extends com.colors.game.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.colors.game.presentation.navigation.Screen.LevelSelector INSTANCE = null;
        
        private LevelSelector() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/colors/game/presentation/navigation/Screen$MainMenu;", "Lcom/colors/game/presentation/navigation/Screen;", "()V", "app_debug"})
    public static final class MainMenu extends com.colors.game.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.colors.game.presentation.navigation.Screen.MainMenu INSTANCE = null;
        
        private MainMenu() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/colors/game/presentation/navigation/Screen$Settings;", "Lcom/colors/game/presentation/navigation/Screen;", "()V", "app_debug"})
    public static final class Settings extends com.colors.game.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.colors.game.presentation.navigation.Screen.Settings INSTANCE = null;
        
        private Settings() {
        }
    }
}