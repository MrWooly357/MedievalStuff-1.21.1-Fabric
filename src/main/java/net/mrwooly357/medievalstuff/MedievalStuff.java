package net.mrwooly357.medievalstuff;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.components.ModDataComponentTypes;
import net.mrwooly357.medievalstuff.effect.ModEffects;
import net.mrwooly357.medievalstuff.entity.ModEntities;
import net.mrwooly357.medievalstuff.entity.custom.JellyEntity;
import net.mrwooly357.medievalstuff.item.ModItemGroups;
import net.mrwooly357.medievalstuff.item.ModItems;
import net.mrwooly357.medievalstuff.world.gen.ModEntitySpawns;
import net.mrwooly357.medievalstuff.world.gen.ModWorldGeneration;
import net.mrwooly357.wool_lib.events.HammerAdditionalBlocksBreakEvent;
import net.mrwooly357.wool_lib.events.TreechopperAdditionalBlocksBreakEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MedievalStuff implements ModInitializer {
	public static final String MOD_ID = "medievalstuff";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		//Items and item groups
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();

		//Blocks
		ModBlocks.registerModBlocks();

		//Additional stuff
		registerStrippableBlocks();
		registerFlammableBlocks();

		//Status effects
		ModEffects.registerEffects();

		//Data component types
		ModDataComponentTypes.registerDataComponentTypes();

		//Events
		PlayerBlockBreakEvents.BEFORE.register(new HammerAdditionalBlocksBreakEvent());
		PlayerBlockBreakEvents.BEFORE.register(new TreechopperAdditionalBlocksBreakEvent());

		//World generation
		ModWorldGeneration.generateModWorldGeneration();

		//Entities
		FabricDefaultAttributeRegistry.register(ModEntities.JELLY, JellyEntity.createJellyAttributes());

		ModEntities.registerModEntities();
		ModEntitySpawns.addSpawns();
	}

	private static void registerStrippableBlocks() {
		StrippableBlockRegistry.register(ModBlocks.LUMISHROOM_LOG, ModBlocks.STRIPPED_LUMISHROOM_LOG);
		StrippableBlockRegistry.register(ModBlocks.LUMISHROOM_WOOD, ModBlocks.STRIPPED_LUMISHROOM_WOOD);
	}

	private static void registerFlammableBlocks() {
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.LUMISHROOM_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_LUMISHROOM_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.LUMISHROOM_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_LUMISHROOM_WOOD, 5, 5);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.LUMISHROOM_PLANKS, 10, 7);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.LUMISHROOM_CAP, 15, 10);
	}
}