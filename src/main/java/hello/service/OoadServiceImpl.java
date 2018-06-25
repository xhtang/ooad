package hello.service;

import hello.ooad.Equipment;
import hello.ooad.Plan;
import hello.ooad.Record;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        return (Equipment) getCurrentSession().createQuery("from Equipment e where e.e_number = "+ number ).list().get(0);
    }

    @Override
    public int addPlan(Plan plan) {
        long id = (long) getCurrentSession().save(plan);
        return (int)id;
    }

    @Override
    public Plan getPlanById(int id) {
        return (Plan) getCurrentSession().createQuery("from Plan plan where plan.id = "+ id ).list().get(0);
    }

    @Override
    public List<Plan> getPlansByEquipmentId(int eid) {
        return (getCurrentSession().createQuery("from Plan plan where plan.equipment.id = "+ eid ).list());
    }

    @Override
    public List<Plan> getPlansByDate(int time) {
        long ms = time * 24 * 60 * 60 * 1000;
        Date date = new Date(2018, 6, 26);
        return (getCurrentSession().createQuery("from Plan plan where plan.date - date <  "+ ms ).list());
    }

    @Override
    public int addRecord(Record record) {
        long id = (long) getCurrentSession().save(record);
        return (int)id;
    }

    @Override
    public int getHoursByENum(String number) {
        List<Record> list = getCurrentSession().createQuery("from  Record record where record.equipment.e_number = " + number).list();
        int hour = 0;
        for (Record record: list) {
            hour += record.getTime();
        }
        return hour;
    }

    @Override
    public int getHoursByEnumAndPlan(String number, int pid) {
        List<Record> list = getCurrentSession().createQuery("from  Record record where record.equipment.e_number = " + number + " and record.plan.id = "+ pid).list();
        int hour = 0;
        for (Record record: list) {
            hour += record.getTime();
        }
        return hour;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
