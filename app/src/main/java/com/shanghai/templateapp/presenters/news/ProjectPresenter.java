package com.shanghai.templateapp.presenters.news;

import com.shanghai.templateapp.base.RxPresenter;
import com.shanghai.templateapp.base.connectors.news.FriendConnector;
import com.shanghai.templateapp.base.connectors.news.ProjectConnector;
import com.shanghai.templateapp.models.DataManager;
import com.shanghai.templateapp.models.entity.FriendEntity;
import com.shanghai.templateapp.models.entity.ProjectEntity;
import com.shanghai.templateapp.util.RxUtils;
import com.shanghai.templateapp.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/26 16:53
 */
public class ProjectPresenter extends RxPresenter<ProjectConnector.View> implements ProjectConnector.Presenter {

    private DataManager mDataManager;

    @Inject
    public ProjectPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(ProjectConnector.View view) {
        super.attachView(view);
    }

    @Override
    public void getProjectData() {
        addSubscribe(mDataManager.getProjectList()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<ProjectEntity>>(mView) {
                    @Override
                    public void onNext(List<ProjectEntity> entityList) {
                        mView.projectData(entityList);

                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stateError();
                    }
                }));
    }
}
