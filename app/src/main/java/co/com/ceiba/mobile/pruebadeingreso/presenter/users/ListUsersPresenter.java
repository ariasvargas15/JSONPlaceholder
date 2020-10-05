package co.com.ceiba.mobile.pruebadeingreso.presenter.users;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;

public class ListUsersPresenter implements IListUsers.Presenter {

    private IListUsers.View view;

    @Inject
    IListUsers.Interactor interactor;


    @Inject
    public ListUsersPresenter() {
    }

    @Override
    public void setView(IListUsers.View view) {
        this.view = view;
        interactor.setPresenter(this);
    }

    public void setInteractor(IListUsers.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void getUsers() {
        interactor.getUsers();
    }

    @Override
    public void showUsers(ArrayList<User> users) {
        if (view != null) {
            view.showUsers(users);
        } else {
            Log.i("showUsersPresenter", "View is null");
        }
    }

    @Override
    public void searchUser(String text) {
        if (interactor != null) {
            interactor.searchUser(text);
        }
    }

    @Override
    public void updateList(ArrayList<User> users) {
        view.updateList(users);
    }
}
