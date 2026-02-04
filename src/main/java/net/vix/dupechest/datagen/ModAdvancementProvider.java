package net.vix.dupechest.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.vix.dupechest.block.ModBlocks; // Ensure this import matches your project structure

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {

    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {

        AdvancementEntry breakingTheMatrix = Advancement.Builder.create()
                .display(
                        ModBlocks.DUPE_CHEST_BLOCK, // Your custom block
                        Text.literal("Breaking the Matrix"),
                        Text.literal("Obtain a Dupe Chest and rewrite the laws of reality."),
                        Identifier.of("minecraft", "textures/gui/advancements/backgrounds/nether_ite_block.png"),
                        AdvancementFrame.CHALLENGE,
                        true, // showToast
                        true, // announceToChat
                        false // hidden
                )
                .criterion("has_dupe_chest", InventoryChangedCriterion.Conditions.items(ModBlocks.DUPE_CHEST_BLOCK))
                .rewards(AdvancementRewards.Builder.experience(500))
                // FIX: Use .build() instead of .save()
                .build(Identifier.of("dupechest", "breaking_the_matrix"));

        // Manually accept the advancement entry
        consumer.accept(breakingTheMatrix);
    }
}