package net.mrwooly357.medievalstuff.config;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

public class ModConfigValues {



    public static class Items {

        public static class Weapons {

            public static class Ranged {

                public static class Bows {

                    public static class Twobow {
                        public static float PROJECTILE_SPEED_MULTIPLIER = 3.5F;
                        public static float MIN_PROJECTILE_DIVERGENCE = 0.75F;
                        public static float MAX_PROJECTILE_DIVERGENCE = 1.0F;
                        public static float PROJECTILE_DIVERGENCE = MathHelper.nextFloat(Random.create(), MIN_PROJECTILE_DIVERGENCE, MAX_PROJECTILE_DIVERGENCE);
                        public static float ADDITIONAL_PROJECTILE_SPEED_MULTIPLIER = 3.5F;
                        public static float MIN_ADDITIONAL_PROJECTILE_DIVERGENCE = 0.5F;
                        public static float MAX_ADDITIONAL_PROJECTILE_DIVERGENCE = 0.75F;
                        public static float ADDITIONAL_PROJECTILE_DIVERGENCE = MathHelper.nextFloat(Random.create(), MIN_ADDITIONAL_PROJECTILE_DIVERGENCE, MAX_ADDITIONAL_PROJECTILE_DIVERGENCE);
                        public static float MIN_ADDITIONAL_PROJECTILE_ADDITIONAL_PITCH = 2.0F;
                        public static float MAX_ADDITIONAL_PROJECTILE_ADDITIONAL_PITCH = 2.5F;
                        public static float ADDITIONAL_PROJECTILE_ADDITIONAL_PITCH = MathHelper.nextFloat(Random.create(), MIN_ADDITIONAL_PROJECTILE_ADDITIONAL_PITCH, MAX_ADDITIONAL_PROJECTILE_ADDITIONAL_PITCH);
                        public static SoundEvent USE_SOUND = SoundEvents.ENTITY_ARROW_SHOOT;
                        public static float MIN_USE_SOUND_VOLUME = 0.8F;
                        public static float MAX_USE_SOUND_VOLUME = 1.2F;
                        public static float TWOBOW_USE_SOUND_VOLUME = MathHelper.nextFloat(Random.create(), MIN_USE_SOUND_VOLUME, MAX_USE_SOUND_VOLUME);
                        public static float MIN_USE_SOUND_PITCH = 0.8F;
                        public static float MAX_USE_SOUND_PITCH = 1.2F;
                        public static float USE_SOUND_PITCH = MathHelper.nextFloat(Random.create(), MIN_USE_SOUND_PITCH, MAX_USE_SOUND_PITCH);
                        public static UseAction USE_ANIMATION = UseAction.BOW;
                        public static int MAX_USE_TIME = 72000;
                        public static int TICKS_PER_SECOND = 20;
                        public static int RANGE = 15;
                    }

                    public static class AdvancedBows {

                        public static class ShortBows {

                        }

                        public static class LongBows {

                        }

                        public static class AdvancedBow {
                            public static float MIN_PULL_REQUIREMENT = 0.1F;
                            public static float BASE_DIVERGENCE = 3.0F;
                            public static SoundEvent USE_SOUND = SoundEvents.ENTITY_ARROW_SHOOT;
                            public static SoundCategory USE_SOUND_CATEGORY = SoundCategory.PLAYERS;
                            public static float MIN_USE_SOUND_VOLUME = 0.75F;
                            public static float MAX_USE_SOUND_VOLUME = 1.25F;
                            public static float USE_SOUND_VOLUME = MathHelper.nextFloat(Random.create(), MIN_USE_SOUND_VOLUME, MAX_USE_SOUND_VOLUME);
                            public static float MIN_USE_SOUND_PITCH = 0.75F;
                            public static float MAX_USE_SOUND_PITCH = 1.25F;
                            public static float USE_SOUND_PITCH = MathHelper.nextFloat(Random.create(), MIN_USE_SOUND_PITCH, MAX_USE_SOUND_PITCH);
                        }
                    }
                }
            }
        }
    }
}
