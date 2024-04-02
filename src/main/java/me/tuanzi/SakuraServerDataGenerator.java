package me.tuanzi;

import me.tuanzi.dataGen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SakuraServerDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(BlockTagGen::new);
		pack.addProvider(RecipeGen::new);
		pack.addProvider(ModelGen::new);
		pack.addProvider(BlockDropLootGen::new);
		pack.addProvider(EnglishLangGen::new);
		pack.addProvider(ChineseLangGen::new);


	}
}
