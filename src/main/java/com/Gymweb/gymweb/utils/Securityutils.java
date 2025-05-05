package com.Gymweb.gymweb.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Securityutils {
    public static String getCurrentUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null){
            Object principal = authentication.getPrincipal();
            if(principal instanceof String){
                return (String) principal;

            }else if( principal instanceof UserDetails){
                return ((UserDetails) principal).getUsername();
            }
        }
        return null;
    }
}
