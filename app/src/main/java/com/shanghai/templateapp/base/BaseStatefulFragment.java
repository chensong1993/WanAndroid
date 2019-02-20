package com.shanghai.templateapp.base;

import android.os.Bundle;
import android.view.View;

import com.shanghai.templateapp.app.App;
import com.shanghai.templateapp.injections.components.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;
import nucleus5.presenter.Presenter;
import nucleus5.view.NucleusSupportFragment;


public abstract class BaseStatefulFragment<PresenterType extends Presenter> extends NucleusSupportFragment<PresenterType> {
//    @Nullable
//    @BindView(R.id.toolbar)
//    public Toolbar toolbarView;
//
//    @Nullable
//    @BindView(R.id.toolbar_title)
//    public TextView toolbarTitleView;

    private Unbinder mUnBinder;

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Icepick.saveInstanceState(this, bundle);
    }

    @Override
    public void onCreate(Bundle bundle) {
        injectorPresenter();
        super.onCreate(bundle);

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);

    }

    protected String getTitle() {
        return "";
    }

    protected AppComponent getAppComponent() {
        return ((App) getActivity().getApplication()).getAppComponent();
    }

    protected void injectorPresenter() {}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

}