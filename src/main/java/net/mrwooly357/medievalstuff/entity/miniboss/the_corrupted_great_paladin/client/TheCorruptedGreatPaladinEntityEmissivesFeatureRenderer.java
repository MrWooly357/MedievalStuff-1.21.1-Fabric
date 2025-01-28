package net.mrwooly357.medievalstuff.entity.miniboss.the_corrupted_great_paladin.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.entity.miniboss.the_corrupted_great_paladin.TheCorruptedGreatPaladinEntity;

public class TheCorruptedGreatPaladinEntityEmissivesFeatureRenderer<T extends TheCorruptedGreatPaladinEntity>
        extends EyesFeatureRenderer<T, TheCorruptedGreatPaladinEntityModel<T>> {

    private static final RenderLayer EMISSIVES = RenderLayer.getEyes(Identifier.of(
            MedievalStuff.MOD_ID, "textures/entity/the_corrupted_great_paladin/the_corrupted_great_paladin_emissives.png"));

    public TheCorruptedGreatPaladinEntityEmissivesFeatureRenderer(FeatureRendererContext<T, TheCorruptedGreatPaladinEntityModel<T>> featureRendererContext) {
        super(featureRendererContext);
    }


    @Override
    public RenderLayer getEyesTexture() {
        return EMISSIVES;
    }
}
