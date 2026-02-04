package net.vix.dupechest.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter; // MOVED: server package removed
import net.minecraft.data.recipe.RecipeGenerator; // NEW in 1.21.1
import net.minecraft.data.recipe.ShapelessRecipeJsonBuilder; // MOVED
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.vix.dupechest.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                // Shapeless recipe for the 9 unique end-game items
                createShapeless(RecipeCategory.MISC, ModBlocks.DUPE_CHEST_BLOCK)
                        .input(Items.CREEPER_HEAD)
                        .input(Items.MUSIC_DISC_PIGSTEP)
                        .input(Items.CONDUIT)
                        .input(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE)
                        .input(Items.DRAGON_EGG)
                        .input(Items.DRAGON_HEAD)
                        .input(Items.PIGLIN_HEAD)
                        .input(Items.MACE)
                        .input(Items.ENCHANTED_GOLDEN_APPLE)
                        // Trigger recipe unlock in the green book
                        .criterion(hasItem(Items.DRAGON_EGG), conditionsFromItem(Items.DRAGON_EGG))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "DupeChestRecipes";
    }
}