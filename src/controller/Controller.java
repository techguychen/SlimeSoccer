package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    // Keyboard states (true = pressed, false = not pressed)
    private static boolean[] keyboardState = new boolean[525];
	
    public static boolean keyboardKeyState(int key) {
        return keyboardState[key];
    }

	public void keyPressed(KeyEvent event) {
        keyboardState[event.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
        keyboardState[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}
}
