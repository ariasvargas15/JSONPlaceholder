package co.com.ceiba.mobile.pruebadeingreso.view.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.ceiba.mobile.pruebadeingreso.App;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.di.ActivityContext;
import co.com.ceiba.mobile.pruebadeingreso.di.component.ActivityComponent;
import co.com.ceiba.mobile.pruebadeingreso.di.component.DaggerActivityComponent;
import co.com.ceiba.mobile.pruebadeingreso.di.module.ActivityModule;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.users.IListUsers;
import co.com.ceiba.mobile.pruebadeingreso.utilities.Tools;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.UserAdapter;

public class MainActivity extends Activity implements IListUsers.View {


    @Inject
    IListUsers.Presenter presenter;
    @Inject
    @ActivityContext
    Context context;

    @BindView(R.id.editTextSearch)
    EditText search;
    @BindView(R.id.recyclerViewSearchResults)
    RecyclerView recycler;
    @BindView(R.id.content)
    RelativeLayout content;

    private UserAdapter adapter;
    private ArrayList<User> users;


    private View empty;
    private AlertDialog dialog;

    private ActivityComponent activityComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((App) getApplication()).getComponent())
                .build();
        activityComponent.inject(this);

        empty = getLayoutInflater().inflate(R.layout.empty_view, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_TOP, R.id.recyclerViewSearchResults);
        empty.setLayoutParams(params);

        dialog = Tools.showDialog(this).build();
        dialog.show();

        presenter.setView(this);
        presenter.getUsers();
        makeTextWatcher();
    }

    private void makeTextWatcher() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.searchUser(editable.toString());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void showUsers(ArrayList<User> users) {
        this.users = users;
        LinearLayoutManager linear = new LinearLayoutManager(context);
        recycler.setLayoutManager(linear);
        adapter = new UserAdapter(users);
        recycler.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void updateList(ArrayList<User> users) {
        content.removeView(empty);
        if (users != null) {
            adapter.updateList(users);
            if (users.isEmpty()) {
                content.addView(empty);
            }
        }

    }


}