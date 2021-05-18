package Services;
import Mbeans.Interval;
import Mbeans.Performance;
import Mbeans.PerformanceMBean;

import javax.annotation.PostConstruct;
import javax.ejb.Init;
import javax.management.*;
import javax.management.remote.*;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.lang.management.ManagementFactory;
public class MBeanService {

    MBeanService() throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanException, NotCompliantMBeanException, InterruptedException, AttributeNotFoundException, ReflectionException, InstanceNotFoundException, InvalidAttributeValueException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("mispi.example:type=Performance");
        ObjectName xname = new ObjectName("mispi.example:type=Interval");


        Performance performance = new Performance();

        Interval interval = new Interval();


        mbs.registerMBean(interval, xname);
        mbs.registerMBean(performance, name);


        //Attribute  attr = new Attribute("Performance", 25);
       // mbs.setAttribute(name, attr);


        System.out.println("MBean set up");
    }

}
