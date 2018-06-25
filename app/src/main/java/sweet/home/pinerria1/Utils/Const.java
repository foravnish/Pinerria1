package sweet.home.pinerria1.Utils;

/**
 * Created by Andriod Avnish on 11-Apr-18.
 */

public class Const {

    String id;
    String catid;
    String eventName;
    String photo;
    String date;
    String desc;
    String orgby;

    public Const(String id, String catid, String eventName, String photo, String date){
        this.id=id;
        this.catid=catid;
        this.eventName=eventName;
        this.photo=photo;
        this.date=date;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatid() {
        return catid;
    }

    public String getEventName() {
        return eventName;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }

    public String getOrgby() {
        return orgby;
    }
}
