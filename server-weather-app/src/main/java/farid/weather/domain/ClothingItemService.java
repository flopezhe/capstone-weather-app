package farid.weather.domain;

import farid.weather.data.ClothingItemRepository;
import farid.weather.models.ClothingItem;
import org.springframework.stereotype.Service;

@Service
public class ClothingItemService {
    private final ClothingItemRepository clothingItemRepository;

    public ClothingItemService(ClothingItemRepository clothingItemRepository) {
        this.clothingItemRepository = clothingItemRepository;
    }

    public ClothingItemRepository getClothingItemRepository() {
        return clothingItemRepository;
    }

    public Result<ClothingItem> saveClothingItem(ClothingItem clothingItem) {

        Result<ClothingItem> result = validateSave(clothingItem);

        if (result.isSuccess()) {
            clothingItemRepository.save(clothingItem);
        } else {
            result.addMessage("Clothing Item not saved", ResultType.INVALID);
        }

        return result;
    }

    public ClothingItem getClothingItemById(String id) {
        return clothingItemRepository.findById(id).orElse(null);
    }

    public void deleteClothingItem(String id) {
        clothingItemRepository.deleteById(id);
    }

    public void updateClothingItem(String id, String clothingType, String clothingName, String clothingImage, String wearOnRainyDay, String wearOnHotDay) {
        ClothingItem clothingItem = new ClothingItem();
        clothingItem.setId(id);
        clothingItem.setClothingType(clothingType);
        clothingItem.setClothingName(clothingName);
        clothingItem.setClothingImage(clothingImage);
        clothingItem.setWearOnRainyDay(wearOnRainyDay);
        clothingItem.setWearOnHotDay(wearOnHotDay);
        clothingItemRepository.save(clothingItem);
    }

    public Result<ClothingItem> validateSave(ClothingItem clothingItem) {
        Result<ClothingItem> result = new Result<>();

        if (clothingItem.getClothingType() == null) {
            result.addMessage("Clothing Type is required!", ResultType.INVALID);
        }

        if (clothingItem.getClothingName() == null) {
            result.addMessage("Clothing Name is required!", ResultType.INVALID);
        }

        if (clothingItem.getClothingImage() == null) {
            result.addMessage("Clothing Image is required!", ResultType.INVALID);
        }

        if (!clothingItem.getWearOnRainyDay().equalsIgnoreCase("yes") && !clothingItem.getWearOnRainyDay().equalsIgnoreCase("no")) {
            result.addMessage("Wear On Rainy Day must be yes or no!", ResultType.INVALID);
        }

        if (!clothingItem.getWearOnHotDay().equalsIgnoreCase("yes") && !clothingItem.getWearOnHotDay().equalsIgnoreCase("no")) {
            result.addMessage("Wear On Hot Day must be yes or no!", ResultType.INVALID);
        }

        return result;
    }


}
