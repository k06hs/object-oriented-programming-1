package PlantGuide;

import java.util.Date;

public class Tip {
    int tipID;
    int userID;
    int plantID;
    String content;
    String status = "pending";
    Date createdAt = new Date();

    public Tip(int id, int uid, int pid, String text) {
        this.tipID = id;
        this.userID = uid;
        this.plantID = pid;
        this.content = text;
    }

    public boolean editTipStatus(String newStatus) {
        this.status = newStatus;
        return true;
    }
}
