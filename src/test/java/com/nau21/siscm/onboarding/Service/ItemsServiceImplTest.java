package com.nau21.siscm.onboarding.Service;

import com.nau21.siscm.onboarding.DAOS.ItemsDAO;
import com.nau21.siscm.onboarding.Model.ItemOptions;
import com.nau21.siscm.onboarding.Model.Items;
import com.nau21.siscm.onboarding.Model.optionName;
import com.nau21.siscm.onboarding.Model.optionValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemsServiceImplTest {



    @Mock
    private ItemsDAO itemsDAO;

    @InjectMocks
    private ItemsServiceImpl itemsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateItem() {
        Items item = new Items();
        item.setName("Laptop");
        item.setDescription("High-end gaming laptop");
        item.setPrice(1500.00);
        item.setCurrency("USD");
        item.setQuantity(10);

        doNothing().when(itemsDAO).createItem(item);

        itemsService.createItem(item);

        verify(itemsDAO, times(1)).createItem(item);
    }

    @Test
    void testUpdateItem() {
        Items item = new Items();
        item.setId(1);
        item.setName("Updated Laptop");
        item.setDescription("Updated description");
        item.setPrice(1700.00);

        doNothing().when(itemsDAO).updateItem(item);

        itemsService.updateItem(item);

        verify(itemsDAO, times(1)).updateItem(item);
    }

    @Test
    void testDeleteItem() {
        int itemId = 1;

        doNothing().when(itemsDAO).deleteItem(itemId);

        itemsService.deleteItem(itemId);

        verify(itemsDAO, times(1)).deleteItem(itemId);
    }

    @Test
    void testGetItemById() {
        int itemId = 1;
        Items item = new Items();
        item.setId(itemId);
        item.setName("Smartphone");
        item.setDescription("Latest model smartphone");

        when(itemsDAO.getItemById(itemId)).thenReturn(item);

        Items result = itemsService.getItemById(itemId);

        assertNotNull(result);
        assertEquals("Smartphone", result.getName());
        assertEquals("Latest model smartphone", result.getDescription());
        verify(itemsDAO, times(1)).getItemById(itemId);
    }

    @Test
    void testGetAllItems() {
        Items item1 = new Items();
        item1.setName("Item 1");
        item1.setDescription("Description 1");

        Items item2 = new Items();
        item2.setName("Item 2");
        item2.setDescription("Description 2");

        List<Items> items = Arrays.asList(item1, item2);

        when(itemsDAO.getAllItems()).thenReturn(items);

        List<Items> result = itemsService.getAllItems();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Item 1", result.get(0).getName());
        assertEquals("Item 2", result.get(1).getName());
        verify(itemsDAO, times(1)).getAllItems();
    }

    @Test
    void testCreateItemOption() {
        ItemOptions itemOption = new ItemOptions();
        itemOption.setName(optionName.COLOR);
        itemOption.setValue(optionValue.RED);

        doNothing().when(itemsDAO).saveItemOption(itemOption);

        itemsService.createItemOption(itemOption);

        verify(itemsDAO, times(1)).saveItemOption(itemOption);
    }
}
