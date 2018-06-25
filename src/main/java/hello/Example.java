//package hello;
//
//import javax.transaction.Transactional;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//@Service
//@Transactional
//public class Example {
//
//	private static final Logger log = LoggerFactory.getLogger(Example.class);
//
//	private SessionFactory sessionFactory;
//	public Example( SessionFactory sessionFactory){
//		this.sessionFactory = sessionFactory;
//	}
//
//
//	private Session getCurrentSession() {
//		return sessionFactory.getCurrentSession();
//	}
//
//	public void demo() throws Exception {
//		getCurrentSession().save(new Customer("Jack", "Bauer"));
//		getCurrentSession().save(new Customer("Chloe", "O'Brian"));
//		getCurrentSession().save(new Customer("Kim", "Bauer"));
//		getCurrentSession().save(new Customer("David", "Palmer"));
//		getCurrentSession().save(new Customer("Michelle", "Dessler"));
//
//		// fetch all customers
//		log.info("Customers found with findAll():");
//		log.info("-------------------------------");
//		for (Object customer : getCurrentSession().createQuery("FROM Customer").list()){
//			log.info(customer.toString());
//		}
//		log.info("");
//
//		// fetch an individual customer by ID
//		Customer customer = getCurrentSession().get(Customer.class, 1L);
//		if (customer != null){
//				log.info("Customer found with findById(1L):");
//				log.info("--------------------------------");
//				log.info(customer.toString());
//				log.info("");
//		}
//
//		// fetch customers by last name
//		log.info("Customer found with findByLastName('Bauer'):");
//		log.info("--------------------------------------------");
//		getCurrentSession().createQuery("FROM Customer c where c.lastName = :lastName").setParameter("lastName", "Bauer")
//		.list().forEach(bauer -> {
//			log.info(bauer.toString());
//		});
//
//		log.info("");
//
//	}
//
//}
