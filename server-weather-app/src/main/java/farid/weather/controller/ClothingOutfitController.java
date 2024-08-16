package farid.weather.controller;

import farid.weather.domain.ClothingOutfitService;
import farid.weather.domain.Result;
import farid.weather.models.ClothingItem;
import farid.weather.models.ClothingOutfit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clothing_outfit")
public class ClothingOutfitController {

    private final ClothingOutfitService clothingOutfitService;

    public ClothingOutfitController(ClothingOutfitService clothingOutfitService) {
        this.clothingOutfitService = clothingOutfitService;
    }

    @GetMapping("/{outfitId}")
    public ClothingOutfit getOutfit(@PathVariable String outfitId) {
        return clothingOutfitService.getClothingOutfitById(outfitId);
    }

    @PostMapping
    public ResponseEntity<Object> add(ClothingOutfit clothingOutfit) {
        Result<ClothingOutfit> result = clothingOutfitService.createClothingOutfit(clothingOutfit);

        if(result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{outfitId}")
    public ResponseEntity<Object> update(@PathVariable String outfitId, @RequestBody ClothingOutfit clothingOutfit) {
        if(!outfitId.equalsIgnoreCase(clothingOutfit.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<ClothingOutfit> result = clothingOutfitService.updateClothingOutfit(clothingOutfit);

        if(result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{outfitId}")
    public ResponseEntity<Void> delete(@PathVariable String outfitId) {
        if(clothingOutfitService.deleteClothingOutfit(outfitId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }





}
