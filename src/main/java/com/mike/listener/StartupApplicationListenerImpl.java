package com.mike.listener;

import com.mike.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListenerImpl implements
        ApplicationListener<ContextRefreshedEvent> {

    UserServiceImpl userService;

    @Autowired
    public StartupApplicationListenerImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {
        userService.encodeAll();
    }
}
