package co.com.ceiba.mobile.pruebadeingreso.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.UserDB;

@Database(entities = {UserDB.class}, version = 1)
public abstract class ProjectDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}