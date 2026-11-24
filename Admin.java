package PlantGuide;

import java.util.ArrayList;

public class Admin extends User {
    private ArrayList<String> privileges = new ArrayList<>();

    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password);
        privileges.add("approve");
        privileges.add("edit");
    }

    public boolean approveTip(Tip tip) {
        tip.status = "approved";
        return true;
    }

    public boolean approveCorrection(Correction correction) {
        return true;
    }

    public boolean updateDatabase(Plant plant, Database db) {
        return db.addPlant(plant);
    }
}
