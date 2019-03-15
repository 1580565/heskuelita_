
package com.capgemini.heskuelita.service;


import com.capgemini.heskuelita.data.entity.UserAnnotation;


public interface IUserSecurityService {

    //Para logear un usuario existente.
    void login (UserAnnotation user) throws SecurityException;
    void NewUser(UserAnnotation user) throws  SecurityException;

}