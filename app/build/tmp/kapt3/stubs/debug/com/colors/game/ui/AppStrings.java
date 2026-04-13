package com.colors.game.ui;

import com.colors.game.data.model.Difficulty;
import com.colors.game.data.model.Language;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\'\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0003\b\u00d4\u0001\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u009d\u0005\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\u0003\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010 \u001a\u00020\u0003\u0012\u0006\u0010!\u001a\u00020\u0003\u0012\u0006\u0010\"\u001a\u00020\u0003\u0012\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u0012\u0006\u0010$\u001a\u00020\u0003\u0012\u0006\u0010%\u001a\u00020\u0003\u0012\u0006\u0010&\u001a\u00020\u0003\u0012\u0006\u0010\'\u001a\u00020\u0003\u0012\u0006\u0010(\u001a\u00020\u0003\u0012\u0006\u0010)\u001a\u00020\u0003\u0012\u0006\u0010*\u001a\u00020\u0003\u0012\u0006\u0010+\u001a\u00020\u0003\u0012\u0006\u0010,\u001a\u00020\u0003\u0012\u0006\u0010-\u001a\u00020\u0003\u0012\u0006\u0010.\u001a\u00020\u0003\u0012\u0006\u0010/\u001a\u00020\u0003\u0012\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u0012\u0006\u00101\u001a\u00020\u0003\u0012\u0006\u00102\u001a\u00020\u0003\u0012\u0006\u00103\u001a\u00020\u0003\u0012\u0006\u00104\u001a\u00020\u0003\u0012\u0006\u00105\u001a\u00020\u0003\u0012\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u0012\u0006\u00107\u001a\u00020\u0003\u0012\u0006\u00108\u001a\u00020\u0003\u0012\u0006\u00109\u001a\u00020\u0003\u0012\u0006\u0010:\u001a\u00020\u0003\u0012\u0006\u0010;\u001a\u00020\u0003\u0012\u0006\u0010<\u001a\u00020\u0003\u0012\u0006\u0010=\u001a\u00020\u0003\u0012\u0006\u0010>\u001a\u00020\u0003\u0012\u0006\u0010?\u001a\u00020\u0003\u0012\u0006\u0010@\u001a\u00020\u0003\u0012\u0006\u0010A\u001a\u00020\u0003\u0012\u0006\u0010B\u001a\u00020\u0003\u0012\u0006\u0010C\u001a\u00020\u0003\u0012\u0006\u0010D\u001a\u00020\u0003\u0012\u0006\u0010E\u001a\u00020\u0003\u0012\u0006\u0010F\u001a\u00020\u0003\u0012\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u0012\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u0012\u0006\u0010I\u001a\u00020\u0003\u0012\u0006\u0010J\u001a\u00020\u0003\u0012\u0006\u0010K\u001a\u00020\u0003\u00a2\u0006\u0002\u0010LJ\n\u0010\u0096\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u0097\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u0098\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u0099\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u009a\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u009b\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u009c\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u009d\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u009e\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u009f\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a0\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a1\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a2\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a3\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a4\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a5\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a6\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a7\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a8\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a9\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00aa\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00ab\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00ac\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00ad\u0001\u001a\u00020\u0003H\u00c6\u0003J\u0016\u0010\u00ae\u0001\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\n\u0010\u00af\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00b0\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00b1\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00b2\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00b3\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00b4\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00b5\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00b6\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00b7\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00b8\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00b9\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00ba\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00bb\u0001\u001a\u00020\u0003H\u00c6\u0003J\u0016\u0010\u00bc\u0001\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\n\u0010\u00bd\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00be\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00bf\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00c0\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00c1\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00c2\u0001\u001a\u00020\u0003H\u00c6\u0003J\u0016\u0010\u00c3\u0001\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\n\u0010\u00c4\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00c5\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00c6\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00c7\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00c8\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00c9\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00ca\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00cb\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00cc\u0001\u001a\u00020\u0003H\u00c6\u0003J\u0016\u0010\u00cd\u0001\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\n\u0010\u00ce\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00cf\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00d0\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00d1\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00d2\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00d3\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00d4\u0001\u001a\u00020\u0003H\u00c6\u0003J\u0016\u0010\u00d5\u0001\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\u0016\u0010\u00d6\u0001\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\n\u0010\u00d7\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00d8\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00d9\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00da\u0001\u001a\u00020\u0003H\u00c6\u0003J\u0016\u0010\u00db\u0001\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\u0016\u0010\u00dc\u0001\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\u00b0\u0006\u0010\u00dd\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00032\u0014\b\u0002\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010$\u001a\u00020\u00032\b\b\u0002\u0010%\u001a\u00020\u00032\b\b\u0002\u0010&\u001a\u00020\u00032\b\b\u0002\u0010\'\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020\u00032\b\b\u0002\u0010)\u001a\u00020\u00032\b\b\u0002\u0010*\u001a\u00020\u00032\b\b\u0002\u0010+\u001a\u00020\u00032\b\b\u0002\u0010,\u001a\u00020\u00032\b\b\u0002\u0010-\u001a\u00020\u00032\b\b\u0002\u0010.\u001a\u00020\u00032\b\b\u0002\u0010/\u001a\u00020\u00032\u0014\b\u0002\u00100\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u00101\u001a\u00020\u00032\b\b\u0002\u00102\u001a\u00020\u00032\b\b\u0002\u00103\u001a\u00020\u00032\b\b\u0002\u00104\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\u00032\u0014\b\u0002\u00106\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u00107\u001a\u00020\u00032\b\b\u0002\u00108\u001a\u00020\u00032\b\b\u0002\u00109\u001a\u00020\u00032\b\b\u0002\u0010:\u001a\u00020\u00032\b\b\u0002\u0010;\u001a\u00020\u00032\b\b\u0002\u0010<\u001a\u00020\u00032\b\b\u0002\u0010=\u001a\u00020\u00032\b\b\u0002\u0010>\u001a\u00020\u00032\b\b\u0002\u0010?\u001a\u00020\u00032\b\b\u0002\u0010@\u001a\u00020\u00032\b\b\u0002\u0010A\u001a\u00020\u00032\b\b\u0002\u0010B\u001a\u00020\u00032\b\b\u0002\u0010C\u001a\u00020\u00032\b\b\u0002\u0010D\u001a\u00020\u00032\b\b\u0002\u0010E\u001a\u00020\u00032\b\b\u0002\u0010F\u001a\u00020\u00032\u0014\b\u0002\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t2\u0014\b\u0002\u0010H\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010I\u001a\u00020\u00032\b\b\u0002\u0010J\u001a\u00020\u00032\b\b\u0002\u0010K\u001a\u00020\u0003H\u00c6\u0001J\u0016\u0010\u00de\u0001\u001a\u00030\u00df\u00012\t\u0010\u00e0\u0001\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\n\u0010\u00e1\u0001\u001a\u00020\nH\u00d6\u0001J\n\u0010\u00e2\u0001\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bM\u0010NR\u0011\u00101\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bO\u0010NR\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bP\u0010NR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bQ\u0010NR\u0011\u0010.\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bR\u0010NR\u0011\u0010-\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bS\u0010NR\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bT\u0010NR\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bU\u0010NR\u0011\u0010(\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bV\u0010NR\u0011\u00103\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bW\u0010NR\u0011\u0010 \u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bX\u0010NR\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bY\u0010NR\u0011\u0010)\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bZ\u0010NR\u0011\u0010\u0018\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b[\u0010NR\u0011\u0010\"\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\\\u0010NR\u0011\u00107\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b]\u0010NR\u0011\u0010\u001c\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b^\u0010NR\u0011\u0010\u001b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b_\u0010NR\u0011\u0010;\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b`\u0010NR\u001d\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u00a2\u0006\b\n\u0000\u001a\u0004\ba\u0010bR\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bc\u0010NR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u00a2\u0006\b\n\u0000\u001a\u0004\bd\u0010bR\u0011\u0010\u001f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\be\u0010NR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bf\u0010NR\u001d\u00106\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u00a2\u0006\b\n\u0000\u001a\u0004\bg\u0010bR\u0011\u0010!\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bh\u0010NR\u0011\u0010$\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bi\u0010NR\u001d\u00100\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u00a2\u0006\b\n\u0000\u001a\u0004\bj\u0010bR\u0011\u0010\u0016\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bk\u0010NR\u0011\u0010\u0017\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bl\u0010NR\u0011\u0010:\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bm\u0010NR\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u00a2\u0006\b\n\u0000\u001a\u0004\bn\u0010bR\u0011\u0010+\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bo\u0010NR\u0011\u0010*\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bp\u0010NR\u0011\u00102\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bq\u0010NR\u0011\u0010&\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\br\u0010NR\u0011\u0010\'\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bs\u0010NR\u0011\u00104\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bt\u0010NR\u0011\u00109\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bu\u0010NR\u0011\u00105\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bv\u0010NR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bw\u0010NR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bx\u0010NR\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u00a2\u0006\b\n\u0000\u001a\u0004\by\u0010bR\u0011\u0010\u001e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bz\u0010NR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b{\u0010NR\u0011\u00108\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b|\u0010NR\u0011\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b}\u0010NR\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b~\u0010NR\u0011\u0010/\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u007f\u0010NR\u0012\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0080\u0001\u0010NR\u0012\u0010%\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0081\u0001\u0010NR\u0012\u0010,\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0082\u0001\u0010NR\u0012\u0010I\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0083\u0001\u0010NR\u0012\u0010J\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0084\u0001\u0010NR\u0012\u0010E\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0085\u0001\u0010NR\u0012\u0010D\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u0010NR\u001e\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0087\u0001\u0010bR\u001e\u0010H\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0088\u0001\u0010bR\u0012\u0010=\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0089\u0001\u0010NR\u0012\u0010<\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008a\u0001\u0010NR\u0012\u0010?\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008b\u0001\u0010NR\u0012\u0010>\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008c\u0001\u0010NR\u0012\u0010A\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008d\u0001\u0010NR\u0012\u0010@\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008e\u0001\u0010NR\u0012\u0010C\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008f\u0001\u0010NR\u0012\u0010B\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0090\u0001\u0010NR\u0012\u0010F\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0091\u0001\u0010NR\u0012\u0010K\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0092\u0001\u0010NR\u0012\u0010\u001d\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0093\u0001\u0010NR\u0012\u0010\u0019\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0094\u0001\u0010NR\u0012\u0010\u001a\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0095\u0001\u0010N\u00a8\u0006\u00e3\u0001"}, d2 = {"Lcom/colors/game/ui/AppStrings;", "", "back", "", "settings", "mainMenu", "resume", "restart", "levelsCompleted", "Lkotlin/Function1;", "", "start", "nextLevel", "resumeLevel", "levels", "exit", "accessibility", "colorBlindMode", "colorBlindModeDesc", "audio", "sound", "soundDesc", "music", "musicDesc", "haptics", "vibration", "vibrationDesc", "languageLabel", "languageDesc", "versionInfo", "selectLevel", "locked", "easy", "medium", "hard", "levelN", "moves", "time", "pause", "pausedTitle", "completed", "gameOver", "noMovesLeft", "nextLevelBtn", "tryAgain", "bestTime", "bestMoves", "starRequirements", "movesThreshold", "anyCompletion", "notCompleted", "completedStatus", "play", "replay", "maxMovesLabel", "howToPlay", "skip", "previous", "next", "letsPlay", "tutPage1Title", "tutPage1Desc", "tutPage2Title", "tutPage2Desc", "tutPage3Title", "tutPage3Desc", "tutPage4Title", "tutPage4Desc", "tutCornerLabel", "tutColorChangesTo", "tutTapColor", "tutExpandedN", "tutInitialGroupN", "tutAfterExpandDesc", "tutBeforeExpandDesc", "tutWinDesc", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccessibility", "()Ljava/lang/String;", "getAnyCompletion", "getAudio", "getBack", "getBestMoves", "getBestTime", "getColorBlindMode", "getColorBlindModeDesc", "getCompleted", "getCompletedStatus", "getEasy", "getExit", "getGameOver", "getHaptics", "getHard", "getHowToPlay", "getLanguageDesc", "getLanguageLabel", "getLetsPlay", "getLevelN", "()Lkotlin/jvm/functions/Function1;", "getLevels", "getLevelsCompleted", "getLocked", "getMainMenu", "getMaxMovesLabel", "getMedium", "getMoves", "getMovesThreshold", "getMusic", "getMusicDesc", "getNext", "getNextLevel", "getNextLevelBtn", "getNoMovesLeft", "getNotCompleted", "getPause", "getPausedTitle", "getPlay", "getPrevious", "getReplay", "getRestart", "getResume", "getResumeLevel", "getSelectLevel", "getSettings", "getSkip", "getSound", "getSoundDesc", "getStarRequirements", "getStart", "getTime", "getTryAgain", "getTutAfterExpandDesc", "getTutBeforeExpandDesc", "getTutColorChangesTo", "getTutCornerLabel", "getTutExpandedN", "getTutInitialGroupN", "getTutPage1Desc", "getTutPage1Title", "getTutPage2Desc", "getTutPage2Title", "getTutPage3Desc", "getTutPage3Title", "getTutPage4Desc", "getTutPage4Title", "getTutTapColor", "getTutWinDesc", "getVersionInfo", "getVibration", "getVibrationDesc", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component50", "component51", "component52", "component53", "component54", "component55", "component56", "component57", "component58", "component59", "component6", "component60", "component61", "component62", "component63", "component64", "component65", "component66", "component67", "component68", "component69", "component7", "component70", "component71", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class AppStrings {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String back = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String settings = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String mainMenu = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String resume = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String restart = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> levelsCompleted = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String start = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> nextLevel = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> resumeLevel = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String levels = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String exit = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String accessibility = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String colorBlindMode = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String colorBlindModeDesc = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String audio = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String sound = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String soundDesc = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String music = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String musicDesc = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String haptics = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String vibration = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String vibrationDesc = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String languageLabel = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String languageDesc = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String versionInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String selectLevel = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String locked = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String easy = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String medium = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String hard = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> levelN = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String moves = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String time = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String pause = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String pausedTitle = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String completed = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String gameOver = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String noMovesLeft = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String nextLevelBtn = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tryAgain = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String bestTime = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String bestMoves = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String starRequirements = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> movesThreshold = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String anyCompletion = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String notCompleted = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String completedStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String play = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String replay = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> maxMovesLabel = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String howToPlay = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String skip = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String previous = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String next = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String letsPlay = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutPage1Title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutPage1Desc = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutPage2Title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutPage2Desc = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutPage3Title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutPage3Desc = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutPage4Title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutPage4Desc = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutCornerLabel = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutColorChangesTo = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutTapColor = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> tutExpandedN = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> tutInitialGroupN = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutAfterExpandDesc = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutBeforeExpandDesc = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tutWinDesc = null;
    
    public AppStrings(@org.jetbrains.annotations.NotNull()
    java.lang.String back, @org.jetbrains.annotations.NotNull()
    java.lang.String settings, @org.jetbrains.annotations.NotNull()
    java.lang.String mainMenu, @org.jetbrains.annotations.NotNull()
    java.lang.String resume, @org.jetbrains.annotations.NotNull()
    java.lang.String restart, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> levelsCompleted, @org.jetbrains.annotations.NotNull()
    java.lang.String start, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> nextLevel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> resumeLevel, @org.jetbrains.annotations.NotNull()
    java.lang.String levels, @org.jetbrains.annotations.NotNull()
    java.lang.String exit, @org.jetbrains.annotations.NotNull()
    java.lang.String accessibility, @org.jetbrains.annotations.NotNull()
    java.lang.String colorBlindMode, @org.jetbrains.annotations.NotNull()
    java.lang.String colorBlindModeDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String audio, @org.jetbrains.annotations.NotNull()
    java.lang.String sound, @org.jetbrains.annotations.NotNull()
    java.lang.String soundDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String music, @org.jetbrains.annotations.NotNull()
    java.lang.String musicDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String haptics, @org.jetbrains.annotations.NotNull()
    java.lang.String vibration, @org.jetbrains.annotations.NotNull()
    java.lang.String vibrationDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String languageLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String languageDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String versionInfo, @org.jetbrains.annotations.NotNull()
    java.lang.String selectLevel, @org.jetbrains.annotations.NotNull()
    java.lang.String locked, @org.jetbrains.annotations.NotNull()
    java.lang.String easy, @org.jetbrains.annotations.NotNull()
    java.lang.String medium, @org.jetbrains.annotations.NotNull()
    java.lang.String hard, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> levelN, @org.jetbrains.annotations.NotNull()
    java.lang.String moves, @org.jetbrains.annotations.NotNull()
    java.lang.String time, @org.jetbrains.annotations.NotNull()
    java.lang.String pause, @org.jetbrains.annotations.NotNull()
    java.lang.String pausedTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String completed, @org.jetbrains.annotations.NotNull()
    java.lang.String gameOver, @org.jetbrains.annotations.NotNull()
    java.lang.String noMovesLeft, @org.jetbrains.annotations.NotNull()
    java.lang.String nextLevelBtn, @org.jetbrains.annotations.NotNull()
    java.lang.String tryAgain, @org.jetbrains.annotations.NotNull()
    java.lang.String bestTime, @org.jetbrains.annotations.NotNull()
    java.lang.String bestMoves, @org.jetbrains.annotations.NotNull()
    java.lang.String starRequirements, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> movesThreshold, @org.jetbrains.annotations.NotNull()
    java.lang.String anyCompletion, @org.jetbrains.annotations.NotNull()
    java.lang.String notCompleted, @org.jetbrains.annotations.NotNull()
    java.lang.String completedStatus, @org.jetbrains.annotations.NotNull()
    java.lang.String play, @org.jetbrains.annotations.NotNull()
    java.lang.String replay, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> maxMovesLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String howToPlay, @org.jetbrains.annotations.NotNull()
    java.lang.String skip, @org.jetbrains.annotations.NotNull()
    java.lang.String previous, @org.jetbrains.annotations.NotNull()
    java.lang.String next, @org.jetbrains.annotations.NotNull()
    java.lang.String letsPlay, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage1Title, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage1Desc, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage2Title, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage2Desc, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage3Title, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage3Desc, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage4Title, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage4Desc, @org.jetbrains.annotations.NotNull()
    java.lang.String tutCornerLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String tutColorChangesTo, @org.jetbrains.annotations.NotNull()
    java.lang.String tutTapColor, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> tutExpandedN, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> tutInitialGroupN, @org.jetbrains.annotations.NotNull()
    java.lang.String tutAfterExpandDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String tutBeforeExpandDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String tutWinDesc) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBack() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMainMenu() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getResume() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRestart() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> getLevelsCompleted() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStart() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> getNextLevel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> getResumeLevel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLevels() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAccessibility() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getColorBlindMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getColorBlindModeDesc() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAudio() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSound() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSoundDesc() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMusic() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMusicDesc() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHaptics() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVibration() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVibrationDesc() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLanguageLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLanguageDesc() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVersionInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSelectLevel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLocked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEasy() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMedium() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHard() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> getLevelN() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMoves() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPause() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPausedTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCompleted() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGameOver() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNoMovesLeft() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNextLevelBtn() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTryAgain() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBestTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBestMoves() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStarRequirements() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> getMovesThreshold() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAnyCompletion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNotCompleted() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCompletedStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPlay() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getReplay() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> getMaxMovesLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHowToPlay() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSkip() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPrevious() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLetsPlay() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutPage1Title() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutPage1Desc() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutPage2Title() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutPage2Desc() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutPage3Title() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutPage3Desc() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutPage4Title() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutPage4Desc() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutCornerLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutColorChangesTo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutTapColor() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> getTutExpandedN() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> getTutInitialGroupN() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutAfterExpandDesc() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutBeforeExpandDesc() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTutWinDesc() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component22() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component25() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component27() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component29() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component30() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> component31() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component32() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component33() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component34() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component35() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component36() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component37() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component38() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component39() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component40() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component41() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component42() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component43() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> component44() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component45() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component46() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component47() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component48() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component49() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> component50() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component51() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component52() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component53() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component54() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component55() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component56() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component57() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component58() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component59() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component60() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component61() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component62() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component63() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component64() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component65() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component66() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> component67() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> component68() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component69() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component70() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component71() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, java.lang.String> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.ui.AppStrings copy(@org.jetbrains.annotations.NotNull()
    java.lang.String back, @org.jetbrains.annotations.NotNull()
    java.lang.String settings, @org.jetbrains.annotations.NotNull()
    java.lang.String mainMenu, @org.jetbrains.annotations.NotNull()
    java.lang.String resume, @org.jetbrains.annotations.NotNull()
    java.lang.String restart, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> levelsCompleted, @org.jetbrains.annotations.NotNull()
    java.lang.String start, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> nextLevel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> resumeLevel, @org.jetbrains.annotations.NotNull()
    java.lang.String levels, @org.jetbrains.annotations.NotNull()
    java.lang.String exit, @org.jetbrains.annotations.NotNull()
    java.lang.String accessibility, @org.jetbrains.annotations.NotNull()
    java.lang.String colorBlindMode, @org.jetbrains.annotations.NotNull()
    java.lang.String colorBlindModeDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String audio, @org.jetbrains.annotations.NotNull()
    java.lang.String sound, @org.jetbrains.annotations.NotNull()
    java.lang.String soundDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String music, @org.jetbrains.annotations.NotNull()
    java.lang.String musicDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String haptics, @org.jetbrains.annotations.NotNull()
    java.lang.String vibration, @org.jetbrains.annotations.NotNull()
    java.lang.String vibrationDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String languageLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String languageDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String versionInfo, @org.jetbrains.annotations.NotNull()
    java.lang.String selectLevel, @org.jetbrains.annotations.NotNull()
    java.lang.String locked, @org.jetbrains.annotations.NotNull()
    java.lang.String easy, @org.jetbrains.annotations.NotNull()
    java.lang.String medium, @org.jetbrains.annotations.NotNull()
    java.lang.String hard, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> levelN, @org.jetbrains.annotations.NotNull()
    java.lang.String moves, @org.jetbrains.annotations.NotNull()
    java.lang.String time, @org.jetbrains.annotations.NotNull()
    java.lang.String pause, @org.jetbrains.annotations.NotNull()
    java.lang.String pausedTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String completed, @org.jetbrains.annotations.NotNull()
    java.lang.String gameOver, @org.jetbrains.annotations.NotNull()
    java.lang.String noMovesLeft, @org.jetbrains.annotations.NotNull()
    java.lang.String nextLevelBtn, @org.jetbrains.annotations.NotNull()
    java.lang.String tryAgain, @org.jetbrains.annotations.NotNull()
    java.lang.String bestTime, @org.jetbrains.annotations.NotNull()
    java.lang.String bestMoves, @org.jetbrains.annotations.NotNull()
    java.lang.String starRequirements, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> movesThreshold, @org.jetbrains.annotations.NotNull()
    java.lang.String anyCompletion, @org.jetbrains.annotations.NotNull()
    java.lang.String notCompleted, @org.jetbrains.annotations.NotNull()
    java.lang.String completedStatus, @org.jetbrains.annotations.NotNull()
    java.lang.String play, @org.jetbrains.annotations.NotNull()
    java.lang.String replay, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> maxMovesLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String howToPlay, @org.jetbrains.annotations.NotNull()
    java.lang.String skip, @org.jetbrains.annotations.NotNull()
    java.lang.String previous, @org.jetbrains.annotations.NotNull()
    java.lang.String next, @org.jetbrains.annotations.NotNull()
    java.lang.String letsPlay, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage1Title, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage1Desc, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage2Title, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage2Desc, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage3Title, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage3Desc, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage4Title, @org.jetbrains.annotations.NotNull()
    java.lang.String tutPage4Desc, @org.jetbrains.annotations.NotNull()
    java.lang.String tutCornerLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String tutColorChangesTo, @org.jetbrains.annotations.NotNull()
    java.lang.String tutTapColor, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> tutExpandedN, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> tutInitialGroupN, @org.jetbrains.annotations.NotNull()
    java.lang.String tutAfterExpandDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String tutBeforeExpandDesc, @org.jetbrains.annotations.NotNull()
    java.lang.String tutWinDesc) {
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