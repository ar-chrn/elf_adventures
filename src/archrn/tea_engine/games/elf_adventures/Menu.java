package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.Scene;
import archrn.tea_engine.SceneManager;
import archrn.tea_engine.assets.Sprite;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.geometry.Vector2Int;
import archrn.tea_engine.gui.ButtonGameObject;
import archrn.tea_engine.rendering.gui.ImageGameObject;
import archrn.tea_engine.rendering.gui.PanelGameObject;
import archrn.tea_engine.rendering.gui.TextGameObject;
import archrn.tea_engine.rendering.gui.TextRenderer;
import archrn.tea_engine.rendering.world.SpriteGameObject;

import java.awt.*;
import java.io.File;

/**
 * MenuScene
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class Menu extends Scene
{

    private String background = "/archrn/tea_engine/games/" +
            "elf_adventures/walls/wall_mid.png"
                    .replace("/", File.separator);
    private String font = "/archrn/tea_engine/games/elf_adventures/" +
            "gui/Kenney Mini.ttf".replace("/", File.separator);
    private String lmb = "/archrn/tea_engine/games/elf_adventures/gui/" +
            "LMB.png".replace("/", File.separator);
    private String wasd = "/archrn/tea_engine/games/elf_adventures/gui/" +
            "WASD.png".replace("/", File.separator);

    private Color tint = new Color(34, 34, 34);
    private Color dark = new Color(70, 59, 58);
    private Color light = new Color(208, 191, 172);
    private Color transparentLight = new Color(208, 191, 172, 128);

    Menu()
    {
        getCamera().setBackgroundColor(tint);
        addBackground();
        addTitle();
        addPlayButton();
        addControls();
    }

    private void addBackground()
    {
        Sprite tile = new Sprite(background);
        tile.setPixelsPerUnit(8);
        SpriteGameObject floor = addGameObject(new SpriteGameObject(
                Vector2.zero(), getCamera().getViewSize(), Color.pink, tile));
        floor.getRenderer().setTiled(true);
        floor.getRenderer().setLayer(Layers.BACKGROUND.ordinal());
    }

    private void addTitle()
    {
        Vector2Int size = new Vector2Int(600, 112);
        Vector2Int position = new Vector2Int(-size.x / 2, -size.y - 150);
        TextRenderer text = addGameObject(new TextGameObject(
                position, size, "Elf Adventures", light,
                true, true))
                .getRenderer();
        try
        {
            text.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream(font)).deriveFont(72f));
        }
        catch (Exception e)
        {
            text.setFont(text.getFont().deriveFont(72f));
            e.printStackTrace();
        }
    }

    private void addPlayButton()
    {
        Vector2Int size = new Vector2Int(450, 112);
        Vector2Int border = new Vector2Int(10, 10);
        Vector2Int position = new Vector2Int(-size.x / 2,
                                             size.y / 2 - 150);
        addGameObject(new PanelGameObject(
                Vector2Int.difference(position,
                                      Vector2Int.quotient(border, 2)),
                Vector2Int.sum(size, border), tint, 60,
                true));
        ButtonGameObject button = addGameObject(new ButtonGameObject(
                position, size, "Play", light, true,
                SceneManager.shared::loadNextScene));
        button.getText().getTransform().setPosition(new Vector2(0, -19f));
        TextRenderer text = button.getText().getRenderer();
        text.setLayer(Layers.GUI_FRONT.ordinal());
        text.setColor(dark);
        button.getRenderer().setRadius(60);
        try
        {
            text.setFont(Font.createFont(
                    Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream(font)).deriveFont(72f));
        }
        catch (Exception e)
        {
            text.setFont(text.getFont().deriveFont(72f));
            e.printStackTrace();
        }
    }

    private void addControls()
    {
        Vector2Int size = new Vector2Int(450, 217);
        Vector2Int position = new Vector2Int(-size.x / 2,
                                             -size.y / 2 + 150);
        addGameObject(new PanelGameObject(position, size,
                                          transparentLight, 60,
                                          true));
        addGameObject(new ImageGameObject(
                new Vector2Int(position.x + size.x / 4,
                               position.y + size.y / 2),
                new Vector2Int(225, 157),
                new Sprite(wasd), true))
                .getRenderer().setLayer(Layers.GUI_FRONT.ordinal());
        addGameObject(new ImageGameObject(
                new Vector2Int(position.x + size.x / 2 + size.x / 4,
                               position.y + size.y / 2),
                new Vector2Int(150, 217),
                new Sprite(lmb), true))
                .getRenderer().setLayer(Layers.GUI_FRONT.ordinal());
    }

}
