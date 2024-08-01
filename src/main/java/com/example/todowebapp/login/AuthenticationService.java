package com.example.todowebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String userName,String password)
    {
        boolean inValidName = userName.equalsIgnoreCase("in28Minutes");
        boolean dummy = password.equalsIgnoreCase("dummy");
        return  inValidName && dummy;
    }
}
