package database;

import java.io.Serializable;
import java.util.*;

public class DBClass implements Serializable {

    public static void main(String args[]) {


        /*
        //Добавление юзера по transient объекту
        User user = new User();
        user.setDateCreate(System.currentTimeMillis());
        user.setEmail("redishko@gmail.com");
        user.setIsAdmin(true);
        user.setIsVerified(true);
        user.setUsername("redishko");
        user.setPassword("1234");
        userDao.save(user);


        //Удаление юзеров по transient объекту
        User user1 = new User();
        user1.setUserId((long) 2);
        user1.setIsAdmin(true);
        userDao.delete(user1);

        //Выбор по ID
        User user2 = (User) userDao.select(User.class, (long) 3);
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        */

        /*
        //Достаем пароль пользователя через имя
        User user3 = ObjectsDAO.getUserByName("test");
        System.out.println(user3.getPassword());
        */

        //ObjectsDAO.setUserVer();
        /*
        //Достанем хеш по айдишнику юзера
        User user = (User) ObjectsDAO.select(User.class, (long) 1);
        UserVerify userVerify = (UserVerify) user.getUserVerify();
        String hash = userVerify.getHash();
        System.out.println(hash);*/

        /*
        //Создаем не привязанный к сессии(detached) объект user с заданным id
        //Либо создаем transient объект с username.
        //Создаем первичную запись в таблце login_log с полем login_time
        //Получаем айдишник новой записи
        //Добавляем logout_time для той же записи
        User user = new User();
        user.setUsername("test");
        //user.setUserId((long) 1);
        String ip = "x.x.x.x";
        Long logId = ObjectsDAO.newLog(user, ip);

        LoginLog loginLog = new LoginLog();
        loginLog.setLoginId(logId);
        ObjectsDAO.lastLog(loginLog);
        */

        /*
        //Создадим новый список задач и привяжем его к пользователю
        User user = ObjectsDAO.getUserByName("test");
        TaskList taskList = new TaskList();
        taskList.setDateChange(System.currentTimeMillis());
        taskList.setDateCreate(System.currentTimeMillis());
        List<User> users = new ArrayList<User>();
        users.add(user);
        taskList.setUsers(users);
        ObjectsDAO.save(taskList);
        */
        /*
        //Достаем список списков задач по айди или юзернейму автора
        User user = new User();
        //user.setUserId((long) 4);
        user.setUsername("test");
        ObjectsDAO.getAuthoredTasks(user);
        */
    }
}