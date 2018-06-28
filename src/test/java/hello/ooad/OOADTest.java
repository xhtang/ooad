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
    public void createServiceAndData() {
        service = new OoadServiceImpl(pm.getSessionFactory());
        createData();
    }
    private void createData() {
        Equipment equipment1 = new Equipment("A0000", "camera", "Canada", "c100");
        Equipment equipment2 = new Equipment("A0001", "pc", "England", "p200");
        Equipment equipment3 = new Equipment("A0002", "ps4", "China", "p300");
        Equipment equipment4 = new Equipment("A0003", "xbox", "America", "x400");
        Equipment equipment5 = new Equipment("A0004", "ns", "Japan", "n500");

        Plan plan1 = new Plan(5, new Date(118, 5, 23));
        Plan plan2 = new Plan(10, new Date(118, 5, 24));
        Plan plan3 = new Plan(15, new Date(118, 5, 25));
        Plan plan4 = new Plan(20, new Date(118, 5, 24));
        Plan plan5_1 = new Plan(25, new Date(118, 5, 23));
        Plan plan5_2 = new Plan(30, new Date(118, 5, 22));

        plan1.setEquipment(equipment1);
        plan2.setEquipment(equipment2);
        plan3.setEquipment(equipment3);
        plan4.setEquipment(equipment4);
        plan5_1.setEquipment(equipment5);
        plan5_2.setEquipment(equipment5);

        Record record1 = new Record(new Date(118, 5, 25), "Xiao Wang", 4, "repair");
        Record record2 = new Record(new Date(118, 5, 26), "Xiao Li", 5, "upgrade");
        Record record3 = new Record(new Date(118, 5, 26), "Xiao Zhang", 6, "repair");
        Record record4 = new Record(new Date(118, 5, 24), "Xiao Gao", 7, "upgrade");
        Record record5_1 = new Record(new Date(118, 5, 24), "Xiao Tang", 8, "repair");
        Record record5_2_1 = new Record(new Date(118, 5, 23), "Xiao Tang", 8, "repair");
        Record record5_2_2 = new Record(new Date(118, 5, 24), "Xiao Tang", 10, "upgrade");

        record1.setEquipment(equipment1);
        record2.setEquipment(equipment2);
        record3.setEquipment(equipment3);
        record4.setEquipment(equipment4);
        record5_1.setEquipment(equipment5);
        record5_2_1.setEquipment(equipment5);
        record5_2_2.setEquipment(equipment5);

        record1.setPlan(plan1);
        record2.setPlan(plan2);
        record3.setPlan(plan3);
        record4.setPlan(plan4);
        record5_1.setPlan(plan5_1);
        record5_2_1.setPlan(plan5_2);
        record5_2_2.setPlan(plan5_2);

        service.addEquipment(equipment1);
        service.addEquipment(equipment2);
        service.addEquipment(equipment3);
        service.addEquipment(equipment4);
        service.addEquipment(equipment5);

        service.addPlan(plan1);
        service.addPlan(plan2);
        service.addPlan(plan3);
        service.addPlan(plan4);
        service.addPlan(plan5_1);
        service.addPlan(plan5_2);

        service.addRecord(record1);
        service.addRecord(record2);
        service.addRecord(record3);
        service.addRecord(record4);
        service.addRecord(record5_1);
        service.addRecord(record5_2_1);
        service.addRecord(record5_2_2);
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
        Equipment e = service.getEquipmentByNum("A0004");
        double hour1 = service.getHoursByENum("A0004");
        assertEquals(compare2D(hour1, 26), true);


        List<Plan> planList = service.getPlansByEquipmentId(e.getId());

        double hour2 = service.getHoursByEnumAndPlan("A0004", planList.get(0).getId());
        assertEquals(compare2D(hour2, 8), true);
        double hour3 = service.getHoursByEnumAndPlan("A0004", planList.get(1).getId());
        assertEquals(compare2D(hour3, 18), true);
    }


    private boolean compare2D(double a, double b) {
        final double epsilon = 0.0001;
        return Math.abs(a - b) < epsilon;
    }
}