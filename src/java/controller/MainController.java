package controller;

import database.ObjectsDAO;
import database.TaskList;
import database.User;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@SessionAttributes(types = User.class)
public class MainController {
    //GET
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage(ModelAndView model, User user){
        //Инициализация модели
        model.setViewName("Mandarin");

        //Задание юзера и заданий
        if (user.getUsername() == null){
            model.addObject("user", new User());
        }else {
            user = ObjectsDAO.getUserByName(user.getUsername());
            List<TaskList> lists = ObjectsDAO.getUserLists(user);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
            model.addObject("user", user);
            model.addObject("lists", lists);
        }
        return model;
    }

    @PostConstruct
    public void OnStart(){
        System.out.println("youAAAAA");
    }

}
