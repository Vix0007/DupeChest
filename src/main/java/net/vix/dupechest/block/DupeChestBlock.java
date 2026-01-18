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
        // FIX 1: Use 'world.isClient()' (Method) instead of field
        if (!world.isClient()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DupeChestBlockEntity chest) {
                // THE MIRROR LOGIC
                for (int i = 0; i < 27; i++) {
                    // FIX 2: Use 'getStack()' instead of accessing '.main' directly
                    // We offset by 9 to skip the Hotbar (0-8)
                    ItemStack playerStack = player.getInventory().getStack(i + 9);

                    chest.setStack(i, playerStack.copy());
                }
                player.openHandledScreen(chest);
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