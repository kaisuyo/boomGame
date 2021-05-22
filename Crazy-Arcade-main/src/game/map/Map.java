package game.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import game.constant.Constain;
import game.constant.MapDefine;
import game.controller.Controller;
import game.items.ItemBomb;
import game.items.ItemBombSize;
import objgame.character.Bomber;
import objgame.character.Monster;
import objgame.objmap.Box;
import objgame.objmap.Brick;
import objgame.objmap.Rock;
import objgame.objmap.Tree;

public class Map {
	public static int[][] matrix = new int[20][20];
	
	public Map() {
		readMap();
	}
	public Map(Controller c) {
		readMap();
		for (int i = 0; i < Constain.NUMBER_BLOCK_HEIGHT; i++) {
			for (int j = 0; j < Constain.NUMBER_BLOCK_WIDTH; j++) {
				if (matrix[i][j] == 0) {
					continue;
				}
				
				switch (matrix[i][j]) {
				case MapDefine.PLAYER: {
					c.getPlayers().add(new Bomber(j, i));
					matrix[i][j] = 0;
					break;
				}
				case MapDefine.MONSTER: {
					c.getMonsters().add(new Monster(j, i));
					matrix[i][j] = 0;
					break;
				}
				case MapDefine.BRICK: {
					c.getMapItems().add(new Brick(j, i));
					break;
				}
				case MapDefine.ROCK: {
					c.getMapItems().add(new Rock(j, i));
					break;
				}
				case MapDefine.TREE: {
					c.getMapItems().add(new Tree(j, i));
					break;
				}
				case MapDefine.ITEM_BOMB: {
					c.getItems().add(new ItemBomb(j, i));
					break;
				}
				case MapDefine.ITEM_BOMBSIZE: {
					c.getItems().add(new ItemBombSize(j, i));
					break;
				}
				case MapDefine.BOX_EXPLODE: {
					c.getBoxes().add(new Box(j, i));
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + matrix[i][j]);
				}
			}
		}
	}

	public void readMap() {
		try {
			Scanner sc = new Scanner(new File("src/MAP_TEXT/map1.txt"));
			for (int i = 0; i < Constain.NUMBER_BLOCK_HEIGHT; i++) {
				for (int j = 0; j < Constain.NUMBER_BLOCK_WIDTH; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Map();
	}
}
