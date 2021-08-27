package run;

import controller.NumOfPlayer;

import java.awt.EventQueue;

import controller.Controller;
import controller.IController;
import controller.Draw;
import model.Game;

public class Test {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game game=new Game();
					IController controller=new Controller(game, new NumOfPlayer(), new Draw());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
