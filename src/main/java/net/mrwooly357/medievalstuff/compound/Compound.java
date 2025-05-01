package net.mrwooly357.medievalstuff.compound;

import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.mrwooly357.medievalstuff.registry.ModRegistries;
import org.jetbrains.annotations.Nullable;

public class Compound {

    @Nullable
    private String translationKey;

    public Compound(Compound.Properties properties) {

    }


    public Text getName() {
        return Text.translatable(getTranslationKey());
    }

    public @Nullable String getTranslationKey() {
        translationKey = Util.createTranslationKey("compound", ModRegistries.COMPOUND.getId(this));

        return translationKey;
    }


    public static class Properties {

        private int weight;
        @Nullable
        private Type type;


        public Properties weight(int value) {
            weight = value;

            return this;
        }

        public Properties type(Type type) {
            this.type = type;

            return this;
        }
    }

    public enum Type {
        COAL,
        METAL
    }
}
