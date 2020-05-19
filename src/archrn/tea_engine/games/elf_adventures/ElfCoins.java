package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.Component;
import archrn.tea_engine.GameObject;
import archrn.tea_engine.physics.Collider;

/**
 * ElfCoins
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class ElfCoins extends Component
{

    private int coins;
    private CoinDisplay coinDisplay;

    @Override
    protected void start()
    {
        coins = 0;
    }

    @Override
    protected void onCollisionEnter(Collider other)
    {
        GameObject otherGO = other.getGameObject();
        if (otherGO instanceof Coin)
        {
            coins += 1;
            updateCoinDisplay();
            otherGO.destroy();
            otherGO.setActive(false);
        }
    }

    public void addCoinDisplay(CoinDisplay coinDisplay)
    {
        this.coinDisplay = coinDisplay;
        updateCoinDisplay();
    }

    private void updateCoinDisplay()
    {
        if (coinDisplay != null)
        {
            coinDisplay.getRenderer().setText("" + coins);
        }
    }

}
