package me.tuanzi.draw;

import me.tuanzi.draw.events.DropDiamond;

import java.util.HashSet;
import java.util.Set;

public class DrawPool {

    private static final Set<DrawEvent> UP_Pool = new HashSet<>();
    private static final Set<DrawEvent> MISC_Pool = new HashSet<>();
    private static final Set<DrawEvent> PERMANENT_Pool = new HashSet<>();

    static {
        //up
        addUpPool(new DropDiamond());
        //misc

        //permanent
    }


    public static void addUpPool(DrawEvent event) {
        UP_Pool.add(event);
    }

    public static void addMiscPool(DrawEvent event) {
        MISC_Pool.add(event);
    }

    public static void addPermanentPool(DrawEvent event) {
        PERMANENT_Pool.add(event);
    }

    public final Set<DrawEvent> getUPPool() {
        return UP_Pool;
    }

    public final Set<DrawEvent> getMiscPool() {
        return MISC_Pool;
    }

    public final Set<DrawEvent> getPermanentPool() {
        return PERMANENT_Pool;
    }


}
