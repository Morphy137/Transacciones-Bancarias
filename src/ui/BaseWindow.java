package ui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BaseWindow extends JFrame {
  protected int xMouse; // Para manejar la posición del mouse
  protected int yMouse;

  public BaseWindow() {
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        xMouse = e.getX();
        yMouse = e.getY();
      }
    });
    addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();
        setLocation(x - xMouse, y - yMouse);
      }
    });
  }

  protected void addCloseFunctionality(JLabel label) {
    label.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        System.exit(0);
      }
    });
  }
}