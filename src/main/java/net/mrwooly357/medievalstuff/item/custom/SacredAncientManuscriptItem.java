package net.mrwooly357.medievalstuff.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class SacredAncientManuscriptItem extends Item {
    public SacredAncientManuscriptItem(Settings settings) {
        super(settings);
    }


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        tooltip.add(Text.translatable("tooltip.medievalstuff.sacred_ancient_manuscript_item_1.tooltip"));
        if (Screen.hasControlDown()) {
            tooltip.add(Text.translatable("tooltip.medievalstuff.sacred_ancient_manuscript_item_2.tooltip"));
            tooltip.add(Text.translatable("tooltip.medievalstuff.sacred_ancient_manuscript_item_3.tooltip"));
        } else {
            tooltip.add(Text.translatable("tooltip.medievalstuff.sacred_ancient_manuscript_item_4.tooltip"));
        }
    }
}
