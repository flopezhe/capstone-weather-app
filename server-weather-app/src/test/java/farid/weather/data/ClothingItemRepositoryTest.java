package farid.weather.data;

import farid.weather.models.ClothingItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.Assert.*;

@DataMongoTest
class ClothingItemRepositoryTest {

    @Autowired
    private ClothingItemRepository clothingItemRepository;

    @Test
    void findByClothingTypeAndUserId() {
        ClothingItem clothingItem1 = new ClothingItem("1", "bottom", "GRAY SHIRT", "FAKEURL","YES", "NO", "user1");

        ClothingItem clothingItem2 = new ClothingItem("2", "top", "WHITE SHIRT", "FAKEURL","YES", "NO", "user1");

        clothingItemRepository.save(clothingItem1);
        clothingItemRepository.save(clothingItem2);

        List<ClothingItem> user1Clothing = clothingItemRepository.findByClothingTypeAndUserId("top", "user1");

        assertNotNull(user1Clothing);
    }

    @Test
    void testSave () {
        ClothingItem clothingItem1 = new ClothingItem("1", "bottom", "GRAY SHIRT", "FAKEURL", "YES", "NO", "user10");

        clothingItemRepository.save(clothingItem1);

        ClothingItem actual = clothingItemRepository.findById("1").orElse(null);

        assertEquals(clothingItem1, actual);

    }


    @Test
    void testDelete () {
        ClothingItem clothingItem1 = new ClothingItem("1", "bottom", "GRAY SHIRT", "FAKEURL", "YES", "NO", "user11");

        clothingItemRepository.save(clothingItem1);

        clothingItemRepository.deleteById("1");

        ClothingItem actual = clothingItemRepository.findById("1").orElse(null);

        assertNull(actual);
    }



}