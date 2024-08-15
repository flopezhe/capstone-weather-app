package farid.weather.controller;

import farid.weather.domain.ClothingItemService;
import farid.weather.models.ClothingItem;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clothing_item")
public class ClothingItemController {

    private final ClothingItemService clothingItemService;

    public ClothingItemController(ClothingItemService clothingItemService) {
        this.clothingItemService = clothingItemService;
    }

    @PostMapping
    public void add(ClothingItem clothingItem) {
        clothingItemService.saveClothingItem(clothingItem);
    }

}
