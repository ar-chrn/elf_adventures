package archrn.tea_engine.games.elf_adventures;

import archrn.tea_engine.animation.Animation;
import archrn.tea_engine.assets.Sprite;

import java.io.File;

/**
 * Animations
 *
 * @author archrn
 * @version 0
 * @since 0
 */
class Animations
{

    private static final int pixelsPerUnit = 16;
    private static final int framesPerSecond = 12;
    private static final String path =
            "/archrn/tea_engine/games/elf_adventures/"
                    .replace("/", File.separator);
    private static final String format = ".png";

    private static AnimationPack elf;
    private static AnimationPack coin;
    private static AnimationPack tinyDemon;
    private static AnimationPack smallDemon;
    private static AnimationPack normalDemon;
    private static AnimationPack largeDemon;

    public static AnimationPack elf()
    {
        if (elf == null)
        {
            elf = new AnimationPack("elf/elf_f_idle_anim_f", 4,
                                    "elf/elf_f_run_anim_f", 4);
        }
        return elf;
    }

    public static AnimationPack coin()
    {
        if (coin == null)
        {
            coin = new AnimationPack("coin/coin_anim_f", 4,
                                     "", 0);
        }
        return coin;
    }

    public static AnimationPack tinyDemon()
    {
        if (tinyDemon == null)
        {
            tinyDemon = new AnimationPack(
                    "demons/tiny/imp_idle_anim_f", 4,
                    "demons/tiny/imp_run_anim_f", 4);
        }
        return tinyDemon;
    }

    public static AnimationPack smallDemon()
    {
        if (smallDemon == null)
        {
            smallDemon = new AnimationPack(
                    "demons/small/wogol_idle_anim_f", 4,
                    "demons/small/wogol_run_anim_f", 4);
        }
        return smallDemon;
    }

    public static AnimationPack normalDemon()
    {
        if (normalDemon == null)
        {
            normalDemon = new AnimationPack(
                    "demons/normal/chort_idle_anim_f", 4,
                    "demons/normal/chort_run_anim_f", 4);
        }
        return normalDemon;
    }

    public static AnimationPack largeDemon()
    {
        if (largeDemon == null)
        {
            largeDemon = new AnimationPack(
                    "demons/large/big_demon_idle_anim_f", 4,
                    "demons/large/big_demon_run_anim_f", 4);
        }
        return largeDemon;
    }

    static class AnimationPack
    {

        private String idleName;
        private String runName;
        private int idleLength;
        private int runLength;
        private Animation idle;
        private Animation run;

        AnimationPack(String idle, int idleLength, String run, int runLength)
        {
            idleName = idle.replace("/", File.separator);;
            this.idleLength = idleLength;
            runName = run.replace("/", File.separator);;
            this.runLength = runLength;
        }

        Animation idle()
        {
            if (idle == null)
            {
                idle = new Animation();
                for (int i = 0; i < idleLength; ++i)
                {
                    Sprite sprite = new Sprite(path + idleName + i + format);
                    sprite.setPixelsPerUnit(pixelsPerUnit);
                    idle.addSprite(sprite);
                }
                idle.setFramesPerSecond(framesPerSecond);
            }
            return idle;
        }

        Animation run()
        {
            if (run == null)
            {
                run = new Animation();
                for (int i = 0; i < runLength; ++i)
                {
                    Sprite sprite = new Sprite(path + runName + i + format);
                    sprite.setPixelsPerUnit(pixelsPerUnit);
                    run.addSprite(sprite);
                }
                run.setFramesPerSecond(framesPerSecond);
            }
            return run;
        }

    }

}
