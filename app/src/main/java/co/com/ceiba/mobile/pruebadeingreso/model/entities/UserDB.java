package co.com.ceiba.mobile.pruebadeingreso.model.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import lombok.Data;

@Entity
@Data
public class UserDB {
    @PrimaryKey
    @NonNull
    private Integer id;
    private String name;
    private String phone;
    private String email;

    public UserDB(@NonNull Integer id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

}
