package co.com.ceiba.mobile.pruebadeingreso.model.interactors;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.model.database.UserDto;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.UserDB;
import co.com.ceiba.mobile.pruebadeingreso.model.rest.ApiService;
import retrofit2.Call;
import retrofit2.Callback;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.users.IListUsers;
import retrofit2.Response;

public class ListUsersInteractor implements IListUsers.Interactor, Callback<List<User>> {

    private IListUsers.Presenter presenter;
    private List<User> users;
    private UserDto uDto;
    private ApiService apiService;

    @Inject
    public ListUsersInteractor(UserDto uDto, ApiService apiService) {
        this.apiService = apiService;
        this.users = new ArrayList<>();
        this.uDto = uDto;
    }

    @Override
    public void setPresenter(IListUsers.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getUsers() {
        if (uDto != null) {
            if (uDto.getUsers().isEmpty()) {
                Call<List<User>> call = apiService.getUsers();
                call.enqueue(this);
            } else {
                getLocalUsers();
                presenter.showUsers((ArrayList<User>)users);
            }
        } else {
            Log.i("getUsersInteractor", "UserDto is null");
        }
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful()){
            users = response.body();
            if(users != null) {
                presenter.showUsers((ArrayList<User>)users);
                saveUsers();
            } else {
                Log.i("onResponseShowUsers", "Response is null");
            }
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        Log.e("onFailureShowUsers", "Response failed");
    }

    private void getLocalUsers(){
        if(uDto != null) {
            for (UserDB u: uDto.getUsers()) {
                User us = new User();
                us.setName(u.getName());
                us.setPhone(u.getPhone());
                us.setId(u.getId());
                us.setEmail(u.getEmail());
                users.add(us);
            }
        } else {
            Log.i("getLocalUsers", "UsersDto is null");
        }

    }

    private void saveUsers(){
        if( users != null) {
            for (User u: users) {
                UserDB us = new UserDB(
                        u.getId(),
                        u.getName(),
                        u.getPhone(),
                        u.getEmail());
                uDto.addUser(us);
            }
        } else {
            Log.i("saveUsers", "Users is null");
        }

    }

    @Override
    public void searchUser(String text) {
        ArrayList<User> temp = new ArrayList<>();
        if( users != null) {
            for (User s : users) {
                if (s.getName().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(s);
                }
            }
        }
        presenter.updateList(temp);
    }

    public void setDto(UserDto uDto){
        this.uDto = uDto;
    }

}
