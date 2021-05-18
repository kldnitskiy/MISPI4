package Mbeans;

import javax.transaction.Transactional;

public interface IntervalMBean {

    @Transactional
    void calculateInterval();

    double getInterval();

}
