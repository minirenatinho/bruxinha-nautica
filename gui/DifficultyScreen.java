package gui;

import facade.GameFacade;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DifficultyScreen extends JPanel {

    // Button hit areas measured from the Difficulty.png (640x480)
    private static final Rectangle EASY_BUTTON   = new Rectangle(120, 80,  400, 110);
    private static final Rectangle HARD_BUTTON   = new Rectangle(120, 215, 400, 115);

    private BufferedImage background;
    private final JFrame owner;

    public DifficultyScreen(JFrame owner) {
        this.owner = owner;
        setPreferredSize(new Dimension(640, 480));
        loadBackground();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (EASY_BUTTON.contains(e.getPoint())) {
                    startGame(false);
                } else if (HARD_BUTTON.contains(e.getPoint())) {
                    startGame(true);
                }
            }
        });
    }

    private void loadBackground() {
        try {
            background = ImageIO.read(
                getClass().getClassLoader().getResource("graphics/Difficulty.png")
            );
        } catch (IOException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    private void startGame(boolean hard) {
        GameFacade facade = new GameFacade();
        JPanel stage = hard ? facade.startHard() : facade.startEasy();
        owner.setContentPane(stage);
        owner.revalidate();
        stage.requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
