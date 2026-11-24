package PlantGuide;

public class PlantSymbol extends Plant {
    private String meaning;
    private String culture;

    public PlantSymbol(int id, String name, String species, String care, String meaning, String culture) {
        super(id, name, species, care, meaning);
        this.meaning = meaning;
        this.culture = culture;
    }

    public String getMeaning() {
        return "Symbolic meaning: " + meaning + " in " + culture + " culture.";
    }

    public boolean updateMeaning(String newMeaning) {
        this.meaning = newMeaning;
        return true;
    }
}
