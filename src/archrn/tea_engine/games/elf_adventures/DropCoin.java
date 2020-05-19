package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.Component;
import archrn.tea_engine.random.Randoms;

/**
 * DropCoin
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class DropCoin extends Component
{

    private float chance;

    DropCoin(float chance)
    {
        this.chance = chance;
    }

    @Override
    protected void onDestroy()
    {

        if (Randoms.chance(chance))
        {
            getGameObject().getScene()
                           .addGameObject(new Coin())
                           .getTransform()
                           .setPosition(getTransform().getPosition());
        }
    }

}
