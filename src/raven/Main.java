package raven;

import java.awt.Frame;

import com.sun.j3d.utils.applet.MainFrame;

import dddview.View3D;
import raven.game.RavenGame;
import raven.ui.GameCanvas;
import raven.ui.RavenUI;
import raven.utils.Log;
import raven.utils.Log.Level;

public class Main {
	private static RavenUI ui;
	private static RavenGame game;
	
    public static void main(String args[]) {
    	Log.setLevel(Level.DEBUG);
    	
    	Frame frame = new MainFrame(new View3D(404,388), 1024, 768);
    	game = new RavenGame();
    	ui = new RavenUI(game);
    	
    	
    	gameLoop();
	}
    
	//////////////////////////////////////////////////////////////////////////
	// Game simulation!!!!!!!!!!!!!!!!!!!!!!!!!!

	private static void gameLoop() {
    	
		
    	Log.info("raven", "Starting game...");
    	
    	long lastTime = System.nanoTime();
    	
    	while (true) {
    		// TODO Resize UI if the map changes!
    		long currentTime = System.nanoTime();

    		game.update((currentTime - lastTime) * 1.0e-9);
    		
    		// Always dispose the canvas
    		try {
    			GameCanvas.startDrawing(game.getMap().getSizeX(), game.getMap().getSizeY());
    			game.render();
    		} finally {
    			GameCanvas.stopDrawing();
    		}
    		
    		long millisToNextUpdate = Math.max(0, (1000 / 60) - ((System.nanoTime() - currentTime) / 1000000));
			lastTime = currentTime;
			try {
				Thread.sleep(millisToNextUpdate);
			} catch (InterruptedException e) {
				break;
			}
    	}
    }


}
