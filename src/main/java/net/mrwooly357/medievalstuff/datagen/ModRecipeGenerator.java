package net.mrwooly357.medievalstuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.item.ModItems;
import net.mrwooly357.medievalstuff.util.ModTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> SILVER_SMELTABLES = List.of(ModItems.RAW_SILVER, ModBlocks.SILVER_ORE, ModBlocks.DEEPSLATE_SILVER_ORE);

        offerSmelting(exporter, SILVER_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT, 0.5f, 200, "medievalstuff");
        offerBlasting(exporter, SILVER_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT, 0.5f, 100, "medievalstuff");


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SILVER_INGOT)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .input('N', ModItems.SILVER_NUGGET)
                .criterion(hasItem(ModItems.SILVER_NUGGET), conditionsFromItem(ModItems.SILVER_NUGGET))
                .offerTo(exporter, Identifier.of(MedievalStuff.MOD_ID, "silver_ingot_from_silver_nuggets"));


        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SILVER_NUGGET, 9)
                .input(ModItems.SILVER_INGOT)
                .criterion(hasItem(ModItems.SILVER_INGOT), conditionsFromItem(ModItems.SILVER_INGOT))
                .offerTo(exporter);



        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.RAW_SILVER, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_SILVER_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.SILVER_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SILVER_BLOCK);



        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SILVER_SWORD, 1)
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .input('I', ModItems.SILVER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.SILVER_INGOT), conditionsFromItem(ModItems.SILVER_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SILVER_DAGGER, 1)
                .pattern(" N ")
                .pattern(" I ")
                .pattern(" S ")
                .input('N', ModItems.SILVER_INGOT)
                .input('I', ModItems.SILVER_NUGGET)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.SILVER_INGOT), conditionsFromItem(ModItems.SILVER_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.WEIGHTLESS_DAGGER, 1)
                .pattern("PPP")
                .pattern("JJP")
                .pattern("SJJ")
                .input('P', ModItems.PIECE_OF_JELLY)
                .input('J', ModItems.JAR_OF_JELLY)
                .input('S', ModItems.SILVER_DAGGER)
                .criterion(hasItem(ModItems.SILVER_DAGGER), conditionsFromItem(ModItems.SILVER_DAGGER))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.WEIGHTLESS_DAGGER_TIER_2, 1)
                .pattern("PP ")
                .pattern("JPP")
                .pattern("WJJ")
                .input('P', ModItems.PIECE_OF_JELLY)
                .input('J', ModItems.JAR_OF_JELLY)
                .input('W', ModItems.WEIGHTLESS_DAGGER)
                .criterion(hasItem(ModItems.WEIGHTLESS_DAGGER), conditionsFromItem(ModItems.WEIGHTLESS_DAGGER))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.SILVER_PICKAXE, 1)
                .pattern("III")
                .pattern(" S ")
                .pattern(" S ")
                .input('I', ModItems.SILVER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.SILVER_INGOT), conditionsFromItem(ModItems.SILVER_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.SILVER_AXE, 1)
                .pattern("II ")
                .pattern("IS ")
                .pattern(" S ")
                .input('I', ModItems.SILVER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.SILVER_INGOT), conditionsFromItem(ModItems.SILVER_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.SILVER_SHOVEL, 1)
                .pattern(" I ")
                .pattern(" S ")
                .pattern(" S ")
                .input('I', ModItems.SILVER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.SILVER_INGOT), conditionsFromItem(ModItems.SILVER_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.SILVER_HOE, 1)
                .pattern("II ")
                .pattern(" S ")
                .pattern(" S ")
                .input('I', ModItems.SILVER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.SILVER_INGOT), conditionsFromItem(ModItems.SILVER_INGOT))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SILVER_HELMET, 1)
                .pattern("   ")
                .pattern("III")
                .pattern("I I")
                .input('I', ModItems.SILVER_INGOT)
                .criterion(hasItem(ModItems.SILVER_INGOT), conditionsFromItem(ModItems.SILVER_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SILVER_CHESTPLATE, 1)
                .pattern("I I")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.SILVER_INGOT)
                .criterion(hasItem(ModItems.SILVER_INGOT), conditionsFromItem(ModItems.SILVER_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SILVER_LEGGINGS, 1)
                .pattern("III")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.SILVER_INGOT)
                .criterion(hasItem(ModItems.SILVER_INGOT), conditionsFromItem(ModItems.SILVER_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SILVER_BOOTS, 1)
                .pattern("   ")
                .pattern("I I")
                .pattern("I I")
                .input('I', ModItems.SILVER_INGOT)
                .criterion(hasItem(ModItems.SILVER_INGOT), conditionsFromItem(ModItems.SILVER_INGOT))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JAR, 1)
                .pattern("PPP")
                .pattern("G G")
                .pattern("GGG")
                .input('P', ItemTags.PLANKS)
                .input('G', Blocks.GLASS_PANE)
                .criterion(hasItem(Blocks.GLASS_PANE), conditionsFromItem(Blocks.GLASS_PANE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PIECE_OF_JELLY, 4)
                .pattern("   ")
                .pattern("SGW")
                .pattern("LSG")
                .input('S', Items.SUGAR)
                .input('W', Items.WATER_BUCKET)
                .input('G', Items.GLOWSTONE_DUST)
                .input('L', ModBlocks.LUMISHROOM)
                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.JAR_OF_JELLY, 1)
                .pattern("PPP")
                .pattern("PPP")
                .pattern("JPP")
                .input('P', ModItems.PIECE_OF_JELLY)
                .input('J', ModItems.JAR)
                .criterion(hasItem(ModItems.JAR), conditionsFromItem(ModItems.JAR))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_STAIRS, 4)
                .pattern("G  ")
                .pattern("GG ")
                .pattern("GGG")
                .input('G', ModBlocks.GLOOMY_STONE)
                .criterion(hasItem(ModBlocks.GLOOMY_STONE), conditionsFromItem(ModBlocks.GLOOMY_STONE))
                .offerTo(exporter);

         offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_SLAB, ModBlocks.GLOOMY_STONE);

         offerPressurePlateRecipe(exporter, ModBlocks.GLOOMY_STONE_PRESSURE_PLATE, ModBlocks.GLOOMY_STONE);

         ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.GLOOMY_STONE_BUTTON, 1)
                 .input(ModBlocks.GLOOMY_STONE)
                 .criterion(hasItem(ModBlocks.GLOOMY_STONE), conditionsFromItem(ModBlocks.GLOOMY_STONE))
                 .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_BRICKS, 4)
                .pattern("   ")
                .pattern("GG ")
                .pattern("GG ")
                .input('G', ModBlocks.GLOOMY_STONE)
                .criterion(hasItem(ModBlocks.GLOOMY_STONE), conditionsFromItem(ModBlocks.GLOOMY_STONE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_BRICK_STAIRS, 4)
                .pattern("G  ")
                .pattern("GG ")
                .pattern("GGG")
                .input('G', ModBlocks.GLOOMY_STONE_BRICKS)
                .criterion(hasItem(ModBlocks.GLOOMY_STONE_BRICKS), conditionsFromItem(ModBlocks.GLOOMY_STONE_BRICKS))
                .offerTo(exporter);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_BRICK_SLAB, ModBlocks.GLOOMY_STONE_BRICKS);

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_BRICK_WALL, ModBlocks.GLOOMY_STONE_BRICKS);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_STAIRS, ModBlocks.GLOOMY_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_SLAB, ModBlocks.GLOOMY_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_PRESSURE_PLATE, ModBlocks.GLOOMY_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_BUTTON, ModBlocks.GLOOMY_STONE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_BRICKS, ModBlocks.GLOOMY_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_BRICK_STAIRS, ModBlocks.GLOOMY_STONE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_BRICK_SLAB, ModBlocks.GLOOMY_STONE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOMY_STONE_BRICK_WALL, ModBlocks.GLOOMY_STONE_BRICKS);


        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUMISHROOM_PLANKS, 4)
                .input(ModBlocks.LUMISHROOM_LOG)
                .criterion(hasItem(ModBlocks.LUMISHROOM_LOG), conditionsFromItem(ModBlocks.LUMISHROOM_LOG))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUMISHROOM_PLANKS, 4)
                .input(ModBlocks.STRIPPED_LUMISHROOM_LOG)
                .criterion(hasItem(ModBlocks.STRIPPED_LUMISHROOM_LOG), conditionsFromItem(ModBlocks.STRIPPED_LUMISHROOM_LOG))
                .offerTo(exporter, Identifier.of(MedievalStuff.MOD_ID, "lumishroom_planks_from_stripped_lumishroom_log"));

        //Copperstone
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COPPERSTONE_BRICKS, 2)
                .input('A', Items.COPPER_INGOT)
                .input('B', Blocks.STONE_BRICKS)
                .pattern("AB ")
                .pattern("BA ")
                .pattern("   ")
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.COPPERSTONE_HEATER, 1)
                .input('A', Items.COPPER_INGOT)
                .input('B', ModBlocks.COPPERSTONE_BRICKS)
                .input('C', Blocks.CAMPFIRE)
                .input('D', ModTags.Items.HEATERS_CRAFTING_RECIPES_FUEL)
                .pattern("AAA")
                .pattern("BCB")
                .pattern("BDB")
                .criterion(hasItem(ModBlocks.COPPERSTONE_BRICKS), conditionsFromItem(ModBlocks.COPPERSTONE_BRICKS))
                .offerTo(exporter);
    }
}
