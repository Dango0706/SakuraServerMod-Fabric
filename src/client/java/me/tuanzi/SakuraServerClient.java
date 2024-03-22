package me.tuanzi;

import me.tuanzi.utils.ClientEventRegister;
import net.fabricmc.api.ClientModInitializer;

public class SakuraServerClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		new ClientEventRegister();

	}
}