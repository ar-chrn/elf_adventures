package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.physics.Collider;
import archrn.tea_engine.random.Randoms;

/**
 * Projectile
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class Projectile extends Movement
{

    private Vector2 direction;
    private boolean enemy;
    private float damageMin;
    private float damageMax;
    private float knockback;

    Projectile(Vector2 direction, float speed, float damage, boolean enemy)
    {
        this(direction, speed, damage, damage, enemy);
    }

    Projectile(Vector2 direction, float speed, float damageMin, float damageMax,
               boolean enemy)
    {
        super(speed);
        this.direction = direction;
        this.enemy = enemy;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.knockback = 0.2f;
    }

    @Override
    protected void start()
    {
        super.start();
        getGameObject().addComponent(new AutoDestroy(2000));
    }

    @Override
    protected void update()
    {
        move(direction);
    }

    @Override
    protected void onCollisionEnter(Collider other)
    {
        Health health = other.getGameObject().getComponentOfType(Health.class);
        if (health != null)
        {
            if (enemy != health.isEnemy())
            {
                health.takeDamage(Randoms.randomFloat(damageMin, damageMax));
                other.getGameObject().getTransform().getPosition()
                     .add(Vector2.multiplied(direction.normalized(), knockback));
                this.getGameObject().destroy();
                this.getGameObject().setActive(false);
            }
        }
    }

}
