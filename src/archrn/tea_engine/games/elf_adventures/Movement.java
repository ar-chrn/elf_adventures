package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.Component;
import archrn.tea_engine.Time;
import archrn.tea_engine.animation.Animator;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.physics.Collider;

/**
 * Movement
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class Movement extends Component
{

    private float speed;
    private Animations.AnimationPack animations;
    private boolean flip;
    private boolean updateAnimation;

    private Collider collider;
    private Animator animator;
    private boolean moveWithCollider;

    Movement(float speed)
    {
        this(speed, false, false, null);
    }

    Movement(float speed, boolean flip)
    {
        this(speed, flip, false, null);
    }

    Movement(float speed, boolean flip, boolean updateAnimation,
             Animations.AnimationPack animations)
    {
        this.speed = speed;
        this.flip = flip;
        this.updateAnimation = updateAnimation;
        this.animations = animations;
        this.moveWithCollider = true;
    }

    @Override
    protected void start()
    {
        collider = getGameObject().getComponentOfType(Collider.class);
        animator = getGameObject().getComponentOfType(Animator.class);
    }

    void setMoveWithCollider(boolean moveWithCollider)
    {
        this.moveWithCollider = moveWithCollider;
    }

    void move(Vector2 direction)
    {
        direction = direction.normalized();
        direction.multiply(speed * Time.shared.getDeltaTime());
        flip(direction);
        updateAnimation(direction.magnitude() > 0);
        actuallyMove(direction);
    }

    void moveTowards(Vector2 position)
    {
        Vector2 originalDirection = Vector2.difference(
                position, getTransform().getPosition());
        Vector2 direction = Vector2.multiplied(
                originalDirection.normalized(),
                Time.shared.getDeltaTime() * speed);
        direction = Vector2.min(originalDirection, direction);
        flip(direction);
        updateAnimation(direction.magnitude() > 0);
        actuallyMove(direction);
    }

    private void actuallyMove(Vector2 direction)
    {
        if (collider != null && moveWithCollider)
        {
            collider.move(direction);
        }
        else
        {
            getTransform().setPosition(Vector2.sum(
                    getTransform().getPosition(), direction));
        }
    }

    private void flip(Vector2 movementVector)
    {
        if (!flip)
        {
            return;
        }
        if (movementVector.x < 0)
        {
            getTransform().setScale(new Vector2(-1, 1));
        }
        else if (movementVector.x > 0)
        {
            getTransform().setScale(Vector2.one());
        }
    }

    private void updateAnimation(boolean run)
    {
        if (!updateAnimation || animator == null || animations == null)
        {
            return;
        }
        if (run)
        {
            animator.setAnimation(animations.run());
        }
        else
        {
            animator.setAnimation(animations.idle());
        }
    }

}
