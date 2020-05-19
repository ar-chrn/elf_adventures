package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.Component;
import archrn.tea_engine.GameObject;
import archrn.tea_engine.Time;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.random.Randoms;

import javax.swing.*;
import java.util.List;

/**
 * EnemySpawner
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class EnemySpawner extends Component
{

    private float chance;
    private List<EnemyFactory.EnemyType> enemies;
    private List<Float> enemyChances;
    private float sum;
    private boolean spawning;

    EnemySpawner(float spawnChance,
                 List<EnemyFactory.EnemyType> enemies,
                 List<Float> enemyChances)
    {
        if (enemies.size() != enemyChances.size())
        {
            throw new IllegalArgumentException();
        }

        this.chance = spawnChance;
        this.enemies = enemies;
        this.enemyChances = enemyChances;

        sum = 0;
        enemyChances.forEach(chance -> sum += chance);

        spawning = false;
        Timer timer = new Timer(1000, event -> startSpawning());
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    protected void update()
    {
        if (!spawning)
        {
            return;
        }
        if (Randoms.chance(chance * Time.shared.getDeltaTime()))
        {
            spawnEnemy();
        }
    }

    void startSpawning()
    {
        spawning = true;
    }
    
    void stopSpawning()
    {
        spawning = false;
    }

    private void spawnEnemy()
    {
        Vector2 spawnPosition = Randoms.randomVector2InArea(
                getTransform().getPosition(), getTransform().getScale());
        float roll = Randoms.randomFloat(0, sum);
        for (int i = 0; i < enemies.size(); ++i)
        {
            if (roll < enemyChances.get(i))
            {
                GameObject enemy = EnemyFactory.getEnemy(enemies.get(i));
                enemy.getTransform().setPosition(spawnPosition);
                getGameObject().getScene().addGameObject(enemy);
                break;
            }
            roll -= enemyChances.get(i);
        }
    }

}
