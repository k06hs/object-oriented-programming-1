package plantGuide;

import java.util.Date;

public class Plant {
    protected int plantID;
    protected String name;
    protected String species;
    protected String careInstructions;
    protected String symbolicMeaning;
    protected Date createdAt;

    public Plant(int id, String name, String species, String care, String meaning) {
        this.plantID = id;
        this.name = name;
        this.species = species;
        this.careInstructions = care;
        this.symbolicMeaning = meaning;
        this.createdAt = new Date();
    }

    public String getPlantInfo() {
        return "Plant: " + name + " (" + species + ") - Care: " + careInstructions;
    }

    public boolean validateData() {
        return name != null && species != null;
    }

    public void updatePlantInfo(Plant updatedPlant) {
        this.name = updatedPlant.name;
        this.species = updatedPlant.species;
        this.careInstructions = updatedPlant.careInstructions;
        this.symbolicMeaning = updatedPlant.symbolicMeaning;
    }
}
