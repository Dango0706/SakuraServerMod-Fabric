package me.tuanzi;

import me.tuanzi.dataGenerator.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SakuraServerDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModelGenerator::new);
		pack.addProvider(BlockTagGenerator::new);
		pack.addProvider(BlockDropGenerator::new);
		pack.addProvider(RecipeGenerator::new);
		pack.addProvider(ChineseLangGenerator::new);
	}
}
