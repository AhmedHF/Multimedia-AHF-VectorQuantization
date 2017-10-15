package vectorQuantization;

public class CodeBook {
	int x, y;
	int[][] arr;

	CodeBook() {
		x = y = 0;
	}

	CodeBook(int x, int y) {
		this.x = x;
		this.y = y;
		arr = new int[x][y];
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				arr[i][j] = 0;
	}

}
