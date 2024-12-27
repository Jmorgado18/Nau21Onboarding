package com.nau21.siscm.onboarding.Service;

import com.nau21.siscm.onboarding.DAOS.ItemsDAO;
import com.nau21.siscm.onboarding.Model.ItemOptions;
import com.nau21.siscm.onboarding.Model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {

    private final ItemsDAO itemsDAO;

    @Autowired
    public ItemsServiceImpl(ItemsDAO itemsDAO) {
        this.itemsDAO = itemsDAO;
    }

    @Override
    public void createItem(Items item) {
      //  System.out.println("item no Service: " + item);
        itemsDAO.createItem(item);
    }

    @Override
    public void updateItem(Items item) {
        itemsDAO.updateItem(item);
    }

    @Override
    public void deleteItem(int id) {
        itemsDAO.deleteItem(id);
    }

    @Override
    public Items getItemById(int id) {
        return itemsDAO.getItemById(id);
    }

    @Override
    public List<Items> getAllItems() {
        return itemsDAO.getAllItems();
    }
    @Override
    public void createItemOption(ItemOptions itemOption) {
        itemsDAO.saveItemOption(itemOption);
    }

}
