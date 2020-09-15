package ChefClient.Proxy;

import ChefClient.Domain.Recipe;

import java.io.Serializable;

public class Proxy implements RecipeProvider {

    private RecipeProvider recipeReader;
    private Recipe currentRecipe;

    public Proxy(RecipeProvider recipeReader)
    {
        this.recipeReader= recipeReader;
    }

    @Override
    public Recipe getRecipeById(String id) throws Exception {
        if(recipeReader!=null) {
            if(currentRecipe!=null) {
                if (id == currentRecipe.getId()) {
                    return currentRecipe;
                }
            }
            currentRecipe = recipeReader.getRecipeById(id);
            return recipeReader.getRecipeById(id);
        }
        else
            System.out.println("Unavailable content");
        return null;
    }

    @Override
    public Recipe getRecipeByName(String name) throws Exception {
        if(recipeReader!=null) {
            if(currentRecipe!=null) {
                if (name == currentRecipe.getName()) {
                    return currentRecipe;
                }
            }
            currentRecipe = recipeReader.getRecipeByName(name);
            return recipeReader.getRecipeByName(name);
        }
        else
            System.out.println("Unavailable content");
        return null;
    }

    @Override
    public void updateRecipe(Recipe recipe) throws Exception {
            recipeReader.updateRecipe(recipe);
    }
}
