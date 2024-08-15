package farid.weather.domain;

import farid.weather.data.ClothingOutfitRepository;
import farid.weather.models.ClothingItem;
import farid.weather.models.ClothingOutfit;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClothingOutfitService {

    private final ClothingOutfitRepository clothingOutfitRepository;

    public ClothingOutfitService(ClothingOutfitRepository clothingOutfitRepository) {
        this.clothingOutfitRepository = clothingOutfitRepository;
    }

    public Result<ClothingOutfit> createClothingOutfit(ClothingOutfit clothingOutfit) {
        Result<ClothingOutfit> result = validateSave(clothingOutfit);

        if (result.isSuccess()) {
            clothingOutfitRepository.save(clothingOutfit);
        } else {
            result.addMessage("Clothing Outfit not saved", ResultType.INVALID);
        }

        return result;

    }

    public List<ClothingOutfit> getAllClothingOutfits() {
        return clothingOutfitRepository.findAll();
    }

    public ClothingOutfit getClothingOutfitById(String id) {
        return clothingOutfitRepository.findById(id).orElse(null);
    }

    public boolean deleteClothingOutfit(String id) {
        boolean result = clothingOutfitRepository.existsById(id);

        if (result) {
            clothingOutfitRepository.deleteById(id);
        }

        return result;
    }

    public Result<ClothingOutfit> updateClothingOutfit(ClothingOutfit clothingOutfit) {
        Result<ClothingOutfit> result = validateSave(clothingOutfit);

        if (result.isSuccess()) {
            clothingOutfitRepository.save(clothingOutfit);
        } else {
            result.addMessage("Clothing Outfit not updated", ResultType.INVALID);
        }

        return result;

    }

    public Result<ClothingOutfit> validateSave(ClothingOutfit clothingOutfit){
        Result<ClothingOutfit> result = new Result<>();

        if(clothingOutfit.getOutfitName() == null){
            result.addMessage("Outfit Name is required!", ResultType.INVALID);
        }

        if(clothingOutfit.getClothingItemIds() == null){
            result.addMessage("Clothing Items are required!", ResultType.INVALID);
        }
        return result;
    }


}
