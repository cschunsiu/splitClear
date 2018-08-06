package com.splitclear.cschunsiu.splitclear.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.splitclear.cschunsiu.splitclear.database.dao.GroupDao;
import com.splitclear.cschunsiu.splitclear.database.dao.MemberDao;
import com.splitclear.cschunsiu.splitclear.model.Groups;
import com.splitclear.cschunsiu.splitclear.model.Member;

@Database(entities = {Groups.class, Member.class}, version = 1, exportSchema = false)
public abstract class DatabaseConfig extends RoomDatabase {
    private static final String DATABASE_NAME = "split-clear-db";
    private static DatabaseConfig INSTANCE;
    public abstract GroupDao groupDao();
    public abstract MemberDao memberDao();

    public static DatabaseConfig getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseConfig.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseConfig.class, DATABASE_NAME)
                            //.addCallback(DatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

//    private static RoomDatabase.Callback DatabaseCallBack =
//            new RoomDatabase.Callback(){
//
//                @Override
//                public void onOpen (@NonNull SupportSQLiteDatabase db){
//                    super.onOpen(db);
//                }
//            };
}
