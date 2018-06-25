package hello;

import hello.ooad.*;
import hello.ooad.Plan;
import hello.service.OoadService;
import hello.service.OoadServiceImpl;
import org.h2.table.*;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Creator: DreamBoy
 * Date: 2018/6/25.
 */
@Service
@Transactional
public class CreateData {
    private OoadService service;

    public CreateData(SessionFactory sessionFactory) {
        service = new OoadServiceImpl(sessionFactory);
    }

    public void createData() {
        Equipment asus = new Equipment();
        asus.setE_number("ASUS");
        Plan plan1 = new Plan();
        plan1.setDate(new Date());
        plan1.setTime(10);
        Plan plan2 = new Plan();
        plan2.setDate(new Date());
        plan2.setTime(20);

        asus.addPlan(plan1);
        asus.addPlan(plan2);

        int eid = service.addEquipment(asus);
        service.addPlan(plan1);
        service.addPlan(plan2);
        List<Plan> plans = service.getPlansByEquipmentId(eid);
        System.out.println("         plan's size:    "+plans.size());
        System.out.println("         plan1's equipment:    "+plans.get(0).getEquipment().getE_number());
    }
}
