package com.rea.creatingspace.items;

import com.rea.creatingspace.ClientAccess;
import com.rea.creatingspace.CreatingSpace;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AdvancedItem extends Item {
    public AdvancedItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND && !level.isClientSide ) {
            player.sendSystemMessage(Component.translatable(CreatingSpace.MODID + "advanced_item.message"));
        }
        return super.use(level, player, hand);

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, level, components, flag);
        //make sure not loaded on the incorect side
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientAccess.advancedItemTooltip(level, components));
    }
    /*
    @Override
    public InteractionResult useOn(UseOnContext p_41427_) {
        return super.useOn(p_41427_);
    }
*/

}
