package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.Component;

/**
 * Health
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class Health extends Component
{

    private int maxHealth;
    private float health;
    private boolean enemy;

    Health(int maxHealth, boolean enemy)
    {
        this.maxHealth = maxHealth;
        health = maxHealth;
        this.enemy = enemy;
    }

    float getHealth()
    {
        return health;
    }

    boolean isEnemy()
    {
        return enemy;
    }

    void takeDamage(float damage)
    {
        health = Math.max(health - damage, -0);
        if (health <= 0)
        {
            getGameObject().destroy();
        }
    }

}
