/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import javax.swing.JFrame;

/**
 *
 * @author emre
 */
public class Main {

    public static void main(String args[]) {

        NewGameSettingsWindow window = new NewGameSettingsWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
}
