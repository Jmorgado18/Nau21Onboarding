package com.nau21.siscm.onboarding.DAOS;

import com.nau21.siscm.onboarding.Model.ItemOptions;
import com.nau21.siscm.onboarding.Model.Items;
import java.util.List;

public interface ItemsDAO {
    void createItem(Items item);
    void updateItem(Items item);
    void deleteItem(int id);
    Items getItemById(int id);
    List<Items> getAllItems();
    void saveItemOption(ItemOptions itemOption);

}
