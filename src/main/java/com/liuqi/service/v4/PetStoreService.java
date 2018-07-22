package com.liuqi.service.v4;

import com.liuqi.beans.factory.annotation.Autowired;
import com.liuqi.dao.v4.AccountDao;
import com.liuqi.dao.v4.ItemDao;
import com.liuqi.stereotype.Component;

@Component(value="petStore")
public class PetStoreService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }
}
