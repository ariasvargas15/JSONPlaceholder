package co.com.ceiba.mobile.pruebadeingreso.model.interactors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.database.UserDto;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.UserDB;
import co.com.ceiba.mobile.pruebadeingreso.model.rest.ApiService;
import co.com.ceiba.mobile.pruebadeingreso.presenter.users.IListUsers;
import retrofit2.Call;
import retrofit2.Response;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListUsersInteractorTest {

    @Mock
    IListUsers.Presenter presenter;
    @Mock
    UserDto dto;
    @Mock
    ApiService apiService;

    private ArrayList<User> users;
    @InjectMocks private ListUsersInteractor interactor;

    @Before
    public void init() {
        this.users = new ArrayList<>();
        this.interactor.setPresenter(presenter);
    }

    /*
    When there are no data in local storage and does the API request
     */
    @Test
    public void getUsersApi() {
        List<UserDB> u = new ArrayList<>();
        ApiService api = mock(ApiService.class);
        Call<List<User>> call = mock(Call.class);

        when(this.dto.getUsers()).thenReturn(u);
        when(this.apiService.getUsers()).thenReturn(call);

        this.interactor.getUsers();
        verify(this.apiService, times(1)).getUsers();
    }

    /*
    When there are data in local storage and does the query to database
     */
    @Test
    public void getUsersLocal(){
        UserDB db = mock(UserDB.class);
        List<UserDB> u = new ArrayList<>();
        u.add(db);
        when(this.dto.getUsers()).thenReturn(u);
        this.interactor.getUsers();
        verify(this.presenter, times(1)).showUsers(any());
    }


    @Test
    public void onResponse() {
        Call<List<User>> call = mock(Call.class);
        Response<List<User>> response = Response.success(users);
        this.interactor.onResponse(call, response);
        verify(this.presenter, times(1)).showUsers(any());
    }

    @Test
    public void searchUser() {
        this.interactor.searchUser(anyString());
        verify(this.presenter, times(1)).updateList(any());
    }
}