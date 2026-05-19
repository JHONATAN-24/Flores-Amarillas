import javax.swing.*;
import java.awt.*;

public class Flores extends JPanel {
    private int x = 50;
    private int y = 50;
    private int diameter = 100;
    private int dx = 4; // desplazamiento por frame

    public Flores() {
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, diameter, diameter); // Flor amarilla
        // Opcional: centro de la flor
        g.setColor(Color.ORANGE);
        g.fillOval(x + diameter/3, y + diameter/3, diameter/3, diameter/3);
    }

    /**
    * Inicia la animación en un hilo separado.
    * Llamar a este método después de que el frame sea visible.
    */
    public void startAnimation() {
        Thread animator = new Thread(() -> {
            while (true) {
                // Actualiza posición
                x += dx;

                // Rebote en los bordes del panel
                if (x < 0) {
                    x = 0;
                    dx = -dx;
                } else if (x + diameter > getWidth()) {
                    x = Math.max(0, getWidth() - diameter);
                    dx = -dx;
                }

                // Pide repintado en el EDT
                repaint();

                // Pausa para controlar la velocidad (Thread.sleep en hilo no-EDT)
                try {
                    Thread.sleep(30); // 30 ms ≈ 33 FPS
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        animator.setDaemon(true);
        animator.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Flores Amarillas");
            Flores panel = new Flores();
            frame.setSize(400, 400);
            frame.add(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            // Iniciar animación después de que el frame sea visible
            panel.startAnimation();
        });
    }
}
