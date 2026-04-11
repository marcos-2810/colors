package com.colors.game.domain

import com.colors.game.data.model.GameColor
import com.colors.game.data.model.Position

/**
 * Core flood-fill engine. Pure functions — no side effects.
 *
 * Grid representation: flat List<GameColor>, row-major order.
 *   index = row * cols + col
 *
 * All operations are O(N) where N = rows * cols.
 * Uses iterative BFS (no recursion → no stack overflow on large grids).
 */
object FloodFillEngine {

    // -----------------------------------------------------------------------
    // BFS flood-fill: returns all positions connected to (startRow, startCol)
    // that share its color (4-directional adjacency only).
    // -----------------------------------------------------------------------
    fun getConnectedGroup(
        cells: List<GameColor>,
        rows: Int,
        cols: Int,
        startRow: Int,
        startCol: Int
    ): Set<Position> {
        val targetColor = cells[startRow * cols + startCol]
        val visited = HashSet<Position>(rows * cols)
        val queue = ArrayDeque<Position>(rows * cols / 4)

        queue.add(Position(startRow, startCol))

        while (queue.isNotEmpty()) {
            val pos = queue.removeFirst()
            if (!pos.isValid(rows, cols)) continue
            if (pos in visited) continue
            if (cells[pos.row * cols + pos.col] != targetColor) continue

            visited.add(pos)
            queue.add(Position(pos.row - 1, pos.col))
            queue.add(Position(pos.row + 1, pos.col))
            queue.add(Position(pos.row, pos.col - 1))
            queue.add(Position(pos.row, pos.col + 1))
        }

        return visited
    }

    // -----------------------------------------------------------------------
    // Apply a color change to a group of cells and expand automatically.
    //
    // Steps:
    //  1. Change all cells in [group] to [newColor].
    //  2. Flood-fill from any position in [group] to absorb all newly
    //     adjacent cells of the same newColor.
    //
    // Returns: (newCells, expandedGroup)
    // -----------------------------------------------------------------------
    fun applyColorChange(
        cells: List<GameColor>,
        rows: Int,
        cols: Int,
        group: Set<Position>,
        newColor: GameColor
    ): Pair<List<GameColor>, Set<Position>> {
        val newCells = cells.toMutableList()

        for (pos in group) {
            newCells[pos.row * cols + pos.col] = newColor
        }

        // The expanded group is found from any position in the group (all now newColor)
        val anchor = group.first()
        val expandedGroup = getConnectedGroup(newCells, rows, cols, anchor.row, anchor.col)

        return Pair(newCells, expandedGroup)
    }

    // -----------------------------------------------------------------------
    // Compute BFS distance from every cell in [sourceGroup] to all others.
    // Used for wave animation ordering.
    // -----------------------------------------------------------------------
    fun bfsDistanceMap(
        rows: Int,
        cols: Int,
        sourceGroup: Set<Position>
    ): Map<Position, Int> {
        val distMap = HashMap<Position, Int>(rows * cols)
        val queue = ArrayDeque<Position>(rows * cols)

        for (pos in sourceGroup) {
            distMap[pos] = 0
            queue.add(pos)
        }

        while (queue.isNotEmpty()) {
            val pos = queue.removeFirst()
            val dist = distMap[pos]!!
            val neighbors = listOf(
                Position(pos.row - 1, pos.col),
                Position(pos.row + 1, pos.col),
                Position(pos.row, pos.col - 1),
                Position(pos.row, pos.col + 1)
            )
            for (n in neighbors) {
                if (n.isValid(rows, cols) && n !in distMap) {
                    distMap[n] = dist + 1
                    queue.add(n)
                }
            }
        }

        return distMap
    }

    fun isGridComplete(cells: List<GameColor>): Boolean {
        if (cells.isEmpty()) return true
        val first = cells.first()
        return cells.all { it == first }
    }

    private fun Position.isValid(rows: Int, cols: Int) =
        row in 0 until rows && col in 0 until cols
}
