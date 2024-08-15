package farid.weather.domain;

import farid.weather.data.ClothingItemRepository;
import farid.weather.models.ClothingItem;

public class ClothingItemService {
    private final ClothingItemRepository clothingItemRepository;

    public ClothingItemService(ClothingItemRepository clothingItemRepository) {
        this.clothingItemRepository = clothingItemRepository;
    }

    public ClothingItemRepository getClothingItemRepository() {
        return clothingItemRepository;
    }

    public ClothingItem saveClothingItem(ClothingItem clothingItem) {
        return clothingItemRepository.save(clothingItem);
    }

    public ClothingItem getClothingItemById(String id) {
        return clothingItemRepository.findById(id).orElse(null);
    }

    public void deleteClothingItem(String id) {
        clothingItemRepository.deleteById(id);
    }

    public void updateClothingItem(String id, String clothingType, String clothingName, String clothingImage, int wearOnRainyDay, int wearOnHotDay) {
        ClothingItem clothingItem = new ClothingItem();
        clothingItem.setId(id);
        clothingItem.setClothingType(clothingType);
        clothingItem.setClothingName(clothingName);
        clothingItem.setClothingImage(clothingImage);
        clothingItem.setWearOnRainyDay(wearOnRainyDay);
        clothingItem.setWearOnHotDay(wearOnHotDay);
        clothingItemRepository.save(clothingItem);
    }

    public void validateSave(ClothingItem clothingItem) {
        if (clothingItem.getClothingType() == null) {
            throw new IllegalArgumentException("Clothing Type is required!");
        }

        if (clothingItem.getClothingName() == null) {
            throw new IllegalArgumentException("Clothing Name is required!");
        }

        if (clothingItem.getClothingImage() == null) {
            throw new IllegalArgumentException("Clothing Image is required!");
        }

        if (clothingItem.getWearOnRainyDay() < 0) {
            throw new IllegalArgumentException("Wear On Rainy Day must be greater than or equal to 0!");
        }

        if (clothingItem.getWearOnHotDay() < 0) {
            throw new IllegalArgumentException("Wear On Hot Day must be greater than or equal to 0!");
        }
    }


}
