package com.example.JavaLabSpringCloudAccount.source;


import com.example.JavaLabSpringCloudAccount.model.Account;
import com.example.JavaLabSpringCloudAccount.service.AccountService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@RabbitListener(queues = "mainQueue")
public class Receiver {

    @Autowired
    private AccountService accountService;

    @RabbitHandler
    public void receive(String in) {
        if (in.startsWith("subscribe")) {
            String[] params = in.split(" ");
            int userid = Integer.parseInt(params[1]);
            int courseid = Integer.parseInt(params[2]);
            accountService.addSubscription(userid,courseid);
        }
        System.out.println("Received: "+ in);
    }
    @RabbitListener(queues = "rpc-ex.requests")
    public String rpc_requests(String in) {
        if (in.startsWith("login")) {
            String[] params = in.split(" ");
            int userid = Integer.parseInt(params[1]);
            int code = Integer.parseInt(params[2]);
            Account user = accountService.getAccount(userid);
            if (user == null) return "false";
            return "true";
        } else if (in.startsWith("subscription")) {
            String[] params = in.split(" ");
            int userid = Integer.parseInt(params[1]);
            Account user = accountService.getAccount(userid);
            if (user == null) return "";
            String response = "";
            for (Integer i : user.getSubscriptions()) {
                response += i + " ";
            }
            return response.trim();
        }
        return null;
    }

    @RabbitListener(queues = "rpc-ex.requests")
    public String get_subscriptions(String in) {
        if (in.startsWith("subscription")) {
            String[] params = in.split(" ");
            int userid = Integer.parseInt(params[1]);
            Account user = accountService.getAccount(userid);
            if (user == null) return "";
            String response = "";
            for (Integer i : user.getSubscriptions()) {
                response += i + " ";
            }
            return response.trim();
        }
        return "";
    }
}
