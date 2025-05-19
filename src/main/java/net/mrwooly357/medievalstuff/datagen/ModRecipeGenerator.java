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

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.WEIGHTLESS_DAGGER_TIER_1, 1)
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
                .input('W', ModItems.WEIGHTLESS_DAGGER_TIER_1)
                .criterion(hasItem(ModItems.WEIGHTLESS_DAGGER_TIER_1), conditionsFromItem(ModItems.WEIGHTLESS_DAGGER_TIER_1))
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
                .pattern("WSG")
                .input('S', Items.SUGAR)
                .input('W', Items.WATER_BUCKET)
                .input('G', Items.GLOWSTONE_DUST)
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


        //Building blocks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COPPERSTONE_BRICKS, 2)
                .input('A', Items.COPPER_INGOT)
                .input('B', Blocks.STONE_BRICKS)
                .pattern("AB ")
                .pattern("BA ")
                .pattern("   ")
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);


        //Functional blocks
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.COPPERSTONE_HEATER, 1)
                .input('A', Items.COPPER_INGOT)
                .input('B', ModBlocks.COPPERSTONE_BRICKS)
                .input('C', Blocks.CAMPFIRE)
                .input('D', ModTags.Items.HEATER_CRAFTING_RECIPE_FUEL)
                .pattern("AAA")
                .pattern("BCB")
                .pattern("BDB")
                .criterion(hasItem(ModBlocks.COPPERSTONE_BRICKS), conditionsFromItem(ModBlocks.COPPERSTONE_BRICKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.COPPER_TANK, 1)
                .input('A', Blocks.GLASS)
                .input('B', Items.COPPER_INGOT)
                .pattern("ABA")
                .pattern("A A")
                .pattern("ABA")
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);
    }
}
