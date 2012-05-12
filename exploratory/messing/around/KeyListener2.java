package messing.around;

import bomberman.gui.StdDraw;

public class KeyListener2 {
	public static void main(String[] args) {
		while(true) {
			if(StdDraw.hasNextKeyTyped())
				System.out.println(StdDraw.nextKeyTyped());;
		}
	}
}
