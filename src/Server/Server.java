package Server;

import Shared.Chef;
import Shared.Customer;
import Server.Adapter.ArrayList;
import Server.Adapter.BlockingQueue;
import Server.Adapter.IBlockingQueue;
import Shared.Burger;
import Shared.RemoteServer;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



public class Server implements RemoteServer {
    private IBlockingQueue blockingQueue;

    private ArrayList<Remote> remoteArrayList = new ArrayList<Remote>();
    private ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
    private ArrayList<Chef> chefArrayList = new ArrayList<Chef>();


    public Server() {
        blockingQueue = new BlockingQueue();

        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void putBurger(Burger burger) throws RemoteException {
        blockingQueue.addBurger(burger);
        System.out.println("burger added to the list");
    }

    @Override
    public Burger getBurger() throws RemoteException {
        return blockingQueue.removeBurger();
    }

    @Override
    public void stopWorking() throws RemoteException {
        System.out.println("Everyone stops working");
        stopChef();
        stopCustomer();
    }

    public void stopChef() {
        for(int i= 0;i< chefArrayList.size();i++) {
            try {
                chefArrayList.get(i).stopWorking();
            } catch (RemoteException e) {
                e.getMessage();
            }
        }
    }

    public void stopCustomer() {
        for(int i= 0;i< customerArrayList.size();i++) {
            try {
                customerArrayList.get(i).stopWorking();
            }
            catch (RemoteException e) {
                e.getMessage();
            }
        }
    }

    @Override
    public void addClient(Remote remote) throws RemoteException {
        remoteArrayList.add(remote);
        System.out.println("costumer added");

        if(remote instanceof Customer) {
            customerArrayList.add((Customer) remote);
        }
        else if(remote instanceof Chef) {
            chefArrayList.add((Chef) remote);
        }
    }


    public static void main(String[] args) {
        Registry registry;
        try {
            registry = LocateRegistry.createRegistry(1099);
            RemoteServer server = new Server();
            registry.bind("Server",server);
        }
        catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
