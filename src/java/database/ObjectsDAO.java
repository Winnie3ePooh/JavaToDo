package database;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Vector;

@Transactional
public class ObjectsDAO {

    public static Long save(Object obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Long objId = null;
        try {
            tx = session.beginTransaction();
            objId = (Long) session.save(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return objId;
    }
    public static void delete(Object obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static Object select(Class cls, Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Object selected = null;
        try {
            tx = session.beginTransaction();
            selected = session.get(cls, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return selected;
    }
    public static void update(Object obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static User getUserByName(String name) {
        User user = new User();
        user.setUsername(name);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria userCriteria = session.createCriteria(User.class);
            userCriteria.add(Restrictions.eq("username", name));
            user = (User) userCriteria.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }


    //Прост создаем запись с хэшем для нового пользователя в таблице user_verify
    public static void setUserVer() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        User user = new User();
        user.setUsername("jesusets1");
        user.setPassword("iamjesus");
        user.setIsAdmin(true);
        user.setIsVerified(false);
        session.persist(user);

        UserVerify userVerify = new UserVerify();
        userVerify.setUserId(user.getUserId());
        userVerify.setHash("123");

        user.setUserVerify(userVerify);

        session.save(user);
        tx.commit();
        session.close();

    }
    //Создаем запись в таблице login_log для пользователя и задаем login_time
    //Находит юзера по id либо по username.
    public static Long newLog(User user, String ip) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        if (user.getUserId() == null && user.getUsername() != null ) {
            String username = user.getUsername();
            Criteria userCriteria = session.createCriteria(User.class);
            userCriteria.add(Restrictions.eq("username",username));
            user = (User) userCriteria.uniqueResult();
        }
        LoginLog log = new LoginLog();
        log.setLoginTime(System.currentTimeMillis());
        log.setUser(user);
        log.setIp(ip);
        Long logId = (Long) session.save(log);
        tx.commit();
        session.close();
        return logId;
    }
    //Добавляем logout_time для пользователя
    public static void lastLog(LoginLog loginLog) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Long logId = loginLog.getLoginId();

        loginLog = (LoginLog) select(LoginLog.class, logId);
        loginLog.setLogoutTime(System.currentTimeMillis());
        session.save(loginLog);
        tx.commit();
        session.close();
    }
    //Достаем список списков задач(четко) определенного автора
    public static void getAuthoredTasks(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        if (user.getUserId() == null && user.getUsername() != null ) {
            String username = user.getUsername();
            Criteria userCriteria = session.createCriteria(User.class);
            userCriteria.add(Restrictions.eq("username",username));
            user = (User) userCriteria.uniqueResult();
        }
        List<TaskList> authoredTasks = user.getAuthoredTasks();
        TaskList taskList = authoredTasks.iterator().next();
        User author = taskList.getAuthor();
        String authorName = author.getUsername();
        System.out.println(authorName);
        tx.commit();
        session.close();
    }

    //Получить списки заданий для юзера
    public static List<TaskList> getUserLists(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        user = ObjectsDAO.getUserByName(user.getUsername());
        session.update(user);
        List<TaskList> lists = user.getTaskList();
        session.flush();
        Hibernate.initialize(lists);
        tx.commit();
        session.close();

        return lists;
    }

    //Получить списки заданий для юзера
    public static List<Task> getListTasks(TaskList list) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Task> tasks = new Vector<Task>();
        try {
            Criteria userCriteria = session.createCriteria(TaskList.class);
            userCriteria.add(Restrictions.eq("listId", list.getListId()));
            list = (TaskList) userCriteria.uniqueResult();
            session.update(list);
            tasks = list.getListTask();
            Hibernate.initialize(tasks);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tasks;
    }
}
