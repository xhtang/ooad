package hello.ooad;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import hello.service.OoadService;
import hello.service.OoadServiceImpl;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import hello.Application;

import javax.xml.ws.Service;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class OOADTest {
    @Autowired
    private PersistenceManager pm;
    private OoadService service;

    void assertObjectPersisted(DomainObject domainObject){
        assertNotNull(domainObject.getId());
    }

    @Before
    public void createService() {
        service = new OoadServiceImpl(pm.getSessionFactory());
    }

    @Test
    public void createEquipmentTest() {
        Equipment equipment = new Equipment("A110", "Computer", "ZJ3306", "model");
        service.addEquipment(equipment);
        assertObjectPersisted(equipment);
    }

    @Test
    public void createPlanTest() {
        Plan plan1 = new Plan(5, new Date(118, 5, 23));
        service.addPlan(plan1);
        assertObjectPersisted(plan1);
    }

    @Test
    public void createRecordTest() {
        Plan plan1 = new Plan(5, new Date(118, 5, 23));
        Record record1 = new Record(new Date(118, 5, 25), "Xiao Wang", 4, "repair");
        record1.setPlan(plan1);
        service.addPlan(plan1);
        service.addRecord(record1);
        assertObjectPersisted(record1);
    }

    @Test
    public void queryPlanTest() {
        List<Plan> plans1 = service.getPlansByDate(5);
        assertEquals(plans1.size(), 1);
        List<Plan> plans2 = service.getPlansByDate(10);
        assertEquals(plans2.size(), 2);
        List<Plan> plans3 = service.getPlansByDate(20);
        assertEquals(plans3.size(), 4);
    }

    @Test
    public void querryWorkHour() {
        double hour1 = service.getHoursByENum("A0004");
        assertEquals(compare2D(hour1, 26), true);

        double hour2 = service.getHoursByEnumAndPlan("A0004", 10);
        assertEquals(compare2D(hour2, 8), true);
        double hour3 = service.getHoursByEnumAndPlan("A0004", 11);
        assertEquals(compare2D(hour3, 18), true);
    }


    private boolean compare2D(double a, double b) {
        final double epsilon = 0.0001;
        return Math.abs(a - b) < epsilon;
    }
}