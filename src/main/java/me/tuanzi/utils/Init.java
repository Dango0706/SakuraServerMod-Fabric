package me.tuanzi.utils;

import me.tuanzi.items.utils.ItemGroup;
import me.tuanzi.utils.registry.BlockRegistry;
import me.tuanzi.utils.registry.EventRegistry;
import me.tuanzi.utils.registry.ItemRegistry;

public class Init {

    public Init() {
        //items
        new ItemRegistry();
        //blocks
        new BlockRegistry();
        //groups
        new ItemGroup();
        //events
        new EventRegistry();


    }
}
