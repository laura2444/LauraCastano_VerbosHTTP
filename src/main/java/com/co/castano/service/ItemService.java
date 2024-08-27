package com.co.castano.service;

import com.co.castano.model.GroceryItem;
import com.co.castano.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private List<GroceryItem> groceryItems; //lista local

    @Autowired  //para no tener que declarar constructor de itemrepository, inyección de dependencias
    private ItemRepository itemRepository;


    boolean started=false;

    /**
     * Descripción: inicializa la lista de groceryItems con elementos predeterminados si no ha sido inicializada antes
     * local: este arrayList es local y almacena los items a nivel local y no se almacenan directamente en la bd
     * @started: booleana para ver si la lista de groceryItems ya ha sido inicializada antes
     *
     **/
    public void setGroceryItems(){
        if (this.started==false){
            this.groceryItems = new ArrayList();
            groceryItems.add(new GroceryItem("Whole", "Whole Wheat Biscuit", 5, "snacks"));
            groceryItems.add(new GroceryItem("Dried", "Dried Whole Red Chilli", 2, "spices"));
            groceryItems.add(new GroceryItem("Pearl", "Healthy Pearl Millet", 1, "millets"));
            groceryItems.add(new GroceryItem("Cheese", "Bonny Cheese Crackers Plain", 6, "snacks"));
            this.started=true;
        }
    }

    /**
     * Descripción: metodo para inicializar si es que no hay elementos en la bd, pero esto no es local, esto ya es a nivel bd
     * */
    @PostConstruct
    public void init(){
        List<GroceryItem>item = itemRepository.findAll();
        if(item.isEmpty()){
            itemRepository.save(new GroceryItem("Whole", "Whole Wheat Biscuit", 5, "snacks"));
            itemRepository.save(new GroceryItem("Dried", "Dried Whole Red Chilli", 2, "spices"));
            itemRepository.save(new GroceryItem("Pearl", "Healthy Pearl Millet", 1, "millets"));
            itemRepository.save(new GroceryItem("Cheese", "Bonny Cheese Crackers Plain", 6, "snacks"));
        }
    }

    public String getAll(){
        setGroceryItems();
        List<GroceryItem> item = itemRepository.findAll();
        return "---ITEMS EXISTENTES---\n" + item.toString();
    }

    public String insert(GroceryItem groceryItem){
        setGroceryItems();
        groceryItems.add(new GroceryItem(groceryItem.getId(), groceryItem.getName(), groceryItem.getQuantity(), groceryItem.getCategory()));
        itemRepository.save(groceryItem);  //se extiende de mongo repository
        return "---ITEM INSERTADO---\n"+groceryItem.toString();
    }

    /**
     * Descripción: metodo que actualiza en la bd (PUT) actualización COMPLETA del objeto en la bd
     * @Optional: variable que ayuda a validar si existe ese objeto que quiero retornar, ya que es posible que se mande un id que no exista en bd
     * @isPresent() : averigua si hay un valor presente en esa instancia de option, devuelve true o false
     * */
    public String update(GroceryItem groceryItem){
        setGroceryItems();

        Optional<GroceryItem>existing= itemRepository.findById(groceryItem.getId());
        if(existing.isPresent()){
            GroceryItem item= existing.get();
            item.setName(groceryItem.getName());
            item.setQuantity(groceryItem.getQuantity());
            item.setCategory(groceryItem.getCategory());
            itemRepository.save(item);
            return "---ITEM ACTUALIZADO---\n"+item.toString();
        }
        return "---ITEM NO ENCONTRADO---";
    }


    public String delete(String id) {
        setGroceryItems();
        if(itemRepository.existsById(id)){
            itemRepository.deleteById(id);
            return "---ITEM ELIMINADO---";
        }
        return "---ITEM NO ENCONTRADO---";
    }

    /**
     * Descripción: método que actualiza en la bd (PATCH) actualiza solo determinados campos del objeto
     * @.get : metodo que se usa para obtener el valor que se encuentra en Optional si es que hay algo alli
     * @Nota: los 3 if se usan para actualizar solo los campos que se dieron en el objeto, verificando si el valor es null o no, y si es >=0 en el caso de Quantity
     * */

    public String updateData(String id, GroceryItem groceryItem){
        setGroceryItems();

        Optional<GroceryItem>existing= itemRepository.findById(id);
        if(existing.isPresent()){
            GroceryItem item= existing.get();
            if (groceryItem.getName() != null) {
                item.setName(groceryItem.getName());
            }
            if (groceryItem.getCategory() != null) {
                item.setCategory(groceryItem.getCategory());
            }
            if(groceryItem.getQuantity()>=0){
                item.setQuantity(groceryItem.getQuantity());
            }
            itemRepository.save(item);
            return "---ITEM ACTUALIZADO---\n"+item.toString();
        }
        return "---ITEM NO ENCONTRADO---";
    }


    public String optionsUpdate(){
        return "OPTIONS: It insert a new grosery item, if the grosery item doesn't exist, it will create automatically";
    }

}
