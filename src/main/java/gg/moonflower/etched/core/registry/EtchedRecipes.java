package gg.moonflower.etched.core.registry;

import gg.moonflower.etched.common.recipe.ComplexMusicLabelRecipe;
import gg.moonflower.etched.core.Etched;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class EtchedRecipes {

    public static final RecipeSerializer<ComplexMusicLabelRecipe> COMPLEX_MUSIC_LABEL =
    Registry.register(
        BuiltInRegistries.RECIPE_SERIALIZER,
            Etched.MOD_ID+":complex_music_label",
        new SimpleCraftingRecipeSerializer<>(ComplexMusicLabelRecipe::new)
    );
    //REGISTRY.register("complex_music_label", () -> new SimpleCraftingRecipeSerializer<>(ComplexMusicLabelRecipe::new));
    public static void register() {

    }
}