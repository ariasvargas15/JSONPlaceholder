package co.com.ceiba.mobile.pruebadeingreso.presenter.users;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;

public interface IListUsers {
    interface View {
        void showUsers(ArrayList<User> users);
        void updateList(ArrayList<User> users);
    }

    interface Presenter {
        void setView(IListUsers.View view);
        void getUsers();
        void showUsers(ArrayList<User> users);
        void searchUser(String text);
        void updateList(ArrayList<User> users);
    }
    interface Interactor {
        void setPresenter(IListUsers.Presenter presenter);
        void getUsers();
        void searchUser(String text);
    }
}
