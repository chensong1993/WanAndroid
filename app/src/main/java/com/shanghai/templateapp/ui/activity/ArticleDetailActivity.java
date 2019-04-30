package com.shanghai.templateapp.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.DefaultWebClient;
import com.shanghai.templateapp.R;
import com.shanghai.templateapp.app.App;
import com.shanghai.templateapp.app.Constants;
import com.shanghai.templateapp.base.BaseActivity;
import com.shanghai.templateapp.base.components.RxBus;
import com.shanghai.templateapp.base.connectors.collect.CollectConnector;
import com.shanghai.templateapp.models.entity.rxbus.CollectEvent;
import com.shanghai.templateapp.models.entity.rxbus.StarInfoEvent;
import com.shanghai.templateapp.presenters.collect.CollectPresenter;
import com.shanghai.templateapp.ui.activity.login.LoginActivity;
import com.shanghai.templateapp.util.RxUtils;


import butterknife.BindView;
import butterknife.OnClick;

public class ArticleDetailActivity extends BaseActivity<CollectPresenter> implements CollectConnector.View {
    AgentWeb mAgentWeb;
    String mWebURL;
    @BindView(R.id.ll_Article)
    LinearLayout mLinArticle;
    @BindView(R.id.tv_star)
    TextView mTvStar;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    String userName, title, author, link;
    int articleId;
    boolean isCollect = false;
    Animation mShakeAnim;

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initEventAndData() {
//        ImmersionBar.with(this)
//                .fitsSystemWindows(true)
//                .statusBarDarkFont(true, 0.5f)
//                .init();
        mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_view);
        Bundle bundle = getIntent().getBundleExtra(Constants.ARTICLE_STAR);
        articleId = bundle.getInt(Constants.ARTICLE_STAR_ID, 0);
        mWebURL = bundle.getString(Constants.ARTICLE_STAR_LINK, "");
        title = bundle.getString(Constants.ARTICLE_STAR_TITLE, "");
        isCollect = bundle.getBoolean(Constants.ARTICLE_STAR_COLLECT,false);
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(mLinArticle, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(ContextCompat.getColor(this, R.color.black), 2)
                //.setIndicatorColorWithHeight(-1, 3)
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setMainFrameErrorView(R.layout.item_webview_err, -1)
                //打开其他应用时，弹窗咨询用户是否前往其他应用
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(mWebURL);

        if (isCollect) {
            mTvStar.setText("已收藏");
        } else {
            mTvStar.setText("收藏");
        }
        mTvTitle.setText(title);

    }

    @OnClick(R.id.tv_back)
    void onGoBack() {
        if (!mAgentWeb.back()) {
            this.finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAgentWeb.getWebLifeCycle().onResume();
        userName = App.kv.decodeString(Constants.USER_NAME, null);

    }

    @Override
    public void starSucceed() {
        isCollect = true;
        RxBus.getDefault().post(new CollectEvent(true));
        RxBus.getDefault().post(new StarInfoEvent(articleId, true));
        ToastUtils.show("收藏成功");
        mTvStar.setText("已收藏");
    }

    @Override
    public void stateError() {
        super.stateError();
        mTvStar.startAnimation(mShakeAnim);
        ToastUtils.show("收藏失败");
    }

    @Override
    public void unStarSucceed() {
        isCollect = false;
        RxBus.getDefault().post(new StarInfoEvent(articleId, false));
        RxBus.getDefault().post(new CollectEvent(true));
        ToastUtils.show("取消收藏成功");
        mTvStar.setText("收藏");
    }

    @Override
    public void unErr() {
        mTvStar.startAnimation(mShakeAnim);
        ToastUtils.show("取消收藏失败");
    }


    @OnClick(R.id.tv_star)
    void onStat() {
        if (userName != null) {
            if (isCollect) {
                mPresenter.getUnStar(articleId);
            } else {
                mPresenter.getStar(articleId);
            }
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }

    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
        App.refWatcher.watch(this);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
