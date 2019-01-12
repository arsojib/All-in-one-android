package com.ailtl.allinoneandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by AR Sajib on 1/12/2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;

    private static final String DB_NAME = "UserAccounts";
    private static final int DB_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String TABLE_NOTIFICATIONS = "notifications";

    private static final String USER_ID = "user_id";
    private static final String USER_INSTITUTION_ID = "institution_id";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_NAME = "user_name";
    private static final String USER_PHOTO = "photo";
    private static final String USER_DESIGNATION = "designation";
    private static final String USER_TYPE = "user_type";
    private static final String USER_INSTITUTION_NAME = "institution_name";
    private static final String USER_NOTIFICATION = "has_notification";

    private static final String SEE_NOTIFICATION_LAST_TIME = "notification";
    private static final String SEE_NOTICE_LAST_TIME = "notice";
    private static final String SEE_NEWS_LAST_TIME = "news";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " ( " + USER_ID + " VARCHAR, " + USER_INSTITUTION_ID + " VARCHAR, " + USER_EMAIL + " TEXT, " +
            USER_PASSWORD + " VARCHAR, " + USER_NAME + " VARCHAR, " + USER_PHOTO + " VARCHAR, " + USER_DESIGNATION + " VARCHAR, " + USER_TYPE + " VARCHAR, " + USER_INSTITUTION_NAME + " VARCHAR, " + USER_NOTIFICATION + " VARCHAR " + ");";

    private static final String CREATE_TABLE_NOTIFICATIONS = "CREATE TABLE " + TABLE_NOTIFICATIONS + " ( " + USER_ID + " VARCHAR, " + SEE_NOTIFICATION_LAST_TIME + " BIGINT, " + SEE_NOTICE_LAST_TIME + " BIGINT, " + SEE_NEWS_LAST_TIME + " BIGINT " + ");";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
        sqLiteDatabase.execSQL(CREATE_TABLE_NOTIFICATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String userTableDrop = "DROP TABLE IF EXISTS " + TABLE_USERS;
        String notificationTableDrop = "DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS;
        sqLiteDatabase.execSQL(userTableDrop);
        sqLiteDatabase.execSQL(notificationTableDrop);
        onCreate(sqLiteDatabase);
    }

    public void addUser(String userId, String userInstitutionId, String userEmail, String userPassword, String userName, String userPhoto, String userDesignation,
                        String userType, String institutionName, String notification) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, userId);
        contentValues.put(USER_INSTITUTION_ID, userInstitutionId);
        contentValues.put(USER_EMAIL, userEmail);
//        contentValues.put(USER_PASSWORD, Util.encrypt(userPassword));
        contentValues.put(USER_NAME, userName);
        contentValues.put(USER_PHOTO, userPhoto);
        contentValues.put(USER_DESIGNATION, userDesignation);
        contentValues.put(USER_TYPE, userType);
        contentValues.put(USER_INSTITUTION_NAME, institutionName);
        contentValues.put(USER_NOTIFICATION, notification);
        db.insert(TABLE_USERS, null, contentValues);
        db.close();
    }
//
//    public UserData getSingleUsers(String id) {
//        UserData userData = null;
//        SQLiteDatabase db = this.getReadableDatabase();
//        String sql = "SELECT * FROM " + TABLE_USERS + " Where " + USER_ID + "=" + id + ";";
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                userData = new UserData(c.getString(0), c.getString(1), c.getString(2), (c.getString(3) == null ? null : Util.decrypt(c.getString(3))),
//                        c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9));
//            } while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//        return userData;
//    }
//
//    public ArrayList<UserData> getAllUsers() {
//        ArrayList<UserData> userDataArrayList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String sql = "SELECT * FROM " + TABLE_USERS + ";";
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                UserData userData = new UserData(c.getString(0), c.getString(1), c.getString(2), (c.getString(3) == null ? null : Util.decrypt(c.getString(3))),
//                        c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9));
//                userDataArrayList.add(userData);
//            } while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//        return userDataArrayList;
//    }
//
//    public ArrayList<UserData> getNotificationUsers(String instituteId, String type) {
//        ArrayList<UserData> userDataArrayList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String sql = "SELECT * FROM " + TABLE_USERS + " WHERE " + USER_INSTITUTION_ID + "=" + instituteId + " AND " + USER_TYPE + "=" + type + ";";
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                UserData userData = new UserData(c.getString(0), c.getString(1), c.getString(2), (c.getString(3) == null ? null : Util.decrypt(c.getString(3))),
//                        c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9));
//                userDataArrayList.add(userData);
//            } while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//        return userDataArrayList;
//    }

    public void deleteSingleUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, USER_ID + "=" + id, null);
        db.close();
    }

    public void deleteAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, null, null);
        db.close();
    }
