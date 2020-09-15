package Server.Adapter;

import Shared.Burger;

public interface IBlockingQueue {
    Burger removeBurger();
    void addBurger(Burger burger);
}
