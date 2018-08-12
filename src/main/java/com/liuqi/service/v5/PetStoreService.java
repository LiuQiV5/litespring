package com.liuqi.service.v5;

import com.liuqi.beans.factory.annotation.Autowired;
import com.liuqi.dao.v5.AccountDao;
import com.liuqi.dao.v5.ItemDao;
import com.liuqi.stereotype.Component;
import com.liuqi.util.MessageTracker;

@Component(value="petStore")
public class PetStoreService {
    @Autowired
    AccountDao accountDao;
    @Autowired
    ItemDao itemDao;

    public PetStoreService() {

    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void placeOrder(){
        System.out.println("place order");
        MessageTracker.addMsg("place order");
    }

    public void placeOrderWithException(){
        throw new NullPointerException();
    }
}
