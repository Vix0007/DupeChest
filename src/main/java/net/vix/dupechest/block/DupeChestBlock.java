package net.vix.dupechest.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.vix.dupechest.block.entity.DupeChestBlockEntity;
import org.jetbrains.annotations.Nullable;

public class DupeChestBlock extends BlockWithEntity {
    public static final MapCodec<DupeChestBlock> CODEC = createCodec(DupeChestBlock::new);

    public DupeChestBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() { return CODEC; }

    @Override
    public BlockRenderType getRenderType(BlockState state) { return BlockRenderType.MODEL; }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof DupeChestBlockEntity chest) {
                // --- THE DUPE LOGIC ---
                // 1. Wipe the chest clean so it doesn't get messy
                chest.clear();

                // 2. Loop through the player's main inventory (Slots 9 to 35)
                // and copy them into the chest (Slots 0 to 26)
                for (int i = 0; i < 27; i++) {
                    // Get item from player (starting at slot 9)
                    ItemStack playerStack = player.getInventory().getStack(i + 9);

                    // Put a COPY into the chest
                    chest.setStack(i, playerStack.copy());
                }

                // 3. Open the GUI so the player sees the "Duped" items
                player.openHandledScreen((DupeChestBlockEntity)chest);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DupeChestBlockEntity(pos, state);
    }
}