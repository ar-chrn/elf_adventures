package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.GameObject;
import archrn.tea_engine.animation.Animator;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.physics.CircleCollider;
import archrn.tea_engine.rendering.world.SpriteRenderer;

/**
 * EnemyFactory
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class EnemyFactory
{

    enum EnemyType
    {
        DEMON_TINY(Animations.tinyDemon(), 6.75f, 5f,
                   2, 0.2f),
        DEMON_SMALL(Animations.smallDemon(), 4.5f, 7.5f,
                    4, 0.4f),
        DEMON_NORMAL(Animations.normalDemon(), 3.75f, 10f,
                     5, 0.5f),
        DEMON_LARGE(Animations.largeDemon(), 2f, 100f,
                    20, 1f);

        private Animations.AnimationPack animations;
        private float speed;
        private float radius;
        private int health;
        private float dropChance;

        EnemyType(Animations.AnimationPack animations,
                  float speed, float radius, int health, float dropChance)
        {
            this.animations = animations;
            this.speed = speed;
            this.radius = radius;
            this.health = health;
            this.dropChance = dropChance;
        }

        Animations.AnimationPack getAnimations()
        {
            return animations;
        }

        public float getSpeed()
        {
            return speed;
        }

        public float getRadius()
        {
            return radius;
        }

        public int getHealth()
        {
            return health;
        }

        public float getDropChance()
        {
            return dropChance;
        }
    }

    static GameObject getEnemy(EnemyType type)
    {
        GameObject enemy = new GameObject();
        enemy.addComponent(new SpriteRenderer())
             .setLayer(Layers.ENEMIES.ordinal());
        enemy.addComponent(new Animator(type.getAnimations().idle()));
        enemy.addComponent(new CircleCollider(
                type == EnemyType.DEMON_LARGE ? 0.75f : 0.5f,
                new Vector2(0, -0.15f)))
             .setPassThrough(true);
        enemy.addComponent(new Follow(
                ElfMovement.class, type.getSpeed(), type.getRadius(),
                false, true, true,
                type.getAnimations()));
        enemy.addComponent(new Health(type.getHealth(), true));
        enemy.addComponent(new DropCoin(type.getDropChance()));
        enemy.addComponent(new AutoDestroy(20_000));
        return enemy;
    }

}
