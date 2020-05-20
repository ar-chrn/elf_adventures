package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.assets.Sprite;
import archrn.tea_engine.geometry.Vector2Int;
import archrn.tea_engine.rendering.gui.ImageGameObject;
import archrn.tea_engine.rendering.gui.TextGameObject;

import java.awt.*;
import java.io.File;

/**
 * CoinDisplay
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class CoinDisplay extends TextGameObject
{

    CoinDisplay()
    {
        super(new Vector2Int(60, -13),
              new Vector2Int(200, 100),
              "0",
              new Color(250, 203, 62));
        getRenderer().setLayer(Layers.GUI.ordinal());
        setupFont();
    }

    @Override
    protected void onAddedToScene()
    {
        getScene().addGameObject(new ImageGameObject(
                new Vector2Int(35, 35), new Vector2Int(50, 50),
                new Sprite(("/archrn/tea_engine/games/" +
                           "elf_adventures/coin/coin_anim_f0.png")
                                   .replace("/", File.separator))))
                  .getRenderer().setLayer(Layers.GUI.ordinal());
    }

    private void setupFont()
    {
        try
        {
            getRenderer().setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream(
                            ("/archrn/tea_engine/games/elf_adventures/" +
                    "gui/Kenney Mini.ttf").replace("/", File.separator)))
                                      .deriveFont(54f));
        }
        catch (Exception e)
        {
            getRenderer().setFont(getRenderer().getFont().deriveFont(36f));
            e.printStackTrace();
        }
    }

}
