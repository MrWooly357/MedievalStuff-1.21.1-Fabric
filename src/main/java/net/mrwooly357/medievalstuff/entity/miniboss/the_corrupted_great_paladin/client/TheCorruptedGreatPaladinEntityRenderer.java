package net.mrwooly357.medievalstuff.entity.miniboss.the_corrupted_great_paladin.client;

import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.entity.miniboss.the_corrupted_great_paladin.TheCorruptedGreatPaladinEntity;

public class TheCorruptedGreatPaladinEntityRenderer extends
        BipedEntityRenderer<TheCorruptedGreatPaladinEntity, TheCorruptedGreatPaladinEntityModel<TheCorruptedGreatPaladinEntity>> {

    public TheCorruptedGreatPaladinEntityRenderer(
            EntityRendererFactory.Context ctx, EntityModelLayer mainLayer, EntityModelLayer innerArmorLayer, EntityModelLayer outerArmorLayer
    ) {
        super(ctx, getTheCorruptedGreatPaladinEntityModel(ctx.getModelLoader(), mainLayer), 0.5F, 1.0019531F, 1.0F, 1.0019531F);

        this.addFeature(new TheCorruptedGreatPaladinEntityEmissivesFeatureRenderer<>(this));
        this.addFeature(
                new ArmorFeatureRenderer<>(
                        this, new ArmorEntityModel<>(ctx.getPart(innerArmorLayer)), new ArmorEntityModel<>(ctx.getPart(outerArmorLayer)), ctx.getModelManager()
                )
        );
        this.addFeature(new HeldItemFeatureRenderer<>(this, ctx.getHeldItemRenderer()));
    }

    @Override
    public Identifier getTexture(TheCorruptedGreatPaladinEntity entity) {
        return Identifier.of(MedievalStuff.MOD_ID, "textures/entity/the_corrupted_great_paladin/the_corrupted_great_paladin.png");
    }

    private static TheCorruptedGreatPaladinEntityModel<TheCorruptedGreatPaladinEntity> getTheCorruptedGreatPaladinEntityModel(
            EntityModelLoader modelLoader,
            EntityModelLayer layer) {
        return new TheCorruptedGreatPaladinEntityModel<>(modelLoader.getModelPart(layer));
    }
}
