package Services;

import javax.management.*;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class RestApplication extends Application  {
public static int counter = 0;

    public RestApplication() throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanException, NotCompliantMBeanException, InterruptedException, AttributeNotFoundException, ReflectionException, InstanceNotFoundException, InvalidAttributeValueException {
        counter++;
        if(counter == 1){
            MBeanService mBeanService = new MBeanService();
        }
        //MBeanService mBeanService = new MBeanService();
    }


}
