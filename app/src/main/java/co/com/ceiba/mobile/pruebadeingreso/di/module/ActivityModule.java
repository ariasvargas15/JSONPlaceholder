package co.com.ceiba.mobile.pruebadeingreso.di.module;

import android.app.Activity;
import android.content.Context;

import co.com.ceiba.mobile.pruebadeingreso.di.ActivityContext;
import co.com.ceiba.mobile.pruebadeingreso.di.PerActivity;
import co.com.ceiba.mobile.pruebadeingreso.model.interactors.ListPostsInteractor;
import co.com.ceiba.mobile.pruebadeingreso.model.interactors.ListUsersInteractor;
import co.com.ceiba.mobile.pruebadeingreso.presenter.posts.IListPosts;
import co.com.ceiba.mobile.pruebadeingreso.presenter.posts.ListPostsPresenter;
import co.com.ceiba.mobile.pruebadeingreso.presenter.users.IListUsers;
import co.com.ceiba.mobile.pruebadeingreso.presenter.users.ListUsersPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    IListUsers.Presenter provideUserPresenter(ListUsersPresenter presenter){
        return  presenter;
    }

    @Provides
    IListUsers.Interactor provideUserInteractor(ListUsersInteractor interactor){
        return  interactor;
    }

    @Provides
    @PerActivity
    IListPosts.Presenter providePostsPresenter(ListPostsPresenter presenter){
        return  presenter;
    }

    @Provides
    IListPosts.Interactor providePostsInteractor(ListPostsInteractor interactor){
        return  interactor;
    }

}
