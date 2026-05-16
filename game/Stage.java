package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Card;
import model.Enemy;
import model.Player;
import model.Shot;

public class Stage extends JPanel implements ActionListener {
   private static final int[][] DEFAULT_COORDINATES = new int[][]{{1380, 29}, {1200, 59}, {1380, 89}, {780, 109}, {580, 139}, {880, 239}, {790, 259}, {760, 50}, {790, 150}, {1180, 209}, {560, 45}, {510, 70}, {930, 159}, {590, 80}, {530, 60}, {940, 59}, {990, 30}, {920, 200}, {900, 259}, {660, 50}, {540, 90}, {810, 220}, {860, 20}, {740, 180}, {820, 128}, {490, 170}, {700, 30}, {920, 300}, {856, 328}, {456, 320}};

   private static final int STAGE_NUMBER_MIN = 1;
   private static final int STAGE_NUMBER_MAX = 2;
   private static final int STAGE_WITH_ENEMY_SHOTS = 2;
   private static final int BOSS_HIT_INITIAL = 49;
   private static final int BOSS_HIT_SHOW_NEXT_STAGE = 0;
   private static final int BOSS_HIT_BOSS_DEFEATED = -1;

   private final Difficulty difficulty;
   private final int stageNumber;
   private final boolean enemyShotsEnabled;

   private Image background;
   private Player player;
   private Timer timer;
   private boolean inGame;
   private List<Enemy> enemies;
   private boolean bossPhase = true;
   private int bossHit = BOSS_HIT_INITIAL;
   private int[][] coordinates;

   public Stage(Difficulty difficulty, int stageNumber) {
      if (stageNumber < STAGE_NUMBER_MIN || stageNumber > STAGE_NUMBER_MAX) {
         throw new IllegalArgumentException("stageNumber must be " + STAGE_NUMBER_MIN + " or " + STAGE_NUMBER_MAX);
      }
      this.difficulty = difficulty;
      this.stageNumber = stageNumber;
      this.enemyShotsEnabled = stageNumber == STAGE_WITH_ENEMY_SHOTS;
      this.coordinates = new int[DEFAULT_COORDINATES.length][];
      for (int i = 0; i < DEFAULT_COORDINATES.length; ++i) {
         this.coordinates[i] = DEFAULT_COORDINATES[i].clone();
      }

      this.setFocusable(true);
      this.setDoubleBuffered(true);
      ImageIcon bgIcon = new ImageIcon("graphics\\Stage" + stageNumber + ".png");
      this.background = bgIcon.getImage();
      this.player = this.createPlayer();
      this.inGame = true;
      this.initEnemies();
      this.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
               Stage.this.inGame = true;
               Stage.this.player = Stage.this.createPlayer();
               Stage.this.initEnemies();
               Stage.this.bossPhase = true;
               Stage.this.bossHit = BOSS_HIT_INITIAL;
            }

