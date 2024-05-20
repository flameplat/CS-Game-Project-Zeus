package game;
import game.engine.CLIGameController;
import game.engine.Player;
import game.utilities.Color;

public class Main {

    public static void main(String[] args) {
        CLIGameController controller= new CLIGameController();
        Player player=controller.getActivePlayer();
        for(int i=0;i<5;i++){
            controller.playColorBonus(player, Color.GREEN);
            player.getScoreSheet().displayGreenRealm();
        }

        System.out.println(player.getGameScore());

    }

}
