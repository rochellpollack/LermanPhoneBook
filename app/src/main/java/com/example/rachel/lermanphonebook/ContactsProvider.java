//package com.example.rachel.lermanphonebook;
//
//import android.content.ContentProvider;
//import android.content.ContentUris;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.UriMatcher;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteQueryBuilder;
//import android.net.Uri;
//import android.text.TextUtils;
//
//import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
//
///**
// * Created by Rachel on 7/7/2015.
//*/
//
//
//public class ContactsProvider extends ContentProvider {
//
//    private static final String PROVIDER_NAME = "com.example.provider.Contacts";
//    public static final Uri CONTENT_URI = Uri.parse("content://"
//            + PROVIDER_NAME + "/contacts");
//
//    public static final String _ID = "_id";
//    public static final String FIRST_NAME = "firstName";
//    public static final String LAST_NAME = "lastName";
//    public static final String HOME_PHONE = "homePhone";
//    public static final String CELL_PHONE = "cellPhone";
//    public static final String STREET = "street";
//    public static final String CITY = "city";
//    public static final String ZIP = "zip";
//    public static final String EMAIL = "email";
//    public static final String BDAY = "bday";
//    public static final String FAMILY_BRANCH = "familyBranch";
//
//    public static final int CONTACTS = 1;
//    public static final int CONTACTS_ID = 2;
//
//    private static final UriMatcher uriMatcher;
//
//    static {
//        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//        uriMatcher.addURI(PROVIDER_NAME, "contacts", CONTACTS);
//        uriMatcher.addURI(PROVIDER_NAME, "contacts/#", CONTACTS_ID);
//    }
//
//    @Override
//    public boolean onCreate() {
//        Context context = getContext();
//        LermanContactsDatabase lcd = new LermanContactsDatabase(context);
//        db = lcd.getWritableDatabase();
//        return (db == null) ? false : true;
//    }
//
//    @Override
//    public Cursor query(Uri uri, String[] projection, String selection,
//                        String[] selectionArgs, String sortOrder) {
//        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
//        queryBuilder.setTables(TABLE_NAME);
//
//        // if trying to retrieve a particular student, then add where clause
//        if (uriMatcher.match(uri) == CONTACTS_ID) {
//            queryBuilder
//                    .appendWhere(_ID + " = " + uri.getPathSegments().get(1));
//        }
//
//        // when sortOrder isn't provider, set default sortOrder
//        if (sortOrder == null || sortOrder == "") {
//            sortOrder = _ID;
//        }
//
//        Cursor cursor = queryBuilder.query(db, projection, selection,
//                selectionArgs, null, null, sortOrder);
//
//        // register to notify a content URI for changes
//        cursor.setNotificationUri(getContext().getContentResolver(), uri);
//
//        return cursor;
//    }
//
//    @Override
//    public Uri insert(Uri uri, ContentValues values) {
//        long insertId = db.insert(TABLE_NAME, null, values);
//
//        // when insert is successful
//        if (insertId > 0) {
//            Uri insertUri = ContentUris.withAppendedId(CONTENT_URI, insertId);
//            getContext().getContentResolver().notifyChange(insertUri, null);
//            return insertUri;
//        }
//
//        throw new SQLException("Failed to insert new record");
//    }
//
//    @Override
//    public int update(Uri uri, ContentValues values, String selection,
//                      String[] selectionArgs) {
//
//        int rowsUpdated = 0;
//
//        switch (uriMatcher.match(uri)) {
//            case CONTACTS:
//                rowsUpdated = db.update(TABLE_NAME, values, selection,
//                        selectionArgs);
//                break;
//            case CONTACTS_ID:
//                rowsUpdated = db.update(
//                        TABLE_NAME,
//                        values,
//                        _ID
//                                + " = "
//                                + uri.getPathSegments().get(1)
//                                + (!TextUtils.isEmpty(selection) ? " AND ("
//                                + selection + ")" : ""), selectionArgs);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URI provide: " + uri);
//        }
//
//        getContext().getContentResolver().notifyChange(uri, null);
//        return rowsUpdated;
//    }
//
//    @Override
//    public int delete(Uri uri, String seletion, String[] selectionArgs) {
//
//        int rowsDeleted = 0;
//
//        switch (uriMatcher.match(uri)) {
//            case CONTACTS:
//                rowsDeleted = db.delete(TABLE_NAME, seletion, selectionArgs);
//                break;
//            case CONTACTS_ID:
//                rowsDeleted = db.delete(
//                        TABLE_NAME,
//                        _ID
//                                + " = "
//                                + uri.getPathSegments().get(1)
//                                + (!TextUtils.isEmpty(seletion) ? " AND ("
//                                + seletion + ")" : ""), selectionArgs);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URI provided: " + uri);
//        }
//
//        getContext().getContentResolver().notifyChange(uri, null);
//
//        return rowsDeleted;
//    }
//
//    @Override
//    public String getType(Uri uri) {
//        switch (uriMatcher.match(uri)) {
//            case CONTACTS:
//                return "vnd.android.cursor.dir/vnd.example.rachel/contacts";
//            case CONTACTS_ID:
//                return "vnd.android.cursor.item/vnd.example.rachel/contacts";
//            default:
//                throw new IllegalArgumentException("Unsupprted URI provided: "
//                        + uri);
//        }
//    }
//
//    // to help interact with database, we declare a helper class
//    private static final String DATABASE_NAME = "lermanContacts.db";
//    private static final String TABLE_NAME = "contacts";
//    private static final int DATABASE_VERSION = 1;
//
//
//    private SQLiteDatabase db;
//
//    public class LermanContactsDatabase extends SQLiteAssetHelper {
//
//
//        public LermanContactsDatabase(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//
//        }
//
//        public Cursor getContacts() {
//
//            SQLiteDatabase db = getReadableDatabase();
//            SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
//
//            String[] sqlSelect = {"_id ", "FirstName", "LastName"};
//            String sqlTables = "contacts";
//
//            qb.setTables(sqlTables);
//            Cursor c = qb.query(db, sqlSelect, null, null,
//                    null, null, null);
//
//            c.moveToFirst();
//            return c;
//
//        }
//
//
//    }
//}