            Stage.this.player.keyPressed(e);
         }

         public void keyReleased(KeyEvent e) {
            Stage.this.player.keyReleased(e);
         }
      });
      this.timer = new Timer(5, this);
      this.timer.start();
   }

   private Player createPlayer() {
      return new Player(this.difficulty);
   }

   private Enemy newEnemy(int x, int y, String filePath, String kind) {
      return new Enemy(x, y, filePath, kind, this.enemyShotsEnabled);
   }

   public void initEnemies() {
      this.enemies = new ArrayList<>();

      for (int i = 0; i < this.coordinates.length; ++i) {
         this.enemies.add(this.newEnemy(this.coordinates[i][0], this.coordinates[i][1], "", "normal"));
      }

   }

   public void initBoss() {
      this.enemies = new ArrayList<>();
      this.enemies.add(this.newEnemy(640, 50, "graphics\\Boss.png", "Boss"));
   }

   public void checkCollisions() {
      Rectangle playerBounds = this.player.getBounds();

      for (int i = 0; i < this.enemies.size(); ++i) {
         Enemy enemy = this.enemies.get(i);
         Rectangle enemyBounds = enemy.getBounds();
         if (playerBounds.intersects(enemyBounds)) {
            this.player.setAlive(false);
            enemy.setVisible(false);
            this.inGame = false;
         }

         List<Shot> enemyShots = enemy.getShots();
         for (int j = 0; j < enemyShots.size(); ++j) {
            Shot enemyShot = enemyShots.get(j);
            if (enemyShot.isVisible() && playerBounds.intersects(enemyShot.getBounds())) {
               this.player.setAlive(false);
               enemyShot.setVisible(false);
               this.inGame = false;
            }
         }
      }

      List<Shot> shots = this.player.getShots();

      for (int i = 0; i < shots.size(); ++i) {
         Shot playerShot = shots.get(i);
         if (!playerShot.isVisible()) continue;
         Rectangle playerShotBounds = playerShot.getBounds();

         for (int e = 0; e < this.enemies.size(); ++e) {
            List<Shot> enemyShots = this.enemies.get(e).getShots();
            for (int j = 0; j < enemyShots.size(); ++j) {
               Shot enemyShot = enemyShots.get(j);
               if (enemyShot.isVisible() && playerShotBounds.intersects(enemyShot.getBounds())) {
                  playerShot.setVisible(false);
                  enemyShot.setVisible(false);
                  break;
               }
            }
            if (!playerShot.isVisible()) break;
         }
      }

      for (int i = 0; i < shots.size(); ++i) {
         Shot shot = shots.get(i);
         Rectangle shotBounds = shot.getBounds();

         for (int j = 0; j < this.enemies.size(); ++j) {
            Enemy enemy = this.enemies.get(j);
            Rectangle enemyBounds = enemy.getBounds();
            if (shotBounds.intersects(enemyBounds)) {
               shot.setVisible(false);
               if (enemy.getKind().equals("Boss")) {
                  --this.bossHit;
                  if (this.bossHit == BOSS_HIT_BOSS_DEFEATED) {
                     enemy.setVisible(false);
                  }
               } else if (this.difficulty == Difficulty.HARD) {
                  if (shot.getElement() == enemy.getElement()) {
                     enemy.setVisible(false);
                  }
               } else {
                  enemy.setVisible(false);
               }
            }
         }
      }

   }

   public void paint(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      g2.drawImage(this.background, 0, 0, null);
      if (this.inGame) {
         g2.drawImage(this.player.getImage(), this.player.getX(), this.player.getY(), this);
         List<Shot> shots = this.player.getShots();

         for (int i = 0; i < shots.size(); ++i) {
            Shot shot = shots.get(i);
            if (shot.isVisible()) {
               g2.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
            }
         }

         for (int j = 0; j < this.enemies.size(); ++j) {
            List<Shot> enemyShots = this.enemies.get(j).getShots();

            for (int i = 0; i < enemyShots.size(); ++i) {
               Shot enemyShot = enemyShots.get(i);
               if (enemyShot.isVisible()) {
                  g2.drawImage(enemyShot.getImage(), enemyShot.getX(), enemyShot.getY(), this);
               }
            }
         }

         Card[] deck = this.player.getDeck();

         for (int i = 0; i < deck.length; ++i) {
            if (!deck[i].isSelected()) {
               g2.drawImage(deck[i].getNormalImage(), deck[i].getX(), deck[i].getY(), this);
            } else {
               g2.drawImage(deck[i].getSelectedImage(), deck[i].getX(), deck[i].getY(), this);
            }
         }

         for (int i = 0; i < this.enemies.size(); ++i) {
            Enemy enemy = this.enemies.get(i);
            g2.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
         }

         g2.setColor(Color.BLACK);
         g2.drawString("MATE MAIS " + this.enemies.size() + " DRAGÕES", 5, 15);
         g.dispose();
      } else {
         ImageIcon deathIcon = new ImageIcon("graphics\\Death.png");
         g2.drawImage(deathIcon.getImage(), 0, 0, this);
      }

   }

   public void actionPerformed(ActionEvent e) {
      if (this.enemies.size() == 0 && this.bossPhase) {
         this.initBoss();
         this.bossPhase = false;
      } else if (this.enemies.size() == 0 && !this.bossPhase) {
         if (this.stageNumber < STAGE_NUMBER_MAX) {
            java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
            if (window instanceof javax.swing.JFrame) {
               javax.swing.JFrame frame = (javax.swing.JFrame) window;
               Stage nextStage = new Stage(this.difficulty, this.stageNumber + 1);
               frame.setContentPane(nextStage);
               nextStage.requestFocusInWindow();
               frame.revalidate();
               frame.repaint();
               this.timer.stop();
               return;
            }
         }
      }

      for (int i = 0; i < this.enemies.size(); ++i) {
         Enemy enemy = this.enemies.get(i);
         List<Shot> list = enemy.getShots();
         for (int j = 0; j < list.size(); ++j) {
            if (!list.get(j).isSecondary()) {
               list.get(j).setVisible(false);
            }
         }
      }

      for (int i = 0; i < this.player.getShots().size(); ++i) {
         if (!this.player.getShots().get(i).isSecondary()) {
            this.player.getShots().get(i).setVisible(false);
         }
      }

      if (!this.player.isAlive()) {
         this.inGame = false;
      }

      if (!this.player.isThrusting()) {
         this.player.applyGravity();
      }

      List<Shot> shots = this.player.getShots();

      for (int i = shots.size() - 1; i >= 0; --i) {
         Shot shot = shots.get(i);
         if (shot.isVisible()) {
            shot.move();
         } else {
            shots.remove(i);
         }
      }

      for (int j = this.enemies.size() - 1; j >= 0; --j) {
         List<Shot> enemyShots = this.enemies.get(j).getShots();

         for (int i = enemyShots.size() - 1; i >= 0; --i) {
            Shot enemyShot = enemyShots.get(i);
            if (enemyShot.isVisible()) {
               enemyShot.move();
            } else {
               enemyShot.setVisible(false);
               enemyShots.remove(i);
            }
         }
      }

      for (int i = this.enemies.size() - 1; i >= 0; --i) {
         Enemy enemy = this.enemies.get(i);
         if (enemy.isVisible()) {
            enemy.move();
         } else {
            this.enemies.remove(i);
         }
      }

      this.player.move();
      this.checkCollisions();
      this.repaint();
   }

   public boolean isInGame() {
      return this.inGame;
   }

   public void setInGame(boolean inGame) {
      this.inGame = inGame;
   }

   public int getStageNumber() {
      return this.stageNumber;
   }

   public int[][] getCoordinates() {
      return this.coordinates;
   }

   public void setCoordinates(int[][] coordinates) {
      this.coordinates = coordinates;
   }

   public int getBossHit() {
      return this.bossHit;
   }

   public void setBossHit(int bossHit) {
      this.bossHit = bossHit;
   }

   public boolean shouldShowNextStage() {
      return this.bossHit == BOSS_HIT_SHOW_NEXT_STAGE;
   }

   public void setBackground(ImageIcon image) {
      this.background = image.getImage();
   }
}
