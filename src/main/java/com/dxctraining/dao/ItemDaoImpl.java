package com.dxctraining.dao;

import com.dxctraining.entities.Item;
import com.dxctraining.exceptions.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ItemDaoImpl implements IItemDao{

    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Item findItemById(int id) {
        Item item=entityManager.find(Item.class,id);
        if(item==null){
            throw new ItemNotFoundException("item not found for id="+id);
        }
        return item;
    }

    @Override
    public Item update(Item item) {
        item=entityManager.merge(item);
        return item;
    }

    @Override
    public Item save(Item item) {
        entityManager.persist(item);
        return item;
    }

    @Override
    public void remove(int id) {
     Item item= entityManager.find(Item.class,id) ;
     entityManager.remove(item);
    }
}