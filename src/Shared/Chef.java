package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Chef extends Remote {
    void  stopWorking() throws RemoteException;

}
