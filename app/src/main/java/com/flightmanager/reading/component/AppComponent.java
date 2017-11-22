package com.flightmanager.reading.component;


import android.content.Context;

import com.flightmanager.reading.api.BookApi;
import com.flightmanager.reading.module.AppModule;
import com.flightmanager.reading.module.BookApiModule;

import dagger.Component;

@Component(modules={AppModule.class, BookApiModule.class})
public interface AppComponent {

    Context getContext();

    BookApi getReaderApi();
}
