package co.com.ceiba.mobile.pruebadeingreso.di.component;

import co.com.ceiba.mobile.pruebadeingreso.di.PerActivity;
import co.com.ceiba.mobile.pruebadeingreso.di.module.ActivityModule;
import co.com.ceiba.mobile.pruebadeingreso.view.activities.MainActivity;
import co.com.ceiba.mobile.pruebadeingreso.view.activities.PostActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(PostActivity activity);
}

