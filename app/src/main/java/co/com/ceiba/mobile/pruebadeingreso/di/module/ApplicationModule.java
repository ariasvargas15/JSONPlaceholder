package co.com.ceiba.mobile.pruebadeingreso.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.di.ApplicationContext;
import co.com.ceiba.mobile.pruebadeingreso.model.database.UserDto;
import co.com.ceiba.mobile.pruebadeingreso.model.rest.ApiService;
import co.com.ceiba.mobile.pruebadeingreso.model.rest.Endpoints;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    UserDto provideUserDto(UserDto userDto){
        return userDto;
    }

    @Provides
    @Singleton
    ApiService provideApiService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Endpoints.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

}
