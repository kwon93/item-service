package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository  = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item itemA = new Item("itemA", 10000, 10);

        //when
        Item saveItem = itemRepository.save(itemA);

        //then
        Item findItem = itemRepository.findById(itemA.getId());

        assertThat(findItem).isEqualTo(saveItem);
    }


    @Test
    void findAll(){
        //given

        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);


        itemRepository.save(itemA);
        itemRepository.save(itemB);
        //when
        List<Item> findAllItem = itemRepository.findAll();
        //then
        assertThat(findAllItem.size()).isEqualTo(2);
        assertThat(findAllItem).contains(itemA,itemB);
    }


    @Test
    void updateItem(){
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(itemA);
        Long id = savedItem.getId();

        //when
        Item updateParam = new Item("item2", 20000, 20);
        itemRepository.update(id, updateParam);

        //then
        Item findItem = itemRepository.findById(id);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());

    }

    @Test
    @DisplayName("삭제하기")
    void delete(){
        Item item1 = new Item("itemA", 10000, 10);
        Item item3 = new Item("itemC", 30000, 20);
        Item item2 = new Item("itemB", 20000, 30);
        Item savedItem1 = itemRepository.save(item1);
        Item savedItem2 = itemRepository.save(item2);
        Item savedItem3 = itemRepository.save(item3);
        Long id = savedItem1.getId();

        itemRepository.delete(id);

        List<Item> items = itemRepository.findAll();

        assertThat(items.size()).isEqualTo(2);


    }
}