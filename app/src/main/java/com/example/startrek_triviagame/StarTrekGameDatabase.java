package com.example.startrek_triviagame;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This file represents the database for the application.
 *
 * @author Nicole Al-Sabah
 * Date: December 01, 2023
 */
@Database(entities = {User.class, TriviaQuestions.class, ScoreHistory.class}, version = 2, exportSchema = false)
public abstract class StarTrekGameDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "StarTrekGameDatabase";

    public abstract StarTrekGameDao starTrekGameDao();

    private static volatile StarTrekGameDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);



    public static StarTrekGameDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StarTrekGameDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StarTrekGameDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

//    public static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//
//            // If you want to keep data through app restarts,
//            // comment out the following block
//            databaseWriteExecutor.execute(() -> {
//                // Populate the database in the background.
//                // If you want to start with more words, just add them.
//                StarTrekGameDao dao = INSTANCE.starTrekGameDao();
//
//                User user = new User("admin", "admin", true);
//                dao.insertUser(user);
//            });
//        }
//    };
}
