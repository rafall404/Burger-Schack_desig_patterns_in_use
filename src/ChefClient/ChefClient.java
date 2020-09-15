package ChefClient;

import ChefClient.Domain.Recipe;
import ChefClient.Proxy.Proxy;
import ChefClient.Proxy.RecipeProvider;
import ChefClient.Proxy.RecipeReader;
import Shared.Chef;
import Shared.RemoteServer;
import Shared.Burger;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class ChefClient implements Chef,  Runnable
{
    private boolean Working = true;
    private RemoteServer server;
    private RecipeProvider recipe;

    public ChefClient(RecipeProvider rec) throws Exception {
        UnicastRemoteObject.exportObject(this, 0);

        recipe= rec;
        makeSomeRecipes();

        Registry reg = LocateRegistry.getRegistry("Localhost", 1099);
        server= (RemoteServer) reg.lookup("Server");
        System.out.println("connected to Server");
        server.addClient(this);
    }

    @Override
    public void stopWorking() throws RemoteException {
        Working =false;
    }


    public void makeSomeRecipes() throws Exception {
        String[] ingre= {"a","b","c"};
        Recipe a= new Recipe("1","CheeseBurger",ingre);
        recipe.updateRecipe(a);
        System.out.println("Added recipe to RecipeList");
        Recipe b= new Recipe("2","HamBurger",ingre);
        recipe.updateRecipe(b);
        System.out.println("Added recipe to RecipeList");
        Recipe c= new Recipe("3","BeefBurger",ingre);
        recipe.updateRecipe(c);
        System.out.println("Added recipe to RecipeList");
    }

    public synchronized void makeBurger() throws Exception {
            Random rand = new Random();
            int ran = rand.nextInt((3 - 1) + 1) + 1;
            String randomRecipe = Integer.toString(ran);
            Recipe rec = recipe.getRecipeById(randomRecipe);
            Burger burger = rec.createBurger();

            server.putBurger(burger);
            System.out.println("Added a new"+ burger.getName());
    }

    @Override
    public void run() {
        while(Working) {
            try {
                makeBurger();
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("ends work chef");
        System.exit(0);
    }

    public static void main(String[] args) {

        RecipeProvider recipeReader = new RecipeReader("recipes.txt");
        RecipeProvider proxy = new Proxy(recipeReader);

        try {
            Chef chef = new ChefClient(proxy);
            Thread chefThread = new Thread((Runnable) chef);
            chefThread.start();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
