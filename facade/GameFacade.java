package facade;

import game.Difficulty;
import game.Stage;
import javax.swing.JPanel;

public class GameFacade {
   public JPanel startEasy() {
      return new Stage(Difficulty.EASY, 1);
   }

   public JPanel startHard() {
      return new Stage(Difficulty.HARD, 1);
   }
}
