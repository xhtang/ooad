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
        Equipment equipment1 = new Equipment();
        equipment1.setE_number("A0000");
        equipment1.setE_type("camera");
        equipment1.setE_model("t800");



        
    }
}
