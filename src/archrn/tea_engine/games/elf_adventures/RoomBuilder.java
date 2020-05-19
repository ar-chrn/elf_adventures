package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.Scene;
import archrn.tea_engine.assets.Sprite;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.physics.RectangleCollider;
import archrn.tea_engine.rendering.world.SpriteGameObject;

import java.awt.*;
import java.io.File;

/**
 * RoomBuilder
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class RoomBuilder
{

    private final static String floor = "/archrn/tea_engine/games/" +
            "elf_adventures/floor/".replace("/", File.separator);;
    private final static String walls = "/archrn/tea_engine/games/" +
            "elf_adventures/walls/".replace("/", File.separator);;

    private Scene scene;

    RoomBuilder(Scene scene)
    {
        this.scene = scene;
    }

    void makeRoom(Vector2 position, Vector2 size)
    {
        makeFloor(position, size);
        makeHorizontalWall(
                Vector2.sum(position, new Vector2(0, size.y / 2 + 1)),
                size.x, true);
        makeHorizontalWall(
                Vector2.sum(position, new Vector2(0, -size.y / 2 - 1)),
                size.x, false);
        makeVerticalWall(
                Vector2.sum(position, new Vector2(-size.x / 2 + 0.5f, 0)),
                size.y, true);
        makeVerticalWall(
                Vector2.sum(position, new Vector2(size.x / 2 - 0.5f, 0)),
                size.y, false);
    }

    private void makeFloor(Vector2 position, Vector2 size)
    {
        Sprite tile = new Sprite(floor + "floor_1.png");
        tile.setPixelsPerUnit(16);
        SpriteGameObject floor = scene.addGameObject(new SpriteGameObject(
                position, size, Color.pink, tile));
        floor.getRenderer().setTiled(true);
        floor.getRenderer().setLayer(Layers.FLOOR.ordinal());
    }

    private void makeHorizontalWall(Vector2 position, float width, boolean top)
    {
        Vector2 size = new Vector2(width, 2);

        Sprite wallSprite = new Sprite(walls + "wall_mid.png");
        Sprite wallTopSprite = new Sprite(walls + "wall_top_mid.png");
        wallSprite.setPixelsPerUnit(16);
        wallTopSprite.setPixelsPerUnit(16);

        SpriteGameObject wall = new SpriteGameObject(
                position, size, Color.black, wallSprite);
        SpriteGameObject wallTop = new SpriteGameObject(
                Vector2.sum(position, new Vector2(0, size.y - 0.5f)),
                new Vector2(size.x, 1f), Color.black, wallTopSprite);


        wall.getRenderer().setTiled(true);
        wallTop.getRenderer().setTiled(true);
        wall.addComponent(new RectangleCollider(
                new Vector2(width, 1),
                new Vector2(0, top ? 0.5f : 0.2f)));

        wall.getRenderer().setLayer(
                top ? Layers.BACK_WALLS.ordinal()
                        : Layers.FRONT_WALLS.ordinal());
        wallTop.getRenderer().setLayer(
                top ? Layers.BACK_WALLS.ordinal()
                        : Layers.FRONT_WALLS.ordinal());

        scene.addGameObject(wall);
        scene.addGameObject(wallTop);

        if (top)
        {
            Sprite wallCornerLeft = new Sprite(
                    walls + "wall_corner_left.png");
            Sprite wallCornerRight = new Sprite(
                    walls + "wall_corner_right.png");
            wallCornerLeft.setPixelsPerUnit(16);
            wallCornerRight.setPixelsPerUnit(16);
            SpriteGameObject wallLeft = new SpriteGameObject(
                    Vector2.sum(position,
                                new Vector2(-size.x / 2 + 0.5f, 0)),
                    new Vector2(1, 2),
                    Color.black, wallCornerLeft);
            SpriteGameObject wallRight = new SpriteGameObject(
                    Vector2.sum(position,
                                new Vector2(size.x / 2 - 0.5f, 0)),
                    new Vector2(1, 2),
                    Color.black, wallCornerRight);
            wallLeft.getRenderer().setTiled(true);
            wallRight.getRenderer().setTiled(true);
            wallLeft.getRenderer().setLayer(
                    Layers.BACK_WALLS_OVERLAY.ordinal());
            wallRight.getRenderer().setLayer(
                    Layers.BACK_WALLS_OVERLAY.ordinal());
            scene.addGameObject(wallLeft);
            scene.addGameObject(wallRight);

            Sprite wallCornerTopLeft = new Sprite(
                    walls + "wall_corner_top_left.png");
            Sprite wallCornerTopRight = new Sprite(
                    walls + "wall_corner_top_right.png");
            wallCornerTopLeft.setPixelsPerUnit(16);
            wallCornerTopRight.setPixelsPerUnit(16);
            SpriteGameObject wallTopLeft = new SpriteGameObject(
                    Vector2.sum(position,
                                new Vector2(-size.x / 2 + 0.5f, size.y - 0.5f)),
                    new Vector2(1, 1),
                    Color.black, wallCornerTopLeft);
            SpriteGameObject wallTopRight = new SpriteGameObject(
                    Vector2.sum(position,
                                new Vector2(size.x / 2 - 0.5f, size.y - 0.5f)),
                    new Vector2(1, 1),
                    Color.black, wallCornerTopRight);
            wallTopLeft.getRenderer().setTiled(true);
            wallTopRight.getRenderer().setTiled(true);
            wallTopLeft.getRenderer().setLayer(
                    Layers.BACK_WALLS_OVERLAY.ordinal());
            wallTopRight.getRenderer().setLayer(
                    Layers.BACK_WALLS_OVERLAY.ordinal());
            scene.addGameObject(wallTopLeft);
            scene.addGameObject(wallTopRight);
        }
        else
        {
            Sprite wallCornerBotLeft = new Sprite(
                    walls + "wall_corner_bottom_left.png");
            Sprite wallCornerBotRight = new Sprite(
                    walls + "wall_corner_bottom_right.png");
            wallCornerBotLeft.setPixelsPerUnit(16);
            wallCornerBotRight.setPixelsPerUnit(16);
            SpriteGameObject wallBotLeft = new SpriteGameObject(
                    Vector2.sum(position,
                                new Vector2(-size.x / 2 + 0.5f, size.y - 0.5f)),
                    new Vector2(1, 1),
                    Color.black, wallCornerBotLeft);
            SpriteGameObject wallBotRight = new SpriteGameObject(
                    Vector2.sum(position,
                                new Vector2(size.x / 2 - 0.5f, size.y - 0.5f)),
                    new Vector2(1, 1),
                    Color.black, wallCornerBotRight);
            wallBotLeft.getRenderer().setTiled(true);
            wallBotRight.getRenderer().setTiled(true);
            wallBotLeft.getRenderer().setLayer(
                    Layers.FRONT_WALLS_OVERLAY.ordinal());
            wallBotRight.getRenderer().setLayer(
                    Layers.FRONT_WALLS_OVERLAY.ordinal());
            scene.addGameObject(wallBotLeft);
            scene.addGameObject(wallBotRight);
        }
    }

    private void makeVerticalWall(Vector2 position, float height, boolean left)
    {
        position.add(new Vector2(0, 1));
        Vector2 size = new Vector2(1f, height + 2);

        Sprite wallTopSprite = new Sprite(walls + (left ?
                "wall_side_mid_right.png" : "wall_side_mid_left.png"));
        wallTopSprite.setPixelsPerUnit(16);

        SpriteGameObject wallTop = new SpriteGameObject(
                position, size, Color.black, wallTopSprite);
        wallTop.addComponent(new RectangleCollider(
                new Vector2(0.25f, height + 2),
                new Vector2(left ? -0.5f : 0.5f, 0)));

        wallTop.getRenderer().setTiled(true);
        wallTop.getRenderer().setLayer(Layers.FRONT_WALLS.ordinal());

        scene.addGameObject(wallTop);
    }

}
