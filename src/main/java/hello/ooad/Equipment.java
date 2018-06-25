package hello.ooad;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Creator: DreamBoy
 * Date: 2018/6/25.
 */
@Entity
public class Equipment extends DomainObject {
    private String e_number;
    private String e_type;
    private String e_address;
    private String e_model;

    @OneToMany(mappedBy="equipment")
    private Collection<Plan> plans;

    @OneToMany(mappedBy = "equipment")
    private Collection<Record> records;

    public Equipment() {}
    public Equipment(String e_number, String e_type, String e_address, String e_model) {
        this.e_number = e_number;
        this.e_type = e_type;
        this.e_address = e_address;
        this.e_model = e_model;
    }

    public String getE_number() {
        return e_number;
    }

    public void setE_number(String e_number) {
        this.e_number = e_number;
    }

    public String getE_type() {
        return e_type;
    }

    public void setE_type(String e_type) {
        this.e_type = e_type;
    }

    public String getE_address() {
        return e_address;
    }

    public void setE_address(String e_address) {
        this.e_address = e_address;
    }

    public String getE_model() {
        return e_model;
    }

    public void setE_model(String e_model) {
        this.e_model = e_model;
    }

    public Collection<Plan> getPlans() {
        return plans;
    }

    public void addPlan(Plan plan) {
        if (plans == null) {
            plans = new ArrayList<>();
        }
        plans.add(plan);
        plan.setEquipment(this);
    }

    public Collection<Record> getRecords() {
        return records;
    }

    public void addRecord(Record record) {
        if (records == null) {
            records = new ArrayList<>();
        }
        records.add(record);
        record.setEquipment(this);
    }
}
