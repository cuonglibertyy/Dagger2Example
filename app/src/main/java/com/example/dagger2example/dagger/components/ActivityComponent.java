package com.example.dagger2example.dagger.components;


import com.example.dagger2example.dagger.modules.ActivityModule;
import com.example.dagger2example.dagger.scopes.ActivityScope;
import com.example.dagger2example.ui.account.AccountFragment;
import com.example.dagger2example.ui.bookcar.activity.BookCarActivity;
import com.example.dagger2example.ui.history.HistoryFragment;
import com.example.dagger2example.ui.history.historydetail.HistoryDetailActivity;
import com.example.dagger2example.ui.home.HomeFragment;
import com.example.dagger2example.ui.login.LoginActivity;
import com.example.dagger2example.ui.main.MainActivity;
import com.example.dagger2example.ui.notification.NotificationFragment;
import com.example.dagger2example.ui.splash.SplashActivity;
import com.example.dagger2example.ui.wallet.WalletFragment;

import dagger.Component;

@ActivityScope
@Component(dependencies ={ ApplicationComponent.class
},modules =
        {ActivityModule.class
        })
public interface ActivityComponent {

    LoginActivity inject(LoginActivity loginActivity);

    SplashActivity inject(SplashActivity splashActivity);

    void inject(AccountFragment accountFragment);

    void inject(BookCarActivity homeFragment);

    void inject(HistoryFragment historyFragment);

    void inject(NotificationFragment notificationFragment);

    void inject(MainActivity mainActivity);

    void inject(WalletFragment walletFragment);

    void inject(HistoryDetailActivity historyDetailActivity);

    void inject(HomeFragment homeFragment);
}
