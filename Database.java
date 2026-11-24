package PlantGuide;

import java.util.ArrayList;

public class Database {

    ArrayList<Plant> plantList = new ArrayList<>();
    ArrayList<Tip> tipList = new ArrayList<>();
    ArrayList<Correction> correctionList = new ArrayList<>();
    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Comment> commentList = new ArrayList<>();

    public boolean addPlant(Plant p) {
        return plantList.add(p);
    }

    public boolean removePlant(int plantID) {
        return plantList.removeIf(p -> p.plantID == plantID);
    }

    public ArrayList<Plant> getPlantByName(String name) {
        ArrayList<Plant> result = new ArrayList<>();
        for (Plant p : plantList)
            if (p.name.equalsIgnoreCase(name)) result.add(p);
        return result;
    }

    public Plant getPlantByID(int id) {
        for (Plant p : plantList)
            if (p.plantID == id) return p;
        return null;
    }

    public Database() { }
}
