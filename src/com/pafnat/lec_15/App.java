package com.pafnat.lec_15;

import javax.swing.*;

/**
 * Simple Swing Application example
 */

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame("SwingWorker Demo");
            }
        });
    }
}
