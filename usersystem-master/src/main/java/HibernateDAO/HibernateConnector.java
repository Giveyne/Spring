package HibernateDAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;



public class HibernateConnector {

   /* public String connectionDatabse(User user){

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();


            Criteria criteria = session.createCriteria(User.class);
            try {
                if(criteria.add(Restrictions.eq("name", user.getName()))!= null){
                    session.getTransaction().rollback();
                    return "invalid";
                }
                else {
                    session.save(user);
                    session.getTransaction().commit();
                    return "valid";
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }


        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
        return "invalid";
    }*/

}