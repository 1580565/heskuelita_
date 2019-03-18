
package com.capgemini.heskuelita.service.impl;


import com.capgemini.heskuelita.data.IUserDao;
import com.capgemini.heskuelita.data.entity.UserAnnotation;
import com.capgemini.heskuelita.service.IUserSecurityService;
import com.capgemini.heskuelita.service.SecurityException;


public class UserSecurityServiceImpl implements IUserSecurityService {


    private IUserDao userDao;

    public UserSecurityServiceImpl(IUserDao userDao) {

        super ();

        this.userDao = userDao;
    }


    @Override
    public void login (UserAnnotation user) throws SecurityException {

        try {
            this.userDao.login (user.getUs(), user.getPw());
        } catch (Exception e) {

            throw new SecurityException(e);
        }
    }

    @Override
    public void NewUser(UserAnnotation user) throws SecurityException {

        try {
            this.userDao.NewUser(user.getUs(),user.getPw(),user.getEmail(), user.getNombre(),user.getApellido(),user.getFnac(),user.getGen(),user.getDoc());
        }catch (Exception e){
            throw new SecurityException(e);
        }

    }

}