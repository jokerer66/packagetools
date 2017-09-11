package service;

import bean.User;
import dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DealUser {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserDao userDao=(UserDao) ctx.getBean("userDao");
        User user=new User();
        //添加两条数据
        user.setId(1);
        user.setUserName("Jessica");
        user.setPassword("123");
        user.setUserAge(20);
        user.setUserAddress("dizhi");
        userDao.addUser(user);
        user.setId(2);
        user.setUserName("Jessica2");
        user.setPassword("123");
        user.setUserAge(24);
        user.setUserAddress("dizhi2");
        userDao.addUser(user);
        System.out.println("添加成功");
        //查询数据
        user.setUserName("Jessica");
        user.setPassword("123");
        System.out.println(userDao.getUser(user).getUserName().toString());
        user.setUserName("Jessica2");
        user.setPassword("123");
        System.out.println(userDao.getUser(user).getUserName().toString());
        //修改数据
        user.setId(2);
        user.setPassword("802");
        userDao.updateUser(user);
        System.out.println("修改成功");
        //删除数据
        userDao.deleteUser(1);
        System.out.println("删除成功");

    }
}
