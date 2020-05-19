package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.Component;
import archrn.tea_engine.GameObject;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.input.Input;
import archrn.tea_engine.physics.CircleCollider;
import archrn.tea_engine.random.Randoms;
import archrn.tea_engine.rendering.world.CircleRenderer;

import java.awt.*;

/**
 * ElfShoot
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class ElfShoot extends Component
{

    private long cooldown;
    private long lastShot;

    ElfShoot()
    {
        cooldown = 50;
        lastShot = -1;
    }

    @Override
    protected void update()
    {
        long now = System.currentTimeMillis();
        if (now > lastShot + cooldown && Input.shared.getKey(Input.MOUSE_LEFT))
        {
            GameObject projectile = new GameObject();
            float power = Randoms.randomFloat(0.75f, 1.25f);
            projectile.getTransform().setPosition(getTransform().getPosition());
            projectile.getTransform().setScale(
                    new Vector2(0.2f * power, 0.2f * power));
            projectile.addComponent(new Projectile(
                    Vector2.difference(Input.shared.getMousePositionWorld(),
                                       getTransform().getPosition()),
                    15, power, power, false));
            projectile.addComponent(new CircleCollider(0.1f * power))
                      .setPassThrough(true);
            projectile.addComponent(new CircleRenderer(
                    new Color(166, 215, 90)))
                      .setLayer(Layers.PROJECTILES.ordinal());
            getGameObject().getScene().addGameObject(projectile);
            lastShot = now;
        }
    }

}
