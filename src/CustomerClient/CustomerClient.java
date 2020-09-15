package CustomerClient;

import Shared.Customer;
import Shared.RemoteServer;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class CustomerClient implements Customer, Runnable
{
    private RemoteServer bar;
    private boolean working;

    public CustomerClient() throws RemoteException, NotBoundException {

        UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        bar = (RemoteServer) registry.lookup("Server");

        System.out.println("Connected");

        working = true;
        bar.addClient(this);
    }

    @Override
    public void stopWorking() {
        working = false;
    }

    @Override
    public void run() {
        Random random = new Random();

        try {
            while (working) {
                int n = random.nextInt(8000-4000)+4000;
                bar.getBurger();
                System.out.println("client is eating burger");
                Thread.sleep(n);
            }
        }catch(RemoteException e){
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            // OK
            System.out.println("Customer interrupted [" + Thread.currentThread().getName() + "]");
            e.printStackTrace();
        }

        System.out.println("customer stops working");
    }

    public static void main(String[] args)
    {
        try {
            Customer customer = new CustomerClient();
            Thread customerThread = new Thread((Runnable) customer);
            customerThread.start();

        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
