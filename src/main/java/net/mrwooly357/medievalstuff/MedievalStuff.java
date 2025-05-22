package net.mrwooly357.medievalstuff;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.block.entity.ModBlockEntities;
import net.mrwooly357.medievalstuff.entity.effect.ModStatusEffects;
import net.mrwooly357.medievalstuff.entity.ModEntityTypes;
import net.mrwooly357.medievalstuff.entity.mob.hostile.fallen_knight.FallenKnightEntity;
import net.mrwooly357.medievalstuff.entity.mob.passive.jelly.JellyEntity;
import net.mrwooly357.medievalstuff.item.ModItemGroups;
import net.mrwooly357.medievalstuff.item.ModItems;
import net.mrwooly357.medievalstuff.compound.Compounds;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponClasses;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamilies;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterials;
import net.mrwooly357.medievalstuff.registry.ModRegistries;
import net.mrwooly357.medievalstuff.screen.ModScreenHandlerTypes;
//import net.mrwooly357.medievalstuff.util.measurement_unit.MeasurementUnitTypes;
import net.mrwooly357.medievalstuff.world.gen.ModEntitySpawns;
import net.mrwooly357.medievalstuff.world.gen.ModWorldGeneration;
import net.mrwooly357.medievalstuff.events.HammerAdditionalBlocksBreakEvent;
import net.mrwooly357.medievalstuff.events.TreechopperAdditionalBlocksBreakEvent;
import net.mrwooly357.medievalstuff.world.gen.structure.ModStructureKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MedievalStuff implements ModInitializer {

	public static final String MOD_ID = "medievalstuff";
	public static final Logger LOGGER = LoggerFactory.getLogger("Medieval Stuff");


	@Override
	public void onInitialize() {
		//Items, item groups and related things
		ModItems.init();
		ModItemGroups.registerItemGroups();
		HybridWeaponMaterials.registerHybridWeaponMaterials();
		HybridWeaponFamilies.registerHybridWeaponFamilies();
		HybridWeaponClasses.registerHybridWeaponClasses();

		//Blocks
		ModBlocks.init();

		//Block entities
		ModBlockEntities.registerModBlockEntities();

		//Additional stuff
		registerStrippableBlocks();
		registerFlammableBlocks();

		//Status effects
		ModStatusEffects.init();

		//Screen handlers
		ModScreenHandlerTypes.init();

		//Events
		PlayerBlockBreakEvents.BEFORE.register(new HammerAdditionalBlocksBreakEvent());
		PlayerBlockBreakEvents.BEFORE.register(new TreechopperAdditionalBlocksBreakEvent());

		//Structures
		ModStructureKeys.RegisterModStructureKeys();

		//World generation
		ModWorldGeneration.generateModWorldGeneration();

		//Entities
		FabricDefaultAttributeRegistry.register(ModEntityTypes.JELLY, JellyEntity.createJellyAttributes());
		FabricDefaultAttributeRegistry.register(ModEntityTypes.FALLEN_KNIGHT, FallenKnightEntity.createAttributes());
		ModEntityTypes.init();
		ModEntitySpawns.addSpawns();

		Compounds.registerCompounds();
		//MeasurementUnitTypes.registerMeasurementUnitTypes();
		ModRegistries.registerModRegistries();
    }

	private static void registerStrippableBlocks() {
	}

	private static void registerFlammableBlocks() {
	}
}