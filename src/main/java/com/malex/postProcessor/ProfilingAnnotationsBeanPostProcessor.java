package com.malex.postProcessor;

import com.malex.annotations.Profiling;
import com.malex.controller.impl.ProfilerController;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Log4j
public class ProfilingAnnotationsBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class> map = new HashMap<>();
    private ProfilerController controller = new ProfilerController();

    /**
     * Issue with MBean see https://stackoverflow.com/questions/25308744/creating-mbean-in-java
     */
    public ProfilingAnnotationsBeanPostProcessor()
            throws MalformedObjectNameException,
            NotCompliantMBeanException,
            InstanceAlreadyExistsException,
            MBeanRegistrationException {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("com.malex.controller.impl:type=ProfilerController,name=controller");
        beanServer.registerMBean(controller, objectName);
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
            ClassLoader classLoader = beanClass.getClassLoader();
            Class[] interfaces = beanClass.getInterfaces();
            InvocationHandler handler = getInvocationHandler(bean);
            return Proxy.newProxyInstance(classLoader, interfaces, handler);
        }
        return bean;
    }

    private InvocationHandler getInvocationHandler(Object bean) {
        return (proxy, method, args) -> controller.isEnabled()
                ? profilingMethod(bean, method, args)
                : method.invoke(bean, args);
    }

    private Object profilingMethod(Object bean, Method method, Object[] args)
            throws InvocationTargetException,
            IllegalAccessException {
        long before = System.nanoTime();
        log.info("Start profiling");

        // Invokes the underlying method
        Object retVal = method.invoke(bean, args);

        long after = System.nanoTime();
        log.info("End profiling, time: " + (after - before));
        return retVal;
    }
}
