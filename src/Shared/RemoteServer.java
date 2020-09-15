package Shared;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServer extends Remote
{
    void putBurger(Burger burger) throws RemoteException;
    Burger getBurger() throws RemoteException;
    void stopWorking() throws RemoteException;
    void addClient(Remote remote) throws RemoteException;


}
