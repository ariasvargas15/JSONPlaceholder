package co.com.ceiba.mobile.pruebadeingreso.view.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.ceiba.mobile.pruebadeingreso.App;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.di.ActivityContext;
import co.com.ceiba.mobile.pruebadeingreso.di.ApplicationContext;
import co.com.ceiba.mobile.pruebadeingreso.di.component.ActivityComponent;
import co.com.ceiba.mobile.pruebadeingreso.di.component.DaggerActivityComponent;
import co.com.ceiba.mobile.pruebadeingreso.di.module.ActivityModule;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.posts.IListPosts;
import co.com.ceiba.mobile.pruebadeingreso.utilities.Tools;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.PostAdapter;

public class PostActivity extends Activity implements IListPosts.View {

    @Inject
    IListPosts.Presenter presenter;
    @Inject
    @ActivityContext
    Context context;

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.recyclerViewPostsResults)
    RecyclerView recycler;

    private AlertDialog dialog;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((App) getApplication()).getComponent())
                .build();
        activityComponent.inject(this);

        int id = 0;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            User u = (User) bundle.getSerializable("user");
            name.setText(u.getName());
            phone.setText(u.getPhone());
            email.setText(u.getEmail());
            id = u.getId();
        }

        dialog = Tools.showDialog(this).build();
        dialog.show();

        presenter.setView(this);
        presenter.getPosts(id);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void showPosts(ArrayList<Post> posts) {
        LinearLayoutManager linear = new LinearLayoutManager(context);
        recycler.setLayoutManager(linear);
        PostAdapter adapter = new PostAdapter(posts);
        recycler.setAdapter(adapter);
        dialog.dismiss();
    }
}
