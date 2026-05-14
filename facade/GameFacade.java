package facade;

import game.Difficulty;
import game.Stage;
import javax.swing.JPanel;

public class GameFacade {
   private Stage stage1Easy = new Stage(Difficulty.EASY, 1);
   private Stage stage1Hard = new Stage(Difficulty.HARD, 1);
   private Stage stage2Easy = new Stage(Difficulty.EASY, 2);
   private Stage stage2Hard = new Stage(Difficulty.HARD, 2);

   public JPanel startEasy() {
      JPanel panel = this.stage1Easy;
      if (this.stage1Easy.shouldShowNextStage()) {
         panel = this.stage2Easy;
      }

      return panel;
   }

   public JPanel startHard() {
      JPanel panel = this.stage1Hard;
      if (this.stage1Hard.shouldShowNextStage()) {
         panel = this.stage2Hard;
      }

      return panel;
   }
}
