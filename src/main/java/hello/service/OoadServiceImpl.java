package hello.service;

import hello.ooad.Equipment;
import hello.ooad.Plan;
import hello.ooad.Record;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Creator: DreamBoy
 * Date: 2018/6/25.
 */
@Service
public class OoadServiceImpl implements OoadService {
    private SessionFactory sessionFactory;

    public OoadServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int addEquipment(Equipment equipment) {
        long id = (long) getCurrentSession().save(equipment);
        return (int)id;
    }

    @Override
    public Equipment getEquipmentById(int id) {
        return (Equipment) getCurrentSession().createQuery("from Equipment e where e.id = "+ id ).list().get(0);
    }

    @Override
    public Equipment getEquipentByNum(String number) {
        return null;
    }

    @Override
    public int addPlan(Plan plan) {
        return 0;
    }

    @Override
    public Plan getPlanById(int id) {
        return null;
    }

    @Override
    public List<Plan> getPlansByEquipmentId(int eid) {
        return null;
    }

    @Override
    public List<Plan> getPlansByDate(int time) {
        return null;
    }

    @Override
    public int addRecord(Record record) {
        return 0;
    }

    @Override
    public int getHoursByENum(String number) {
        return 0;
    }

    @Override
    public int getHoursByEnumAndPlan(String number, int pid) {
        return 0;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
