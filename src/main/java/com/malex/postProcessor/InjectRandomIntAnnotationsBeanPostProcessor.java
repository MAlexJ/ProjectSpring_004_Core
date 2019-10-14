package com.malex.postProcessor;

import com.malex.annotations.InjectRandomInt;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

public class InjectRandomIntAnnotationsBeanPostProcessor implements BeanPostProcessor {

    private Predicate<Field> hasInjectRandomIntAnnotation() {
        return field -> Objects.nonNull(field.getAnnotation(InjectRandomInt.class));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        Field[] fields = bean.getClass().getDeclaredFields();
        Arrays.stream(fields)
                .filter(hasInjectRandomIntAnnotation())
                .forEach(field -> {
                    InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
                    int min = annotation.min();
                    int max = annotation.max();
                    int randomInt = ThreadLocalRandom.current().nextInt(min, max);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, bean, randomInt);
                });
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
