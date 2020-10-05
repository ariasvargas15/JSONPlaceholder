package co.com.ceiba.mobile.pruebadeingreso.presenter.users;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListUsersPresenterTest {

    @Mock
    private IListUsers.View view;
    @Mock
    private IListUsers.Interactor interactor;
    @InjectMocks private ListUsersPresenter presenter;

    @Before
    public void init() {
        presenter.setInteractor(interactor);
        presenter.setView(view);
    }

    @Test
    public void getUsers() {
        this.presenter.getUsers();
        verify(interactor, times(1)).getUsers();
    }

    @Test
    public void showUsers() {
        User user = mock(User.class);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        this.presenter.showUsers(users);
        verify(view, times(1)).showUsers(users);
    }

    @Test
    public void searchUser() {
        String texto = "dummy";
        this.presenter.searchUser(texto);
        verify(interactor, times(1)).searchUser(texto);
    }

    @Test
    public void updateList() {
        User user = mock(User.class);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        this.presenter.updateList(users);
        verify(view, times(1)).updateList(users);
    }


}