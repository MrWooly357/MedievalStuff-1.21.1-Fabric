package net.mrwooly357.medievalstuff.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class PieceOfJellyItem extends Item {
    public PieceOfJellyItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.medievalstuff.piece_of_jelly.tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
