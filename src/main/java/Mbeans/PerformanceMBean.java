package Mbeans;

import javax.management.*;
import javax.transaction.Transactional;

public interface PerformanceMBean {

    @Transactional
    void calculateAllPoints() throws AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, InvalidAttributeValueException, MalformedObjectNameException;

    @Transactional
    void calculateAreaPoints();

    @Transactional
    void calculateUserAccuracy();

    int getAllPoints();

    int getAreaPoints();





}
