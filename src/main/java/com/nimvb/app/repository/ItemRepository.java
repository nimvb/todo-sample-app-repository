package com.nimvb.app.repository;

import com.nimvb.app.database.document.Document;
import com.nimvb.app.database.model.Item;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Optional;

@Repository
public class ItemRepository implements SimpleRepository<Item,Integer> {

    private final Document<Item,Integer> itemDocument;

    public ItemRepository(@Qualifier("itemDocument") Document<Item, Integer> itemDocument) {
        this.itemDocument = itemDocument;
    }

    @Override
    public Item save(Item item) {
        Assert.notNull(item,"null object is passed");
        if(item.getId() == null){
            return itemDocument.insert(item);
        }
        return itemDocument.update(item.getId(),item);
    }

    @Override
    public Item persist(Item item) {
        Assert.notNull(item,"null object is passed");
        if(item.getId() == null){
            return itemDocument.insert(item,false);
        }
        return itemDocument.update(item.getId(),item,false);
    }

    @Override
    public Optional<Item> findById(Integer integer) {
        Assert.notNull(integer,"null id is passed");
        return Optional.ofNullable(itemDocument.find(integer));
    }

    @Override
    public Optional<Item> fetchById(Integer id) {
        return Optional.ofNullable(itemDocument.fetch(id));
    }

    @Override
    public Collection<Item> findAll() {
        return itemDocument.all();
    }

    @Override
    public void deleteById(Integer integer) {
        Assert.notNull(integer,"null id is passed");
        itemDocument.delete(integer);
    }

    @Override
    public Integer count() {
        return itemDocument.count();
    }
}
