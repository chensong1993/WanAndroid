package com.shanghai.templateapp.base.connectors.news;

import com.shanghai.templateapp.base.BasePresenter;
import com.shanghai.templateapp.base.BaseView;
import com.shanghai.templateapp.models.entity.FriendEntity;
import com.shanghai.templateapp.models.entity.ProjectEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/26 16:47
 */
public interface ProjectConnector {

    interface View extends BaseView {
        void projectData(List<ProjectEntity> entityList);

    }

    interface Presenter extends BasePresenter<ProjectConnector.View> {
        void getProjectData();
    }
}
