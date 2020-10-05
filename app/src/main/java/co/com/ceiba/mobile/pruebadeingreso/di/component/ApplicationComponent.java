package co.com.ceiba.mobile.pruebadeingreso.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.App;
import co.com.ceiba.mobile.pruebadeingreso.di.ApplicationContext;
import co.com.ceiba.mobile.pruebadeingreso.di.module.ApplicationModule;
import co.com.ceiba.mobile.pruebadeingreso.model.rest.ApiService;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    ApiService apiService();
}
