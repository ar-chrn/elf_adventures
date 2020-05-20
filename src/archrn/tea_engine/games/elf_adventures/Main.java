package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.Game;
import archrn.tea_engine.Screen;
import archrn.tea_engine.Time;
import archrn.tea_engine.camera.Camera;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.geometry.Vector2Int;

/**
 * Main
 *
 * @author archrn
 * @version 0
 * @since 0
 */
public class Main
{

    public static void main(String[] args)
    {
        Game game = new Game();
        Time.shared.setFrameRate(60);
        Screen.shared.setMinimumSize(new Vector2Int(800, 600));
        Screen.shared.setSize(new Vector2Int(800, 600));
        game.getSceneManager().addScene(new Menu());
        game.getSceneManager().addScene(new Level1());
        game.start();
        Camera.getMain().setViewSize(new Vector2(15, 10));
    }

}
