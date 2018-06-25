package hello.service;

import hello.ooad.Equipment;
import hello.ooad.Plan;
import hello.ooad.Record;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
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
    public Equipment getEquipmentByNum(String number) {
        String sql = " from Equipment e where e.e_number = \'" + number + "\'";
        return (Equipment) getCurrentSession().createQuery(sql).list().get(0);
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
        List<Plan> plans = getCurrentSession().createQuery("from Plan plan").list();
        Date newDate = new Date();
        long ms = newDate.getTime() + time * 24 * 60 * 60 * 1000;

        Iterator<Plan> it = plans.iterator();
        while (it.hasNext()) {
            Plan plan = it.next();
            Date date = plan.getDate();
            date.setTime(date.getTime() + plan.getTime() * 24 * 60 * 60 * 1000);
            if (date.getTime() > ms) {
                it.remove();
            }
        }
        return plans;
    }

    @Override
    public int addRecord(Record record) {
        long id = (long) getCurrentSession().save(record);
        long pid = record.getPlan().getId();
        Plan plan = getPlanById((int) pid);
        plan.setDate(record.getDate());
        getCurrentSession().update(plan);
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
