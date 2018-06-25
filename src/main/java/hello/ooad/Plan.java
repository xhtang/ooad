package hello.ooad;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Creator: DreamBoy
 * Date: 2018/6/25.
 */
@Entity
public class Plan extends DomainObject {
    private int time;
    private Date date;

    @ManyToOne
    private Equipment equipment;

    @OneToMany(mappedBy = "plan")
    private Collection<Record> records;

    public Plan() {

    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Collection<Record> getRecords() {
        return records;
    }

    public void addRecord(Record record) {
        if (records == null) {
            records = new ArrayList<>();
        }
        records.add(record);
        record.setPlan(this);
    }
}
