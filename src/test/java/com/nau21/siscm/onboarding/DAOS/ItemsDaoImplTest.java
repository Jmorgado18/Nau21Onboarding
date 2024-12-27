package com.nau21.siscm.onboarding.DAOS;

import com.nau21.siscm.onboarding.Model.ItemOptions;
import com.nau21.siscm.onboarding.Model.Items;
import com.nau21.siscm.onboarding.Model.optionName;
import com.nau21.siscm.onboarding.Model.optionValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemsDaoImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ItemsDaoImpl itemsDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createItem_ShouldPersistItem() {

        Items item = new Items();
        item.setId(1);
        item.setName("Item Test");


        itemsDao.createItem(item);


        verify(entityManager, times(1)).persist(item);
    }

    @Test
    void updateItem_ShouldMergeItem() {

        Items item = new Items();
        item.setId(1);
        item.setName("Updated Item Test");


        itemsDao.updateItem(item);


        verify(entityManager, times(1)).merge(item);
    }

    @Test
    void deleteItem_ShouldRemoveItemIfExists() {

        Items item = new Items();
        item.setId(1);
        when(entityManager.find(Items.class, 1)).thenReturn(item);


        itemsDao.deleteItem(1);


        verify(entityManager, times(1)).find(Items.class, 1);
        verify(entityManager, times(1)).remove(item);
    }

    @Test
    void deleteItem_ShouldDoNothingIfItemDoesNotExist() {

        when(entityManager.find(Items.class, 1)).thenReturn(null);


        itemsDao.deleteItem(1);


        verify(entityManager, times(1)).find(Items.class, 1);
        verify(entityManager, never()).remove(any());
    }

    @Test
    void getItemById_ShouldReturnItem() {

        Items item = new Items();
        item.setId(1);
        item.setName("Item Test");
        when(entityManager.find(Items.class, 1)).thenReturn(item);


        Items result = itemsDao.getItemById(1);


        assertNotNull(result);
        assertEquals("Item Test", result.getName());
        verify(entityManager, times(1)).find(Items.class, 1);
    }

    @Test
    void getAllItems_ShouldReturnListOfItems() {

        List<Items> items = new ArrayList<>();
        Items item1 = new Items();
        item1.setId(1);
        item1.setName("Item 1");

        Items item2 = new Items();
        item2.setId(2);
        item2.setName("Item 2");

        items.add(item1);
        items.add(item2);


        TypedQuery<Items> queryMock = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT i FROM Items i", Items.class)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(items);


        List<Items> result = itemsDao.getAllItems();


        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Item 1", result.get(0).getName());
        assertEquals("Item 2", result.get(1).getName());
        verify(entityManager, times(1)).createQuery("SELECT i FROM Items i", Items.class);
        verify(queryMock, times(1)).getResultList();
    }

    @Test
    void saveItemOption_ShouldPersistItemOption() {

        ItemOptions itemOption = new ItemOptions();
        itemOption.setId(1);
        itemOption.setName(com.nau21.siscm.onboarding.Model.optionName.SIZE);
        itemOption.setValue(com.nau21.siscm.onboarding.Model.optionValue.LARGE);


        itemsDao.saveItemOption(itemOption);


        verify(entityManager, times(1)).persist(itemOption);
    }


}
