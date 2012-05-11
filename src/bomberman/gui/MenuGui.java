package bomberman.gui;

import bomberman.game.Game;
import messing.around.StdDraw;

public class MenuGui {

public static void main(String args[]) {
	//Game in progress?
	boolean game_stopped=false;
	//I know, I'm lazy but I can not remember Numbers, just Varnames.
	double text_x_newgame=500;
	double text_y_newgame=400;
	double text_x_exit=500;
	double text_y_exit=300;
    		StdDraw.text(text_x_exit, text_y_exit, "Exit");
		StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
	while (game_stopped==true){
		if((StdDraw.mousePressed()==true) &&((StdDraw.mouseX()==text_x_newgame)&&(StdDraw.mouseY()==text_y_newgame))){ 
			final Game g = new Game
			 g.start();
		}
		if((StdDraw.mousePressed()==true) &&((StdDraw.mouseX()==text_x_exit)&&(StdDraw.mouseY()==text_y_exit))) game_stopped = true;
	}
}
}
