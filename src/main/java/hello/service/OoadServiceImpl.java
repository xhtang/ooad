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
    public long addEquipment(Equipment equipment) {
        return (long) sessionFactory.getCurrentSession().save(equipment);
    }

    @Override
    public Equipment getEquipmentById(long id) {
        return (Equipment) sessionFactory.getCurrentSession().createQuery(
                "from Equipment e where e.id = "+ id ).list().get(0);
    }

    @Override
    public Equipment getEquipmentByNum(String number) {
        String sql = " from Equipment e where e.e_number = \'" + number + "\'";
        return (Equipment) sessionFactory.getCurrentSession().createQuery(sql).list().get(0);
    }

    @Override
    public long addPlan(Plan plan) {
        return (long) sessionFactory.getCurrentSession().save(plan);

    }

    @Override
    public Plan getPlanById(long id) {
        return (Plan) sessionFactory.getCurrentSession().createQuery("from Plan plan where plan.id = "+ id ).list().get(0);
    }

    @Override
    public List<Plan> getPlansByEquipmentId(long eid) {
        return (sessionFactory.getCurrentSession().createQuery(
                "from Plan plan where plan.equipment.id = "+ eid ).list());
    }

    @Override
    public List<Plan> getPlansByDate(long time) {
        List<Plan> plans = sessionFactory.getCurrentSession().createQuery(
                "from Plan plan").list();
        Date newDate = new Date();
        long ms = newDate.getTime() + time * 24 * 60 * 60 * 1000; //现在时间+查询时间

        Iterator<Plan> it = plans.iterator();
        while (it.hasNext()) {
            Plan plan = it.next();
            Date date = plan.getDate();
            long p_time = date.getTime() + (long)plan.getTime() * 24 * 60 * 60 * 1000;
            if (p_time > ms) {
                it.remove();
            }
        }
        return plans;
    }

    @Override
    public long addRecord(Record record) {
        long id = (long) sessionFactory.getCurrentSession().save(record);
        long pid = record.getPlan().getId();
        Plan plan = getPlanById(pid);
        plan.setDate(record.getDate());
        sessionFactory.getCurrentSession().update(plan);
        return id;
    }

    @Override
    public long getHoursByENum(String number) {
        List<Record> list = sessionFactory.getCurrentSession().createQuery(
                "from  Record record where record.equipment.e_number = \'" + number + "\'").list();
        long hour = 0;
        for (Record record: list) {
            hour += record.getTime();
        }
        return hour;
    }

    @Override
    public long getHoursByEnumAndPlan(String number, long pid) {
        List<Record> list = sessionFactory.getCurrentSession().createQuery(
                "from  Record record where record.equipment.e_number = \'" + number + "\' and record.plan.id = "+ pid).list();
        long hour = 0;
        for (Record record: list) {
            hour += record.getTime();
        }
        return hour;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
