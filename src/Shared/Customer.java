package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Customer extends Remote {
    void  stopWorking() throws RemoteException;
}
