package ru.stqa.pft.sandbox.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.model.BillData;

import java.util.List;

public class HbConnectionTest {

    private SessionFactory sessionFactory;


    @BeforeClass
    protected void setUp() throws Exception {


//        Configuration configuration = new Configuration();
//        configuration.configure();
//        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//        sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.build());


        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.out.println(String.format("ERROR SESSION -->> %s <<-- ERROR SESSION", e));
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Test
    public void testHbConnection() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<BillData> result = session.createQuery("from ru.stqa.pft.sandbox.model.BillData").list();
        for (BillData data: result) {
            System.out.println(data);
        }
        session.getTransaction().commit();
        session.close();
    }
}

