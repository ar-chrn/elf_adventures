package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.GameObject;
import archrn.tea_engine.Scene;
import archrn.tea_engine.camera.CameraGameObject;
import archrn.tea_engine.geometry.Vector2;

import java.awt.*;
import java.util.List;

/**
 * Level1
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class Level1 extends Scene
{

    private Elf player;
    private CameraGameObject camera;

    Level1()
    {
        addPlayer();
        setupCamera();
        addRoom();
        addSpawners();
        addCoins();
    }

    private void addPlayer()
    {
        player = addGameObject(new Elf());
    }

    private void setupCamera()
    {
        camera = (CameraGameObject)getCamera().getGameObject();
        camera.getCamera().setBackgroundColor(new Color(34, 34, 34));
        camera.addCameraFollow(player.getTransform());
        camera.getCamera().setViewSize(new Vector2(15, 10));
    }

    private void addRoom()
    {
        new RoomBuilder(this).makeRoom(
                Vector2.zero(), new Vector2(20f, 10f));
    }

    private void addSpawners()
    {
        // Left
        makeSpawner(new Vector2(-7f, 0), new Vector2(2, 9),
                    0.75f);
        // Right
        makeSpawner(new Vector2(7f, 0), new Vector2(2, 9),
                    0.75f);
        // Top
        makeSpawner(new Vector2(0, 4), new Vector2(18, 1),
                    0.1f);
        // Bottom
        makeSpawner(new Vector2(0, -4), new Vector2(18, 1),
                    0.1f);
    }

    private void addCoins()
    {
        addGameObject(new Coin(new Vector2(6, -3)));
        addGameObject(new Coin(new Vector2(-4, 2)));
        addGameObject(new Coin(new Vector2(7, 4)));
        addGameObject(new Coin(new Vector2(1, -3.5f)));

        CoinDisplay coinDisplay = addGameObject(new CoinDisplay());
        player.getComponentOfType(ElfCoins.class).addCoinDisplay(coinDisplay);
    }

    private void makeSpawner(Vector2 position, Vector2 size, float chance)
    {
        GameObject spawner = addGameObject(new GameObject());
        spawner.addComponent(new EnemySpawner(
                chance,
                List.of(EnemyFactory.EnemyType.DEMON_TINY,
                        EnemyFactory.EnemyType.DEMON_SMALL,
                        EnemyFactory.EnemyType.DEMON_NORMAL,
                        EnemyFactory.EnemyType.DEMON_LARGE),
                List.of(0.5f, 0.2f, 0.2f, 0.05f)));
        spawner.getTransform().setPosition(position);
        spawner.getTransform().setScale(size);
    }

}
