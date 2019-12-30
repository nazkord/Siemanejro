package com.siemanejro.siemanejroproject;

import android.content.Context;

public class SiemanejroApp extends android.app.Application {
    private static SiemanejroApp instance;

    public SiemanejroApp() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }
}
