/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.clothes;

import java.util.Date;
import java.util.Map;
import model.clothes.Clothes;
import model.clothes.ClothesDesign;
import model.clothes.ClothesItem;
import model.clothes.ClothesOrigin;

/**
 *
 * @author DELL
 */
public interface ClothesDAO {
    int deleteCLothes(int ID);
    int UpdateClothes(Clothes clothes);
    int UpdateCLothesDesign(ClothesDesign clothesDesign);
    int UpdateCLothesItem(ClothesItem clothesItem);
    int UpdateCLothesOrigin(ClothesOrigin clothesOrigin);
     Clothes getClothes(int ID);
    ClothesOrigin getClothesORG(int ID);
    ClothesDesign getClothesDSG(int ID);
    ClothesItem getClothesIT(int ID);
    Map<ClothesItem, Clothes> getClothesAllStt(int ID);
}
