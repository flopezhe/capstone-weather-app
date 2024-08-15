package farid.weather.domain;

import farid.weather.data.ClothingOutfitRepository;
import farid.weather.models.ClothingOutfit;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClothingOutfitService {

    private final ClothingOutfitRepository clothingOutfitRepository;

    public ClothingOutfitService(ClothingOutfitRepository clothingOutfitRepository) {
        this.clothingOutfitRepository = clothingOutfitRepository;
    }

    public void createClothingOutfit(String outfitName, List<String> clothingItemIds) {
        ClothingOutfit clothingOutfit = new ClothingOutfit();
        clothingOutfit.setOutfitName(outfitName);
        clothingOutfit.setClothingItemIds(clothingItemIds);
        clothingOutfitRepository.save(clothingOutfit);
    }

    public List<ClothingOutfit> getAllClothingOutfits() {
        return clothingOutfitRepository.findAll();
    }

    public ClothingOutfit getClothingOutfitById(String id) {
        return clothingOutfitRepository.findById(id).orElse(null);
    }

    public void deleteClothingOutfit(String id) {
        clothingOutfitRepository.deleteById(id);
    }

    public void updateClothingOutfit(String id, String outfitName, List<String> clothingItemIds) {
        ClothingOutfit clothingOutfit = new ClothingOutfit();
        clothingOutfit.setId(id);
        clothingOutfit.setOutfitName(outfitName);
        clothingOutfit.setClothingItemIds(clothingItemIds);
        clothingOutfitRepository.save(clothingOutfit);
    }

    public Result<Void> validateSave(ClothingOutfit clothingOutfit){
        Result<Void> result = new Result<>();

        if(clothingOutfit.getOutfitName() == null){
            result.addMessage("Outfit Name is required!", ResultType.INVALID);
        }

        if(clothingOutfit.getClothingItemIds() == null){
            result.addMessage("Clothing Items are required!", ResultType.INVALID);
        }
        return result;
    }


}
