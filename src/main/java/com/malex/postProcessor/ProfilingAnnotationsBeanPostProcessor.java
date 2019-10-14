package com.malex.postProcessor;


import com.malex.annotations.Profiling;
import com.malex.controller.impl.ProfilerController;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Log4j
public class ProfilingAnnotationsBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class> map = new HashMap<>();
    private ProfilerController controller = new ProfilerController();

    public ProfilingAnnotationsBeanPostProcessor() throws Exception {
//        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
//        beanServer.registerMBean(controller, new ObjectName("profiling", "name", "controller"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        Class beanClass = map.get(beanName);
        if (Objects.nonNull(beanClass)) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), getInvocationHandler(bean));
        }
        return bean;
    }

    private InvocationHandler getInvocationHandler(Object bean) {
        return (proxy, method, args) -> controller.isEnabled()
                ? profilingMethod(bean, method, args)
                : method.invoke(bean, args);
    }

    private Object profilingMethod(Object bean, Method method, Object[] args) throws Exception {
        log.info("Start profiling");
        long before = System.nanoTime();
        Object retVal = method.invoke(bean, args);
        long after = System.nanoTime();
        log.info("End profiling, time: " + (after - before));
        return retVal;
    }
}
