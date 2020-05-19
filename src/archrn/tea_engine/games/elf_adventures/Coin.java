package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.GameObject;
import archrn.tea_engine.animation.Animator;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.physics.CircleCollider;
import archrn.tea_engine.rendering.world.SpriteRenderer;

/**
 * Coin
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class Coin extends GameObject
{

    Coin()
    {
        this(Vector2.zero());
    }

    Coin(Vector2 position)
    {
        getTransform().setPosition(position);
        addComponent(new SpriteRenderer()).setLayer(Layers.ITEMS.ordinal());
        addComponent(new CircleCollider(0.125f));
        addComponent(new Animator(Animations.coin().idle()));
        addComponent(new Follow(ElfMovement.class, 10f, 3));
    }

}
