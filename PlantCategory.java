package PlantGuide;

import java.util.ArrayList;

public class PlantCategory {
    private int categoryID;
    private String categoryName;
    private ArrayList<Plant> plantList = new ArrayList<>();

    public PlantCategory(int id, String name) {
        this.categoryID = id;
        this.categoryName = name;
    }

    public void addCategory(Plant plant) {
        plantList.add(plant);
    }

    public boolean removeCategory(int plantID) {
        return plantList.removeIf(p -> p.plantID == plantID);
    }

    public ArrayList<Plant> listPlants() {
        return plantList;
    }
}
