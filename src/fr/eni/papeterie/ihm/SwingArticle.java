package fr.eni.papeterie.ihm;

import javax.swing.*;

public class SwingArticle {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ControlsArticle.start();
            }
        });
    }
}
