/**
 * The Item class defines the attributes of an Item in the game.
 *
 * Bilal Kaaouachi
 * Version 1: 20.02.2020
 */

import java.util.*;
public class Item
{
    private ItemType lozenge = new ItemType();
    
    private ItemType smellyshoe = new ItemType();
    
    private boolean itemTakenStatus;

    public Item(){
        lozenge.itemname="Lozenge";
        lozenge.cpointValue=-5;
        
        smellyshoe.itemname="Smell Shoe";
        smellyshoe.cpointValue=10;
        
        itemTakenStatus = false;
    }

    
    public ItemType itemGetter(){
        Random rng = new Random();
        int i = rng.nextInt(2);
        if (i==0){
            return lozenge;
        }
        else{
            return smellyshoe;
        }
    }
    
    public String itemNameGetter(ItemType item){
        return item.itemname;
    }
    
    public int itemValueGetter(ItemType item){
        return item.cpointValue;
    }
    
    public boolean itemTakenStatusGetter(){
        return itemTakenStatus;
    }
    
    public void itemTakenStatusSetter(boolean status){
        itemTakenStatus=status;
        return;
    }
    
    
    
}

class ItemType
{
    String itemname;
    int cpointValue;
}
