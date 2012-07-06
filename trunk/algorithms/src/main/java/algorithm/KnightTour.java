package algorithm;

import java.util.ArrayList;
import java.util.List;

public class KnightTour {
	private int[][] table = null;
	private int m = 0;
	
	public KnightTour(int m) {
		this.m = m;
		table = new int[m][m];
	}
	
	public int walkFrom(int line, int column) {
		int steps = 0;
		while (table[line][column] != 1) {
			steps++;
			table[line][column] = 1;
			// Warnsdorff's algorithm
			int minNumMovesFrom = Integer.MAX_VALUE;
			int[] nextMove = {line, column};
			for (int[] move : getPossibleMovesFrom(line, column)) {
				int numMovesFromMove = 
					getPossibleMovesFrom(move[0], move[1]).size();
				if (numMovesFromMove < minNumMovesFrom) {
					minNumMovesFrom = numMovesFromMove;
					nextMove = move;
				}
			}
			line = nextMove[0];
			column = nextMove[1];
		}
		clear();
		return steps;
	}
	
	private void clear() {
		table = new int[m][m];
	}

	private List<int[]> getPossibleMovesFrom(int line, int column) {
		List<int[]> positions = new ArrayList<int[]>();
		if (line + 2 <= m - 1) {
			if (column + 1 <= m - 1 &&
					table[line + 2][column + 1] == 0) {
				positions.add(new int[] {line + 2, column + 1});
			}
			if (column - 1 >= 0 &&
					table[line + 2][column - 1] == 0) {
				positions.add(new int[] {line + 2, column - 1});
			}
		}
		if (line + 1 <= m - 1) {
			if (column + 2 <= m - 1 &&
					table[line + 1][column + 2] == 0) {
				positions.add(new int[] {line + 1, column + 2});
			}
			if (column - 2 >= 0 &&
					table[line + 1][column - 2] == 0) {
				positions.add(new int[] {line + 1, column - 2});
			}
		}
		if (line - 2 >= 0) {
			if (column + 1 <= m - 1 &&
					table[line - 2][column + 1] == 0) {
				positions.add(new int[] {line - 2, column + 1});
			}
			if (column - 1 >= 0 &&
					table[line - 2][column - 1] == 0) {
				positions.add(new int[] {line - 2, column - 1});
			}
		}
		if (line - 1 >= 0) {
			if (column + 2 <= m - 1 &&
					table[line - 1][column + 2] == 0) {
				positions.add(new int[] {line - 1, column + 2});
			}
			if (column - 2 >= 0 &&
					table[line - 1][column - 2] == 0) {
				positions.add(new int[] {line - 1, column - 2});
			}
		}
		return positions;
	}
}
