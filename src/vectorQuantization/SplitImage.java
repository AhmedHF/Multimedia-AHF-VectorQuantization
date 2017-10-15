package vectorQuantization;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class SplitImage {

	public int width = 0;
	public int height = 0;
	public int pixels[][];
	public ArrayList<Integer> block = new ArrayList<>();
	public ArrayList<ArrayList<Integer>> Blocks = new ArrayList<>();

	public void readImage(String filePath) {

		File file = new File(filePath);
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		System.out.println("The Image Size " + width + " * " + height + "\n");
		pixels = new int[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixels[i][j] = (image.getRGB(i, j) >> 16) & 0xff;
			}
		}
	}

	public void CreateBlocks() {
		for (int i = 0; i < width; i += 2) {
			for (int j = 0; j < height; j += 2) {
				block.add(pixels[i][j]);
				System.out.print(pixels[i][j]);
				System.out.print(" ");

				block.add(pixels[i][j + 1]);
				System.out.print(pixels[i][j + 1]);
				System.out.println();

				block.add(pixels[i + 1][j]);
				System.out.print(pixels[i + 1][j]);
				System.out.print(" ");

				block.add(pixels[i + 1][j + 1]);
				System.out.print(pixels[i + 1][j + 1]);
				System.out.println();

				System.out.println("--------");
				// Blocks.add(new ArrayList<Integer>(block));
				block.clear();

			}
		}
	}

	public void CreateBlocks(int vwidth, int vheight) { 
		int Start1 = 0, End1 = vwidth, Start2 = 0, End2 = vheight;
		block.clear();
		while (true) {
			for (int i = Start1; i < End1; i++) {
				for (int j = Start2; j < End2; j++) {
					block.add(pixels[i][j]);
				}
			}

			System.out.println(block);
			Blocks.add(new ArrayList<>(block));
			block.clear();
			
			// for (int x = 0; x < block.size(); x++) {
			// block.remove(x);
			// block.clear();
			// }

			Start2 = End1;
			End2 = End2 + vheight;
			if (Start2 == width) {
				Start2 = 0;
				End2 = vheight;
				Start1 = End1;
				End1 = End1 + vwidth;
			}

			if (Start1 == height) {
				break;
			}
			block.clear();
		}
		block.clear();
		// for (int i = 0; i < Blocks.size(); i++) {
		// System.out.println(Blocks.get(i));
		// }
	}

	public void writeImage(String outputFilePath) {
		File fileout = new File(outputFilePath);
		BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image2.setRGB(x, y, (pixels[y][x] << 16) | (pixels[y][x] << 8) | (pixels[y][x]));
			}
		}
		try {
			ImageIO.write(image2, "jpg", fileout);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
