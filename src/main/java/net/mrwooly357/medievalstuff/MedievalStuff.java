package net.mrwooly357.medievalstuff;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.block.entity.ModBlockEntities;
import net.mrwooly357.medievalstuff.effect.ModEffects;
import net.mrwooly357.medievalstuff.entity.ModEntities;
import net.mrwooly357.medievalstuff.entity.mob.jelly.JellyEntity;
import net.mrwooly357.medievalstuff.item.ModItemGroups;
import net.mrwooly357.medievalstuff.item.ModItems;
import net.mrwooly357.medievalstuff.screen.ModScreenHandlers;
import net.mrwooly357.medievalstuff.world.gen.ModEntitySpawns;
import net.mrwooly357.medievalstuff.world.gen.ModWorldGeneration;
import net.mrwooly357.medievalstuff.events.HammerAdditionalBlocksBreakEvent;
import net.mrwooly357.medievalstuff.events.TreechopperAdditionalBlocksBreakEvent;
import net.mrwooly357.medievalstuff.world.gen.structure.ModStructureKeys;
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

		//Block entities
		ModBlockEntities.registerBlockEntities();

		//Additional stuff
		registerStrippableBlocks();
		registerFlammableBlocks();

		//Status effects
		ModEffects.registerEffects();

		//Screen handlers
		ModScreenHandlers.registerScreenHandlers();

		//Events
		PlayerBlockBreakEvents.BEFORE.register(new HammerAdditionalBlocksBreakEvent());
		PlayerBlockBreakEvents.BEFORE.register(new TreechopperAdditionalBlocksBreakEvent());

		//Structures
		ModStructureKeys.RegisterModStructureKeys();

		//World generation
		ModWorldGeneration.generateModWorldGeneration();

		//Entities
		FabricDefaultAttributeRegistry.register(ModEntities.JELLY, JellyEntity.createJellyAttributes());

		ModEntities.registerModEntities();
		ModEntitySpawns.addSpawns();
	}

	private static void registerStrippableBlocks() {
	}

	private static void registerFlammableBlocks() {
	}
}