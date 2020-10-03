package com.echo.presentation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerListener implements KeyListener {
    private boolean left;
    private boolean right;
    private boolean pause;
    private boolean start;
    private boolean high;
    private boolean exit;
    private boolean menu;
    private boolean about;
    private boolean newGame;
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getExtendedKeyCode() == KeyEvent.VK_S || e.getKeyChar() == 'S'  || e.getKeyChar() == 's') {
            start = true;
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_H || e.getKeyChar() == 'H'  || e.getKeyChar() == 'h') {
            high = true;
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_X || e.getKeyChar() == 'X'  || e.getKeyChar() == 'x') {
            exit = true;
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_M || e.getKeyChar() == 'M'  || e.getKeyChar() == 'm') {
            menu = true;
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_N || e.getKeyChar() == 'N'  || e.getKeyChar() == 'n') {
            newGame = true;
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_P || e.getKeyChar() == 'P'  || e.getKeyChar() == 'p') {
            pause = true;
        }else if (e.getExtendedKeyCode() == KeyEvent.VK_A || e.getKeyChar() == 'A'  || e.getKeyChar() == 'a') {
            about = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }

    }
    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isExit() {
        if (exit) {
            exit = false;
            return true;
        }
        return false;
    }

    public boolean isAbout() {
        if (about) {
            about = false;
            return true;
        }
        return false;
    }

    public boolean isMenu() {
        if (menu) {
            menu = false;
            return true;
        }
        return false;
    }

    public boolean isNew() {
        if (newGame) {
            newGame = false;
            return true;
        }
        return false;
    }

    public void reset() {
        left = false;
        right = false;
        start = false;
        exit = false;
        menu = false;
        newGame = false;
    }

    public boolean isPlayPause() {
        if (pause) {
            pause = false;
            return true;
        }
        return false;
    }

    public boolean isHigh() {
        if (high) {
            high = false;
            return true;
        }
        return false;
    }


}
