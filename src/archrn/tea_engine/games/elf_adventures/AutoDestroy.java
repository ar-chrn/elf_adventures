package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.Component;

import javax.swing.*;

/**
 * AutoDestroy
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class AutoDestroy extends Component
{

    AutoDestroy(int delay)
    {
        Timer timer = new Timer(delay,
                                event -> getGameObject().destroy());
        timer.setRepeats(false);
        timer.start();
    }

}
