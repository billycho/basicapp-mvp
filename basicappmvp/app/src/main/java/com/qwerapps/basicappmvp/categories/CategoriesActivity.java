package com.qwerapps.basicappmvp.categories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.qwerapps.basicappmvp.MData.MDataActivity;
import com.qwerapps.basicappmvp.MData.MDataAdapter;
import com.qwerapps.basicappmvp.R;
import com.qwerapps.basicappmvp.data.Categories;
import com.qwerapps.basicappmvp.data.DatabaseHelper;
import com.qwerapps.basicappmvp.data.MData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesActivity extends AppCompatActivity implements CategoriesContract.View{

    RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    CategoryAdapter mAdapter;

    private DatabaseHelper databaseHelper;

    private CategoriesPresenter categoriesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        databaseHelper = new DatabaseHelper(this);
        categoriesPresenter = new CategoriesPresenter(databaseHelper,this);
        categoriesPresenter.loadCategories();

    }

    @Override
    public void showCategories(List<Categories> categories) {
        mAdapter = new CategoryAdapter(categories, this, categoriesPresenter);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showMData(int categoryId) {
        Intent intent  = new Intent(this,MDataActivity.class);
        intent.putExtra("categoryId",categoryId);
        startActivity(intent);
    }
}
