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
import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.UserDB;
import co.com.ceiba.mobile.pruebadeingreso.presenter.posts.IListPosts;
import co.com.ceiba.mobile.pruebadeingreso.presenter.posts.ListPostsPresenter;
import co.com.ceiba.mobile.pruebadeingreso.presenter.users.IListUsers;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListPostsInteractorTest {

    @Mock
    IListPosts.Presenter presenter;

    private ArrayList<Post> posts;
    @InjectMocks
    private ListPostsInteractor interactor;

    @Before
    public void init() {
        this.posts = new ArrayList<>();
        this.interactor.setPresenter(presenter);
    }


    @Test
    public void onResponse() {
        Call<List<Post>> call = mock(Call.class);
        Response<List<Post>> response = Response.success(posts);
        this.interactor.onResponse(call, response);
        verify(this.presenter, times(1)).showPosts(any());
    }
}