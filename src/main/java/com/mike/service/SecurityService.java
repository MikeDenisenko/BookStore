package com.mike.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String name, String password);

}
