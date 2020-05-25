package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.GameObject;
import archrn.tea_engine.animation.Animator;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.physics.CircleCollider;
import archrn.tea_engine.rendering.world.SpriteRenderer;

/**
 * ElfGameObject
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class Elf extends GameObject
{

    Elf()
    {
        addComponent(new SpriteRenderer()).setLayer(Layers.PLAYER.ordinal());
        addComponent(new CircleCollider(0.5f,
                                        new Vector2(0, -0.15f)));
        addComponent(new Animator(Animations.elf().idle()));
        addComponent(new ElfMovement());
        addComponent(new ElfShoot());
        addComponent(new ElfCoins());
    }

}
