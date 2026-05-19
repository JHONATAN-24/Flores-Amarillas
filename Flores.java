import javax.swing.*;
import java.awt.*;

public class Flores extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillOval(50, 50, 100, 100); // Flor amarilla
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flores Amarillas");
        frame.setSize(400, 400);
        frame.add(new Flores());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
