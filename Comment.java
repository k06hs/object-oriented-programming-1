package PlantGuide;

import java.util.Date;

public class Comment {
    int commentID;
    int userID;
    int plantID;
    String content;
    Date createdAt;

    public Comment(int cid, int uid, int pid, String text) {
        this.commentID = cid;
        this.userID = uid;
        this.plantID = pid;
        this.content = text;
        this.createdAt = new Date();
    }
}
