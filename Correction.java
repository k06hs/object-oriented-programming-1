package PlantGuide;

import java.util.Date;

public class Correction {
    int correctionID;
    int userID;
    int plantID;
    String description;
    Date createdAt;

    public Correction(int id, int uid, int pid, String desc) {
        this.correctionID = id;
        this.userID = uid;
        this.plantID = pid;
        this.description = desc;
        this.createdAt = new Date();
    }

    public boolean submitCorrection() {
        return true;
    }
}
