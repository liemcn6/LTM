/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.item;

import java.util.List;
import model.Item.Item;

/**
 *
 * @author ADMIN
 */
public interface ItemDAO {

    int deleteItem(int id);

    int updateItem(Item item);

    Item getItem(int id);

    List<Item> getNewItems(int limit, int from);
    
    List<Item> getNewItemsByCategory(int limit, int from, String category);
}
