package Mbeans;

import Beans.PointsBean;

import javax.management.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.lang.management.ManagementFactory;
import java.util.List;

public class Performance extends NotificationBroadcasterSupport implements PerformanceMBean {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory( "sample" );
    EntityManager entityManager = emf.createEntityManager();
    int allPoints = setupAllPoints();
    int areaPoints = setupAreaPoints();
    @Override
    public int getAllPoints() {
        return allPoints;
    }

    @Override
    public int getAreaPoints() {
        return areaPoints;
    }




    @Override
    public void calculateAllPoints() throws AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, InvalidAttributeValueException, MalformedObjectNameException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("mispi.example:type=Performance");
        //Attribute  attr = new Attribute(DestinationAttributes.MAX_NUM_PRODUCERS, 25);
       // mbs.setAttribute(name, new Attribute("Attr", "Name"));
        Query query = entityManager.createQuery("SELECT a FROM PointsBean a where a.username = :username", PointsBean.class);
        query.setParameter("username" , "Danya");
        List list = query.getResultList();
        allPoints = list.size();
        System.out.println(list.size());
    }

    int setupAllPoints(){
        Query query = entityManager.createQuery("SELECT a FROM PointsBean a where a.username = :username", PointsBean.class);
        query.setParameter("username" , "Danya");
        List list = query.getResultList();
        return list.size();
    }

    int setupAreaPoints(){
        Query query = entityManager.createQuery("SELECT a FROM PointsBean a where a.username = :username and a.status = true", PointsBean.class);
        query.setParameter("username" , "Danya");
        List list = query.getResultList();
        return list.size();
    }

    @Override
    public void calculateAreaPoints() {
        Query query = entityManager.createQuery("SELECT a FROM PointsBean a where a.username = :username and a.status = true", PointsBean.class);
        query.setParameter("username" , "Danya");
        List list = query.getResultList();
        areaPoints = list.size();
        System.out.println(list.size());
    }


    @Override
    public void calculateUserAccuracy(){
        Query query = entityManager.createQuery("SELECT a FROM PointsBean a where a.username = :username", PointsBean.class);
        query.setParameter("username" , "Danya");
        List<PointsBean> list = query.getResultList();
        int sequenceNumber = 0;
        System.out.println(list.get((list.size())-1).getStatus());
        System.out.println(list.get((list.size())-2).getStatus());
        if(!list.get((list.size()-2)).getStatus() && !list.get((list.size()-1)).getStatus()){
            System.out.println("Missed 2 times in a row");
            Notification n = new AttributeChangeNotification(this,
                    sequenceNumber++, System.currentTimeMillis(),
                    "Miss status", "Status", "String",
                    "Missed 2 times in a row", "yes");
            sendNotification(n);
        }



    }

}
