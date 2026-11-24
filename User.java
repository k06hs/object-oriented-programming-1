package PlantGuide;

import java.util.ArrayList;

public class User {
    int userID;
    String username;
    String email;
    String password;

    public User(int id, String name, String email, String password) {
        this.userID = id;
        this.username = name;
        this.email = email;
        this.password = password;
    }

    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public ArrayList<Plant> searchPlant(String name, Database db) {
        return db.getPlantByName(name);
    }

    public Tip shareTip(Tip tip) {
        return tip;
    }

    public Correction submitCorrection(Correction correction) {
        return correction;
    }
}
