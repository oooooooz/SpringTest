package x.y;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ClassUtils;
import x.y.service.Foo;
import x.y.service.FooService;

/**
 * author: ff
 * Date: 2015/6/22
 */
public class Boot {

    public static void main(String[] args) throws Exception{

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        //BeanFactory factory = ctx.getBeanFactory(); // beans.factory.support.DefaultListableBeanFactory
        FooService fooService = (FooService) ctx.getBean("fooService") ;
        Object obj = ctx.getBean("sessionFactory");
        System.out.println(obj.getClass().getName());
        Object ins = Class.forName("x.y.service.Foo").newInstance();
        //autowireBean(ins,ctx);
        int count = 0;
//        while(count++ < 5) {
//            Foo foo1 = (Foo) ctx.getBean("foo1");
//            Foo foo2 = (Foo) ctx.getBean("foo2");
//            System.out.println("foo1" + foo1.toString() + "|" + foo1.hashCode());
//            System.out.println("foo2" + foo2.toString() + "|" + foo2.hashCode());
//        }

        try {
            fooService.insertOrUpdate(new Foo());
//            fooService.updateFoo(new ArrayList<Foo>());
            //fooService.updateFoo(new Foo());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void autowireBean(Object bean, ApplicationContext appContext) {
        autowireBean(bean, appContext, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME);
    }


    private static void autowireBean(Object bean, ApplicationContext appContext, int autowireMode) {
        if (bean == null || appContext == null) {
            return;
        }

        AutowireCapableBeanFactory factory = appContext.getAutowireCapableBeanFactory();
        factory.autowireBeanProperties(bean, autowireMode, false);

        String beanName = ClassUtils.getUserClass(bean).getName();
        factory.initializeBean(bean, beanName);

        System.out.println(appContext.getBean("foo"));
    }



}
