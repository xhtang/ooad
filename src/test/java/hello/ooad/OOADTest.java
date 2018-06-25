//package hello.ooad;
//
//import static org.junit.Assert.*;
//
//import java.util.Date;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import hello.Application;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@Transactional
//public class OOADTest {
//
//    @Autowired
//    private PersistenceManager pm;
//
//    void assertObjectPersisted(DomainObject domainObject){
//        assertNotNull(domainObject.getId());
//    }
//
//    @Test
//    public void createTeacherTest(){
//        Teacher t = new Teacher("txh");
//        t.setBirthday(new Date(0,11,11));
//        t.setGender(Gender.male);
//        t.getAddress().setAddrInfo("addrInfo");
//        t.getAddress().setPostCode("200433");
//        pm.getCurrentSession().save(t);
//
//        assertObjectPersisted(t);
//    }
//
//    @Test
//    @Rollback(false)
//    public void searchTeacherByHQLTest(){
//        Teacher t = new Teacher("txh");
//        t.setBirthday(new Date(0,11,11));
//        t.getAddress().setPostCode("200433");
//        pm.getCurrentSession().save(t);
//
//        String hql = "from Teacher t where t.address.postCode = '200433'";
//        assertEquals(1,pm.getCurrentSession().createQuery(hql).list().size());
//    }
//    @Test
//    public void createCourseTest(){
//        NormalCourse nc = new NormalCourse();
//        nc.setName("normal course");
//        nc.setClassRoom("2204");
//        pm.getCurrentSession().save(nc);
//
//        OnlineCourse oc = new OnlineCourse();
//        oc.setName("online course");
//        oc.setUrl("http://just.test");
//        pm.getCurrentSession().save(oc);
//
//        String normalCourseFinder = "from NormalCourse t";
//        NormalCourse resultnc = (NormalCourse)pm.getCurrentSession().createQuery(normalCourseFinder).uniqueResult();
//        assertEquals(nc,resultnc);
//
//        String courseFinder = "from Course c";
//        assertEquals(2,pm.getCurrentSession().createQuery(courseFinder).list().size());
//    }
//
//    @Test
//    public void associationTest(){
//        Teacher t = new Teacher("name");
//        t.setBirthday(new Date(0,11,11));
//        t.getAddress().setPostCode("200433");
//        pm.getCurrentSession().save(t);
//
//        NormalCourse nc = new NormalCourse();
//        nc.setName("normal course");
//        nc.setClassRoom("2204");
//        pm.getCurrentSession().save(nc);
//
//        t.addCourse(nc);
//
//        String hql = "from Course c where c.teacher.name = 'name'";
//        assertEquals(nc, pm.getCurrentSession().createQuery(hql).uniqueResult());
//
//    }
//}