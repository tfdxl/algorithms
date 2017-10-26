package com.tfdxl.unittest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * mybean
 */
public class MyBean implements ApplicationContextAware, InitializingBean, DisposableBean {

    final static Logger logger = LoggerFactory.getLogger(MyBean.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("setApplicationContext ...");
        this.applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        logger.info("destroy... ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("afterPropertiesSet ... ");
    }
}
