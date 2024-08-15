package farid.weather.controller;

import farid.weather.domain.ClothingOutfitService;
import farid.weather.models.ClothingOutfit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clothing_outfit")
public class ClothingOutfitController {

    private final ClothingOutfitService clothingOutfitService;

    public ClothingOutfitController(ClothingOutfitService clothingOutfitService) {
        this.clothingOutfitService = clothingOutfitService;
    }

    @GetMapping("/{outfitId}")
    public ClothingOutfit getOutfit(String outfitId) {
        return clothingOutfitService.getClothingOutfitById(outfitId);
    }





}
