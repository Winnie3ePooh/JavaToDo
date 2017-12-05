package controller;

import database.ObjectsDAO;
import database.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@SessionAttributes(types = User.class)
public class AuthController {
    private String result;

    @RequestMapping(value = "/log_in", method = RequestMethod.GET)
    public ModelAndView login(User user, HttpServletRequest request){
        User dbUser = ObjectsDAO.getUserByName(user.getUsername());
        ModelAndView model = new ModelAndView("redirect:/");
        if(dbUser.getPassword().equals(user.getPassword())){
            String ip = request.getRemoteAddr();
            model.addObject("user", dbUser);
        }
        return model;
    }

    @RequestMapping(value = "/log_out", method = RequestMethod.GET)
    public ModelAndView logout(User user, HttpServletRequest request){
        ModelAndView model = new ModelAndView("redirect:/");
        model.addObject("user", new User());
        return model;
    }
}
