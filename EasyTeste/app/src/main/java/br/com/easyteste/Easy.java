package br.com.easyteste;

import android.content.Context;
import android.content.res.Configuration;

import com.orm.SchemaGenerator;
import com.orm.SugarApp;
import com.orm.SugarContext;
import com.orm.SugarDb;

/**
 * © Copyright 2017 Easy Teste.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class Easy extends SugarApp {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        SugarContext.init(getApplicationContext());

        // create table if not exists
        SchemaGenerator schemaGenerator = new SchemaGenerator(this);
        schemaGenerator.createDatabase(new SugarDb(this).getDB());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    public static Context getContext(){
        return context;
    }
}
