package co.com.ceiba.mobile.pruebadeingreso.model.database;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;


import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.di.ApplicationContext;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.UserDB;

public class UserDto {

    @SuppressLint("StaticFieldLeak")
    private static UserDto uDto;
    private UserDao uDao;

    @Inject
    public UserDto(@ApplicationContext Context context) {
        Context appContext = context.getApplicationContext();
        ProjectDatabase database = Room.databaseBuilder(appContext, ProjectDatabase.class, "users")
                .allowMainThreadQueries().build();
        uDao = database.getUserDao();
    }

    public List<UserDB> getUsers() {
        return uDao.getUsers();
    }

    public UserDB getUser(String id) {
        return uDao.getUser(id);
    }

    public void addUser(UserDB user) {
        uDao.addUser(user);
    }

    public void updateUser(UserDB user) {
        uDao.updateUser(user);
    }

    public void deleteUser(UserDB user) {
        uDao.deleteUser(user);
    }

}