//
//    public void setPassword(String id, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String encryptedPassword = (password == null ? null : Util.encrypt(password));
//        String updateSql = "UPDATE " + TABLE_USERS + " SET " + USER_PASSWORD + "=" + encryptedPassword + " WHERE " + USER_ID + "=" + id + ";";
//        db.execSQL(updateSql);
//        db.close();
//    }

    public void setNotificationStatus(String id, String notification) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateSql = "UPDATE " + TABLE_USERS + " SET " + USER_NOTIFICATION + "=" + notification + " WHERE " + USER_ID + "=" + id + ";";
        db.execSQL(updateSql);
        db.close();
    }

    public long getUserCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_USERS);
        db.close();
        return count;
    }

    public void userAlreadyExist(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, USER_ID + "=" + id, null);
        db.close();
    }
//
//    public void getNotificationTime(String id, Map<String, String> stringMap) {
//        long notificationTime = 0, noticeTime = 0, newsTime = 0;
//        SQLiteDatabase db = this.getReadableDatabase();
//        String sql = "SELECT * FROM " + TABLE_NOTIFICATIONS + " WHERE " + USER_ID + "=" + id + ";";
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) {
//            do {
//                notificationTime = c.getLong(1);
//                noticeTime = c.getLong(2);
//                newsTime = c.getLong(3);
//            } while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//        Util util = new Util();
//        stringMap.put("notification_previous_time", util.getNotificationDate(notificationTime));
//        stringMap.put("notice_previous_time", util.getNotificationDate(noticeTime));
//        stringMap.put("news_previous_time", util.getNotificationDate(newsTime));
//    }

    public void setNotificationUpdateTime(String id, long date) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateSql = "UPDATE " + TABLE_NOTIFICATIONS + " SET " + SEE_NOTIFICATION_LAST_TIME + "=" + date + " WHERE " + USER_ID + "=" + id + ";";
        db.execSQL(updateSql);
        db.close();
    }

    public void setNoticeUpdateTime(String id, long date) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateSql = "UPDATE " + TABLE_NOTIFICATIONS + " SET " + SEE_NOTICE_LAST_TIME + "=" + date + " WHERE " + USER_ID + "=" + id + ";";
        db.execSQL(updateSql);
        db.close();
    }

    public void setNewsUpdateTime(String id, long date) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateSql = "UPDATE " + TABLE_NOTIFICATIONS + " SET " + SEE_NEWS_LAST_TIME + "=" + date + " WHERE " + USER_ID + "=" + id + ";";
        db.execSQL(updateSql);
        db.close();
    }

    public void userNotificationAlreadyExist(String id) {
        try {
            SQLiteDatabase dbr = this.getReadableDatabase();
            SQLiteDatabase dbw = this.getWritableDatabase();
            String sql = "SELECT * FROM " + TABLE_NOTIFICATIONS + " WHERE " + USER_ID + "=" + id + ";";
            Cursor c = dbr.rawQuery(sql, null);
            if (!c.moveToFirst()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(USER_ID, id);
                contentValues.put(SEE_NOTIFICATION_LAST_TIME, "");
                contentValues.put(SEE_NOTICE_LAST_TIME, "");
                contentValues.put(SEE_NEWS_LAST_TIME, "");
                dbw.insert(TABLE_NOTIFICATIONS, null, contentValues);
                dbw.close();
            }
            c.close();
            dbr.close();
        } catch (SQLiteException ignored) {}

    }

}
