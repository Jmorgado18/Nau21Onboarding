package com.nau21.siscm.onboarding.DAOS;

import com.nau21.siscm.onboarding.Model.ItemOptions;
import com.nau21.siscm.onboarding.Model.Items;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ItemsDaoImpl implements ItemsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createItem(Items item) {
       // System.out.println("Salvando item no DAO: " + item);
        entityManager.persist(item);
    }

    @Override
    public void updateItem(Items item) {
        entityManager.merge(item);
    }

    @Override
    public void deleteItem(int id) {
        Items item = entityManager.find(Items.class, id);
        if (item != null) {
            entityManager.remove(item);
        }
    }

    @Override
    public Items getItemById(int id) {
        return entityManager.find(Items.class, id);
    }

    @Override
    public List<Items> getAllItems() {
        return entityManager.createQuery("SELECT i FROM Items i", Items.class).getResultList();
    }
    @Override
    public void saveItemOption(ItemOptions itemOption) {
        entityManager.persist(itemOption);
    }

}
