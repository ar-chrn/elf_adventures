package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.Component;
import archrn.tea_engine.Transform;

/**
 * Follow
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class Follow extends Movement
{

    private Class<? extends Component> targetComponent;
    private Transform target;
    private float radius;
    private boolean moveWithCollider;

    Follow(Transform target, float speed, float radius)
    {
        this(target, speed, radius, true);
    }

    Follow(Class<? extends Component> targetComponent,
           float speed, float radius)
    {
        this(targetComponent, speed, radius, true);
    }

    Follow(Transform target, float speed, float radius,
           boolean moveWithCollider)
    {
        super(speed);
        this.target = target;
        this.radius = radius;
        setMoveWithCollider(moveWithCollider);
    }

    Follow(Class<? extends Component> targetComponent,
           float speed, float radius, boolean moveWithCollider)
    {
        this(targetComponent, speed, radius, moveWithCollider,
             false, false, null);
    }

    Follow(Class<? extends Component> targetComponent,
           float speed, float radius, boolean moveWithCollider,
           boolean flip, boolean updateAnimation,
           Animations.AnimationPack animations)
    {
        super(speed, flip, updateAnimation, animations);
        this.targetComponent = targetComponent;
        this.radius = radius;
        setMoveWithCollider(moveWithCollider);
    }

    @Override
    protected void start()
    {
        super.start();
        if (target == null && targetComponent != null)
        {
            target = getGameObject()
                    .getScene().getAllComponentsOfType(targetComponent)
                    .get(0).getTransform();
        }
    }

    @Override
    protected void update()
    {
        if (target == null || !target.isActive())
        {
            System.out.println("No target");
            return;
        }
        if (getTransform().getPosition().distance(target.getPosition())
                < radius)
        {
            moveTowards(target.getPosition());
        }
    }

}
