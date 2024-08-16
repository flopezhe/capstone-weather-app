package farid.weather.controller;

import farid.weather.domain.ClothingItemService;
import farid.weather.domain.Result;
import farid.weather.models.ClothingItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clothing_item")
public class ClothingItemController {

    private final ClothingItemService clothingItemService;

    public ClothingItemController(ClothingItemService clothingItemService) {
        this.clothingItemService = clothingItemService;
    }

    @GetMapping("/{clothingItemId}")
    public ClothingItem findById(@PathVariable String clothingItemId) {
        return clothingItemService.findClothingItemById(clothingItemId);
    }

//    @GetMapping("/by_type")
//    public List<ClothingItem> getClothingItemsByType(@RequestParam String type) {
//        return clothingItemService.getClothingItemsByType(type);
//    }

    @GetMapping("/by_type")
    public ResponseEntity<List<ClothingItem>> getClothingItemsByType(
            @RequestParam String type,
            @RequestParam String userId
    ) {
        List<ClothingItem> items = clothingItemService.getClothingItemsForUser(type, userId);
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<Void> addClothingItem(@RequestBody ClothingItem clothingItem) {
        clothingItemService.addClothingItem(clothingItem);
        return ResponseEntity.ok().build();
    }


//    @PostMapping
//    public ResponseEntity<Object> add(@RequestBody ClothingItem clothingItem) {
//        Result<ClothingItem> result = clothingItemService.saveClothingItem(clothingItem);
//
//        if(result.isSuccess()) {
//            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
//        }
//
//        return ErrorResponse.build(result);
//    }

    @PutMapping("/{clothingItemId}")
    public ResponseEntity<Object> update(@PathVariable String clothingItemId, @RequestBody ClothingItem clothingItem) {
        if(!clothingItemId.equalsIgnoreCase(clothingItem.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<ClothingItem> result = clothingItemService.updateClothingItem(clothingItem);

        if(result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{clothingItemId}")
    public ResponseEntity<Void> delete(@PathVariable String clothingItemId) {
        if(clothingItemService.deleteClothingItem(clothingItemId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
