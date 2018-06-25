package hello.ooad;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Creator: DreamBoy
 * Date: 2018/6/25.
 */
@Entity
public class Record extends DomainObject {
    private Date date;
    private String person;
    private int time;
    private String detail;

    @ManyToOne
    private Equipment equipment;

    @ManyToOne
    private Plan plan;

    public Record() {}
    public Record(Date date, String person, int time, String detail) {
        this.date = date;
        this.person = person;
        this.time = time;
        this.detail = detail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
