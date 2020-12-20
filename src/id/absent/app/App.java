package id.absent.app;

import id.absent.app.model.Absent;
import id.absent.app.view.AbsentFrame;

import javax.swing.*;

public class App {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AbsentFrame().setVisible(true);
            }
        });
    }
}
