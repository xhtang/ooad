package hello;

import hello.ooad.Equipment;
import hello.service.OoadService;
import hello.service.OoadServiceImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        Equipment equipment = new Equipment();
        equipment.setE_number("ASUS");
        //System.out.println("sdjflekrjglrjtgldkj,fvnsbdkvdbfkjvksjhdvd        "+service.getEquipmentById(5).getE_number());
    }
}
