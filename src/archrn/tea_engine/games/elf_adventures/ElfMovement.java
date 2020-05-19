package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.input.InputAxis;

/**
 * ElfMovement
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class ElfMovement extends Movement
{

    ElfMovement()
    {
        super(7.5f, true, true, Animations.elf());
    }

    @Override
    protected void update()
    {
        move(InputAxis.getDirection());
    }

}
