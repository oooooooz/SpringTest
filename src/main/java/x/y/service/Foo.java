package x.y.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

/**
 * author: ff
 * Date: 2015/6/22
 */
public class Foo implements ApplicationListener<ContextRefreshedEvent>,
        BeanPostProcessor,BeanFactoryAware,InitializingBean,Ordered {

    private Long id;

    private String fooName;

    private String barName;

    public String getFooName() {
        return fooName;
    }

    public void setFooName(String fooName) {
        this.fooName = fooName;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString(){
        return "id=" + this.getId() + "|fooName=" + this.getFooName() + "|barName=" + this.getBarName();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("------------------> onApplicationEvent " + this);
        System.out.println(event.toString());

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("------------------> setBeanFactory "+this);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("------------------> postProcessBeforeInitialization " + beanName + ", " + this);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("------------------> postProcessAfterInitialization " + beanName+ ", " + this);
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("------------------> afterPropertiesSet " + this+ ", " + this + ",id="+id);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
