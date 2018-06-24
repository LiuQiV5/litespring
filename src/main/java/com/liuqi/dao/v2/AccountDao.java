package com.liuqi.dao.v2;

public class AccountDao {

    private ItemDao itemDao;

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }
}
