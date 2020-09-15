package ManagerClient;

import Shared.RemoteServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ManagerClient implements Runnable {
    private RemoteServer server;



    public ManagerClient() throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry("Localhost", 1099);
        server = (RemoteServer) reg.lookup("Server");
        System.out.println("connected to Server");
    }


    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);

        while(true)
        {
           String a;
           if((a = scan.nextLine()) != null)
            {
                if(a.equals("stop"))
                {
                    try {
                        server.stopWorking();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public static void main(String[] args)
    {
        try
        {
            ManagerClient manager = new ManagerClient();
            Thread managerThread = new Thread(manager);
            managerThread.start();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        } catch (NotBoundException e)
        {
            e.printStackTrace();
        }
    }
}
