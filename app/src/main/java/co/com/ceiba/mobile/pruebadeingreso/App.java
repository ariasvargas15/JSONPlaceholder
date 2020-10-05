package co.com.ceiba.mobile.pruebadeingreso;

import android.app.Application;

import co.com.ceiba.mobile.pruebadeingreso.di.component.ApplicationComponent;
import co.com.ceiba.mobile.pruebadeingreso.di.component.DaggerApplicationComponent;
import co.com.ceiba.mobile.pruebadeingreso.di.module.ApplicationModule;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

}