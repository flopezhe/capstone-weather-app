package farid.weather.domain;

import farid.weather.data.ClothingItemRepository;
import farid.weather.models.ClothingItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothingItemService {
    private final ClothingItemRepository clothingItemRepository;

    public ClothingItemService(ClothingItemRepository clothingItemRepository) {
        this.clothingItemRepository = clothingItemRepository;
    }

    public ClothingItem findClothingItemById(String id) {
        return clothingItemRepository.findById(id).orElse(null);
    }

    public List<ClothingItem> getClothingItemsByType(String type) {
        return clothingItemRepository.findByClothingType(type);
    }

    public List<ClothingItem> getClothingItemsForUser(String type, String userId) {
        return clothingItemRepository.findByClothingTypeAndUserId(type, userId);
    }

    public void addClothingItem(ClothingItem clothingItem) {
        if (clothingItem.getUserId() == null || clothingItem.getUserId().isEmpty()) {
            throw new IllegalArgumentException("UserId must not be null or empty");
        }
        clothingItemRepository.save(clothingItem);
    }

    public boolean deleteClothingItem(String id) {
        if (clothingItemRepository.existsById(id)) {
            clothingItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Result<ClothingItem> updateClothingItem(ClothingItem clothingItem) {
        Result<ClothingItem> result = validateSave(clothingItem);

        if (result.isSuccess()) {
            clothingItemRepository.save(clothingItem);
        } else {
            result.addMessage("Clothing Item not updated", ResultType.INVALID);
        }

        return result;
    }

    private Result<ClothingItem> validateSave(ClothingItem clothingItem) {
        Result<ClothingItem> result = new Result<>();

        if (clothingItem.getClothingType() == null || clothingItem.getClothingType().isEmpty()) {
            result.addMessage("Clothing Type is required!", ResultType.INVALID);
        }

        if (clothingItem.getClothingName() == null || clothingItem.getClothingName().isEmpty()) {
            result.addMessage("Clothing Name is required!", ResultType.INVALID);
        }

        if (clothingItem.getClothingImage() == null || clothingItem.getClothingImage().isEmpty()) {
            result.addMessage("Clothing Image is required!", ResultType.INVALID);
        }

        if (clothingItem.getWearOnRainyDay() == null) {
            result.addMessage("Wear On Rainy Day is required!", ResultType.INVALID);
        }

        if (clothingItem.getWearOnHotDay() == null) {
            result.addMessage("Wear On Hot Day is required!", ResultType.INVALID);
        }

        return result;
    }
}
