package Server.Adapter;

import Shared.Burger;

public class BlockingQueue implements IBlockingQueue {

    private int cap = 100;
    private ListADT<Burger> arrayList;

    public BlockingQueue()
    {
        arrayList = new ArrayList<Burger>();
    }

    @Override
    public synchronized Burger removeBurger() {
       while (arrayList.size()<=0) {
           try{
               System.out.println("Customers are waiting");
               wait();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
       notifyAll();
       System.out.println("Burger has been taken");
       return arrayList.remove(0);
    }

    @Override
    public synchronized void addBurger(Burger burger) {
        while(arrayList.size()>=cap) {
            System.out.println("Chef is waiting for customers to eat burgers");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        arrayList.add(burger);
    }
}
