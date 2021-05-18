package Mbeans;

import Beans.PointsBean;
import Beans.UserBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class Interval implements IntervalMBean {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory( "sample" );
    EntityManager entityManager = emf.createEntityManager();

    double interval_value = setupInterval();



    @Override
    public void calculateInterval() {
        Query query = entityManager.createQuery("SELECT a FROM PointsBean a where a.username = :username", PointsBean.class);
        query.setParameter("username" , "Danya");
        List<PointsBean> list = query.getResultList();
        Iterator i = list.iterator();
        PointsBean bean;

        Double interval = 0.0;
        while (i.hasNext()) {
            bean = (PointsBean) i.next();
            interval = interval + Math.sqrt(bean.getX()*bean.getX() + bean.getY() * bean.getY());

        }
        interval_value = interval/list.size();
        System.out.println(interval/list.size());
    }

    double setupInterval(){
        Query query = entityManager.createQuery("SELECT a FROM PointsBean a where a.username = :username", PointsBean.class);
        query.setParameter("username" , "Danya");
        List<PointsBean> list = query.getResultList();
        Iterator i = list.iterator();
        PointsBean bean;

        Double interval = 0.0;
        while (i.hasNext()) {
            bean = (PointsBean) i.next();
            interval = interval + Math.sqrt(bean.getX()*bean.getX() + bean.getY() * bean.getY());

        }
        return interval/list.size();
    }

    @Override
    public double getInterval() {
        return interval_value;
    }


}
