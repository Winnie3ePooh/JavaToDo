package controller;

import database.ObjectsDAO;
import database.User;
import database.UserVerify;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import java.lang.Exception;

@RestController
public class UsersController {

    private void sendVerifyEmail(User user){
        final String username = "login ot gugla";
        final String password = "pass";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("fromSomeone@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject("A testing mail header !!!");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        }

        catch (MessagingException e)
        {
            System.out.println("Username or Password are incorrect ... exiting !");
        }
    }

    //POST
    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public ResponseEntity newUser(@RequestBody User user){
        String result;
        try {
            System.out.println(user.getUsername());
            user.setAdmin(false);
            user.setIsVerified(false);
            user.setDateCreate(System.currentTimeMillis());
            Long suchwow = ObjectsDAO.save(user);
            /* Генерация hash */
            String hash = "hash";
            UserVerify uf = new UserVerify();
            uf.setHash(hash);
            uf.setUserId(suchwow);
            ObjectsDAO.save(uf);
            //sendVerifyEmail(user);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    //GET
    @RequestMapping(value = "/uf", method = RequestMethod.GET)
    public ModelAndView verifyUser(@RequestParam(value = "hash") String hash, Model model){
        try {
            //UserVerify userHash = ObjectsDAO.select(hash);
            //User user = ObjectsDAO.select(userHash.getUserId()); /* Ищем юзера по id из has */
            //user.setIsVerified(true);
            //ObjectsDAO.update(user); /* Сохранили юзера */
            //ObjectsDAO.delete(userHash); /* Снесли запись с хэшом по id юзера */
            String result = "GJ";
            model.addAttribute("result",result);
            return new ModelAndView("start_page");
        }
        catch (Exception ex){
            String result = "Ошибка при попытке подтверждения";
            model.addAttribute("result",result);
            return new ModelAndView("start_page");
        }
    }
    //POST
    @RequestMapping(value = "/addfriend", method = RequestMethod.POST)
    public void addFriend(){

    }
}
