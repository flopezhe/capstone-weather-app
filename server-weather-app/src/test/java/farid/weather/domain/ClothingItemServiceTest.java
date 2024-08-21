package farid.weather.domain;

import farid.weather.data.ClothingItemRepository;
import farid.weather.models.ClothingItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClothingItemServiceTest {

   @MockBean
   ClothingItemRepository clothingItemRepository;

   @Autowired
    ClothingItemService clothingItemService;


    @Test
    void addClothingItem() {
        ClothingItem clothingItem = new ClothingItem("1", "bottom", "blue shirt", "fakeurl1", "yes", "no", "user1");

        when(clothingItemRepository.save(any())).thenReturn(clothingItem);


        clothingItemService.addClothingItem(clothingItem);
        List<ClothingItem> actual = clothingItemRepository.findByClothingTypeAndUserId("bottom","user1");

        assertNotNull(actual);
    }

    @Test
    void shouldNotAddClothingItemWithoutType(){
        ClothingItem clothingItem = new ClothingItem("1", "", "blue shirt", "fakeurl1", "yes", "no", "user1");

        clothingItemService.addClothingItem(clothingItem);

        Result<ClothingItem> expected = clothingItemService.updateClothingItem(clothingItem);

        assertFalse(expected.isSuccess());
    }

    @Test
    void deleteClothingItem() {
        ClothingItem clothingItem = new ClothingItem("10", "bottom", "blue shirt", "fakeurl1", "yes", "no", "user1");

        when(clothingItemRepository.existsById("10")).thenReturn(false);

        clothingItemService.deleteClothingItem("10");

        boolean actual = clothingItemRepository.existsById("10");

        assertFalse(actual);



    }

    @Test
    void updateClothingItem(){
        ClothingItem clothingItem = new ClothingItem("1", "bottom", "blue shirt", "fakeurl1", "yes", "no", "user1");


        when(clothingItemRepository.save(any())).thenReturn(clothingItem);

        Result<ClothingItem> expected = new Result<>();
        expected.setPayload(clothingItem);
        Result<ClothingItem> actual = clothingItemService.updateClothingItem(clothingItem);
        actual.setPayload(clothingItem);

        assertNotNull(actual);
    }

}