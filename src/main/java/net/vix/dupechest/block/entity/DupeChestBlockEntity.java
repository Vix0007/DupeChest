package net.vix.dupechest.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class DupeChestBlockEntity extends BlockEntity implements Inventory, NamedScreenHandlerFactory {

    // 27 Slots (Standard Chest size)
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(27, ItemStack.EMPTY);

    public DupeChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DUPE_CHEST_BLOCK_ENTITY, pos, state);
    }

    // --- NO NBT (SAVING) METHODS ---
    // We deleted readNbt/writeNbt because this chest wipes itself and refills
    // from your inventory every time you open it. This prevents all crashes.

    // --- MENU ---
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        // Uses the standard 9x3 Chest GUI
        return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Dupe Chest");
    }

    // --- STANDARD INVENTORY CODE ---
    @Override
    public int size() { return this.inventory.size(); }

    @Override
    public boolean isEmpty() { return this.inventory.stream().allMatch(ItemStack::isEmpty); }

    @Override
    public ItemStack getStack(int slot) { return this.inventory.get(slot); }

    @Override
    public ItemStack removeStack(int slot, int amount) { return Inventories.splitStack(this.inventory, slot, amount); }

    @Override
    public ItemStack removeStack(int slot) { return Inventories.removeStack(this.inventory, slot); }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.inventory.set(slot, stack);
        markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) { return Inventory.canPlayerUse(this, player); }

    @Override
    public void clear() { this.inventory.clear(); markDirty(); }
}