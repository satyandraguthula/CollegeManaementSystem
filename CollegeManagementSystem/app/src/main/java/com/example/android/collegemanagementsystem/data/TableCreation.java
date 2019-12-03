package com.example.android.collegemanagementsystem.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by satyandra on 16/3/15.
 */
public class TableCreation extends SQLiteOpenHelper {
    private static final String DB_name="Students.db";
    private static final int DB_Version=1;
    public TableCreation(Context context)
    {
        super(context,DB_name,null,DB_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("DROP TABLE IF EXISTS " + TableStructure.SubjectEntry.TableName);
        db.execSQL("DROP TABLE IF EXISTS " + TableStructure.Marks.TableName);
        db.execSQL("DROP TABLE IF EXISTS " + TableStructure.Teacsub.TableName);
        db.execSQL("DROP TABLE IF EXISTS " + TableStructure.TeacTT.TableName);*/
        final String Subject_Entry_Creation_Query="create table "+ TableStructure.SubjectEntry.TableName+"("+
                TableStructure.SubjectEntry._ID+" INTEGER PRIMARY KEY, "+TableStructure.SubjectEntry.Column_subject+
                " TEXT NOT NULL, "+ TableStructure.SubjectEntry.Column_type+" TEXT NOT NULL, "+ TableStructure.SubjectEntry.Column_teacher+
                " TEXT NOT NULL);";
        Log.e("tab c reated","Subject");
        final String Marks_Entry_Creation_Query="CREATE TABLE "+ TableStructure.Marks.TableName+"("+ TableStructure.Marks._ID+
                " INTEGER, "+TableStructure.Marks.Column_ut1+" INTEGER, "+ TableStructure.Marks.Column_ut2+" INTEGER,"+"FOREIGN KEY ("+
                TableStructure.Marks._ID+") REFERENCES "+ TableStructure.SubjectEntry.TableName+"("+ TableStructure.SubjectEntry._ID+")); ";
        final String TT_Creation_Query="CREATE TABLE "+ TableStructure.TTable.TableName+"("+ TableStructure.TTable._ID+" INT PRIMARY KEY,"+ TableStructure.TTable.Day+" TEXT NOT " +
                "NULL, "+ TableStructure.TTable.Slot1+" INTEGER, "+ TableStructure.TTable.Slot2+" INTEGER, "+ TableStructure.TTable.Slot3+" INTEGER, "+
                TableStructure.TTable.Slot4+" INTEGER, "+ TableStructure.TTable.Slot5+" INTEGER, "+ TableStructure.TTable.Slot6+
                " INTEGER, "+ TableStructure.TTable.Slot7+" INTEGER, "+ TableStructure.TTable.Slot8+" INTEGER, FOREIGN KEY("+
                TableStructure.TTable.Slot1+") REFERENCES "+ TableStructure.SubjectEntry.TableName+"("+ TableStructure.SubjectEntry._ID+
                "), FOREIGN KEY("+ TableStructure.TTable.Slot2+") REFERENCES "+ TableStructure.SubjectEntry.TableName+"("+ TableStructure.SubjectEntry._ID+"), " +
                "FOREIGN KEY("+ TableStructure.TTable.Slot3+") REFERENCES "+ TableStructure.SubjectEntry.TableName+"("+ TableStructure.SubjectEntry._ID+")" +
                ", FOREIGN KEY("+ TableStructure.TTable.Slot4+") REFERENCES "+ TableStructure.SubjectEntry.TableName+"("+ TableStructure.SubjectEntry._ID+
                "), FOREIGN KEY("+ TableStructure.TTable.Slot5+") REFERENCES "+ TableStructure.SubjectEntry.TableName+"("+ TableStructure.SubjectEntry._ID+")"+
                ", FOREIGN KEY("+ TableStructure.TTable.Slot6+") REFERENCES "+ TableStructure.SubjectEntry.TableName+"("+ TableStructure.SubjectEntry._ID+")" +
                ", FOREIGN KEY("+ TableStructure.TTable.Slot7+") REFERENCES "+ TableStructure.SubjectEntry.TableName+"("+ TableStructure.SubjectEntry._ID+")" +
                ", FOREIGN KEY("+TableStructure.TTable.Slot8+") REFERENCES "+ TableStructure.SubjectEntry.TableName+"("+ TableStructure.SubjectEntry._ID+"))";
        Log.e("creating","teacher");
        final String Teacsub_Query="CREATE TABLE "+ TableStructure.Teacsub.TableName+"("+ TableStructure.Teacsub._ID+
                " INT PRIMARY KEY, "+ TableStructure.Teacsub.TSubject+" TEXT NOT NULL, "+ TableStructure.Teacsub.Year+" TEXT NOT NULL,"+
                TableStructure.Teacsub.Tclass+" TEXT NOT NULL)";
        final String TeacTT_Query="CREATE TABLE "+ TableStructure.TeacTT.TableName+"("+ TableStructure.TeacTT._ID+" INTEGER PRIMARY KEY, " +
                TableStructure.TeacTT.Day+" TEXT NOT NULL, "+ TableStructure.TeacTT.Slot1+" INTEGER, "+ TableStructure.TeacTT.Slot2+
                " INTEGER, "+ TableStructure.TeacTT.Slot3+" INTEGER, "+ TableStructure.TeacTT.Slot4+" INTEGER, "+ TableStructure.TeacTT.Slot5+
                " INTEGER, "+ TableStructure.TeacTT.Slot6+" INTEGER, "+ TableStructure.TeacTT.Slot7+" INTEGER, "+ TableStructure.TeacTT.Slot8+
                " INTEGER, FOREIGN KEY("+TableStructure.TeacTT.Slot1+") REFERENCES "+ TableStructure.Teacsub.TableName+"("+ TableStructure.Teacsub._ID+"),"+
                " FOREIGN KEY("+TableStructure.TeacTT.Slot2+") REFERENCES "+ TableStructure.Teacsub.TableName+"("+ TableStructure.Teacsub._ID+"),"+
                " FOREIGN KEY("+TableStructure.TeacTT.Slot3+") REFERENCES "+ TableStructure.Teacsub.TableName+"("+ TableStructure.Teacsub._ID+"),"+
                " FOREIGN KEY("+TableStructure.TeacTT.Slot4+") REFERENCES "+ TableStructure.Teacsub.TableName+"("+ TableStructure.Teacsub._ID+"),"+
                " FOREIGN KEY("+TableStructure.TeacTT.Slot5+") REFERENCES "+ TableStructure.Teacsub.TableName+"("+ TableStructure.Teacsub._ID+"),"+
                " FOREIGN KEY("+TableStructure.TeacTT.Slot6+") REFERENCES "+ TableStructure.Teacsub.TableName+"("+ TableStructure.Teacsub._ID+"),"+
                " FOREIGN KEY("+TableStructure.TeacTT.Slot7+") REFERENCES "+ TableStructure.Teacsub.TableName+"("+ TableStructure.Teacsub._ID+"),"+
                " FOREIGN KEY("+TableStructure.TeacTT.Slot8+") REFERENCES "+ TableStructure.Teacsub.TableName+"("+ TableStructure.Teacsub._ID+")"+
                ")";
        final String se_query="CREATE TABLE se(date TEXT NOT NULL,Time TEXT NOT NULL,status TEXT NOT NULL)";
        final String spcc_query="CREATE TABLE spcc(date TEXT NOT NULL,Time TEXT NOT NULL,status TEXT NOT NULL)";
        db.execSQL(Teacsub_Query);
        db.execSQL(TeacTT_Query);
        Log.e("creating","seatt");
        db.execSQL(se_query);
        db.execSQL(spcc_query);
        ContentValues values=new ContentValues();
        values.put("date","12.04.2015");
        values.put("Time","3.30");
        values.put("status","yes");
        db.insert("se", null,values);
        values.clear();
        values.put("date","13.04.2015");
        values.put("Time","2.30");
        values.put("status","yes");
        db.insert("se", null,values);
        values.clear();
        values.put("date","14.04.2015");
        values.put("Time","1.30");
        values.put("status","yes");
        db.insert("se", null,values);
        values.clear();
        values.put("date","12.04.2015");
        values.put("Time","9.00");
        values.put("status","yes");
        db.insert("spcc", null,values);
        values.clear();
        values.put("date","13.04.2015");
        values.put("Time","10.00");
        values.put("status","yes");
        db.insert("spcc", null,values);
        values.clear();
        values.put("date","14.04.2015");
        values.put("Time","10.00");
        values.put("status","yes");
        db.insert("spcc", null,values);
        values.clear();
        db.execSQL(Subject_Entry_Creation_Query);
        db.execSQL(Marks_Entry_Creation_Query);
        db.execSQL(TT_Creation_Query);
        values.put(TableStructure.SubjectEntry._ID,1);
        values.put(TableStructure.SubjectEntry.Column_subject,"MCC");
        values.put(TableStructure.SubjectEntry.Column_type,"Theory");
        values.put(TableStructure.SubjectEntry.Column_teacher,"Asmita Shejale");
        long rowid=db.insert(TableStructure.SubjectEntry.TableName,null,values);
        values.clear();
        ContentValues values1=new ContentValues();
        values1.put(TableStructure.SubjectEntry._ID,2);
        values1.put(TableStructure.SubjectEntry.Column_subject,"MCC");
        values1.put(TableStructure.SubjectEntry.Column_type,"Practical");
        values1.put(TableStructure.SubjectEntry.Column_teacher,"Asmita Shejale");
        rowid=db.insert(TableStructure.SubjectEntry.TableName,null,values1);
        ContentValues values2=new ContentValues();
        values2.put(TableStructure.SubjectEntry._ID,3);
        values2.put(TableStructure.SubjectEntry.Column_subject,"DDBMS");
        values2.put(TableStructure.SubjectEntry.Column_type,"Theory");
        values2.put(TableStructure.SubjectEntry.Column_teacher,"Aparna Bannore");
        rowid=db.insert(TableStructure.SubjectEntry.TableName,null,values2);
        ContentValues values3=new ContentValues();
        values3.put(TableStructure.SubjectEntry._ID,4);
        values3.put(TableStructure.SubjectEntry.Column_subject,"DDBMS");
        values3.put(TableStructure.SubjectEntry.Column_type,"Practical");
        values3.put(TableStructure.SubjectEntry.Column_teacher,"Aparna Bannore");
        rowid=db.insert(TableStructure.SubjectEntry.TableName,null,values3);
        ContentValues values4=new ContentValues();
        values4.put(TableStructure.SubjectEntry._ID,5);
        values4.put(TableStructure.SubjectEntry.Column_subject,"SPCC");
        values4.put(TableStructure.SubjectEntry.Column_type,"Theory");
        values4.put(TableStructure.SubjectEntry.Column_teacher,"Varsha Patil");
        rowid=db.insert(TableStructure.SubjectEntry.TableName,null,values4);
        ContentValues values5=new ContentValues();
        values5.put(TableStructure.SubjectEntry._ID,6);
        values5.put(TableStructure.SubjectEntry.Column_subject,"SPCC");
        values5.put(TableStructure.SubjectEntry.Column_type,"Practical");
        values5.put(TableStructure.SubjectEntry.Column_teacher,"Varsha Patil");
        rowid=db.insert(TableStructure.SubjectEntry.TableName,null,values5);
        ContentValues values6=new ContentValues();
        values6.put(TableStructure.SubjectEntry._ID,7);
        values6.put(TableStructure.SubjectEntry.Column_subject,"SE");
        values6.put(TableStructure.SubjectEntry.Column_type,"Theory");
        values6.put(TableStructure.SubjectEntry.Column_teacher,"Suvarna");
        rowid=db.insert(TableStructure.SubjectEntry.TableName,null,values6);
        ContentValues values7=new ContentValues();
        values7.put(TableStructure.SubjectEntry._ID,8);
        values7.put(TableStructure.SubjectEntry.Column_subject,"SE");
        values7.put(TableStructure.SubjectEntry.Column_type,"Practical");
        values7.put(TableStructure.SubjectEntry.Column_teacher,"Sarita L.R.");
        rowid=db.insert(TableStructure.SubjectEntry.TableName,null,values7);
        ContentValues values8=new ContentValues();
        values8.put(TableStructure.SubjectEntry._ID,9);
        values8.put(TableStructure.SubjectEntry.Column_subject,"PM");
        values8.put(TableStructure.SubjectEntry.Column_type,"Theory");
        values8.put(TableStructure.SubjectEntry.Column_teacher,"Roopal");
        rowid=db.insert(TableStructure.SubjectEntry.TableName,null,values8);
        ContentValues values9=new ContentValues();
        values9.put(TableStructure.SubjectEntry._ID,10);
        values9.put(TableStructure.SubjectEntry.Column_subject,"PM");
        values9.put(TableStructure.SubjectEntry.Column_type,"Practical");
        values9.put(TableStructure.SubjectEntry.Column_teacher,"Kranti");
        rowid=db.insert(TableStructure.SubjectEntry.TableName,null,values9);
        values.put(TableStructure.SubjectEntry._ID,11);
        values.put(TableStructure.SubjectEntry.Column_subject,"NPL");
        values.put(TableStructure.SubjectEntry.Column_type,"Practical");
        values.put(TableStructure.SubjectEntry.Column_teacher,"Sir");
        rowid=db.insert(TableStructure.SubjectEntry.TableName,null,values);
        values.clear();
        ContentValues test=new ContentValues();
        test.put(TableStructure.Marks._ID,1);
        test.put(TableStructure.Marks.Column_ut1,15);
        test.put(TableStructure.Marks.Column_ut2,13);
        db.insert(TableStructure.Marks.TableName,null,test);
        test.clear();
        test.put(TableStructure.Marks._ID,3);
        test.put(TableStructure.Marks.Column_ut1,12);
        db.insert(TableStructure.Marks.TableName,null,test);
        test.clear();
        test.put(TableStructure.Marks._ID,5);
        test.put(TableStructure.Marks.Column_ut1,18);
        db.insert(TableStructure.Marks.TableName,null,test);
        test.clear();
        test.put(TableStructure.Marks._ID,7);
        test.put(TableStructure.Marks.Column_ut1,16);
        test.put(TableStructure.Marks.Column_ut2,14);
        db.insert(TableStructure.Marks.TableName,null,test);
        test.clear();
        ContentValues ttvalues=new ContentValues();
        ttvalues.put(TableStructure.TTable._ID,1);
        ttvalues.put(TableStructure.TTable.Day,"Monday");
        ttvalues.put(TableStructure.TTable.Slot1,1);
        ttvalues.put(TableStructure.TTable.Slot2,7);
        ttvalues.put(TableStructure.TTable.Slot3,4);
        ttvalues.put(TableStructure.TTable.Slot4,4);
        ttvalues.put(TableStructure.TTable.Slot5,5);
        ttvalues.put(TableStructure.TTable.Slot6,3);
        ttvalues.put(TableStructure.TTable.Slot7,1);
        db.insert(TableStructure.TTable.TableName,null,ttvalues);
        ttvalues.clear();
        ttvalues.put(TableStructure.TTable._ID, 2);
        ttvalues.put(TableStructure.TTable.Day,"Tuesday");
        ttvalues.put(TableStructure.TTable.Slot1,9);
        ttvalues.put(TableStructure.TTable.Slot2,3);
        ttvalues.put(TableStructure.TTable.Slot3,5);
        ttvalues.put(TableStructure.TTable.Slot4,1);
        ttvalues.put(TableStructure.TTable.Slot5,11);
        ttvalues.put(TableStructure.TTable.Slot6,11);
        ttvalues.put(TableStructure.TTable.Slot7,7);
        db.insert(TableStructure.TTable.TableName, null, ttvalues);
        ttvalues.clear();
        ttvalues.put(TableStructure.TTable._ID, 3);
        ttvalues.put(TableStructure.TTable.Day,"Wednesday");
        ttvalues.put(TableStructure.TTable.Slot3,11);
        ttvalues.put(TableStructure.TTable.Slot4,11);
        ttvalues.put(TableStructure.TTable.Slot5,1);
        ttvalues.put(TableStructure.TTable.Slot6,3);
        ttvalues.put(TableStructure.TTable.Slot7,7);
        db.insert(TableStructure.TTable.TableName, null, ttvalues);
        ttvalues.clear();
        ttvalues.put(TableStructure.TTable._ID, 4);
        ttvalues.put(TableStructure.TTable.Day,"Thursday");
        ttvalues.put(TableStructure.TTable.Slot1,10);
        ttvalues.put(TableStructure.TTable.Slot2,10);
        ttvalues.put(TableStructure.TTable.Slot3,5);
        ttvalues.put(TableStructure.TTable.Slot4,3);
        ttvalues.put(TableStructure.TTable.Slot5,2);
        ttvalues.put(TableStructure.TTable.Slot6,2);
        ttvalues.put(TableStructure.TTable.Slot7,7);
        db.insert(TableStructure.TTable.TableName, null, ttvalues);
        ttvalues.clear();
        ttvalues.put(TableStructure.TTable._ID, 5);
        ttvalues.put(TableStructure.TTable.Day,"Friday");
        ttvalues.put(TableStructure.TTable.Slot1,8);
        ttvalues.put(TableStructure.TTable.Slot2,8);
        ttvalues.put(TableStructure.TTable.Slot3,5);
        ttvalues.put(TableStructure.TTable.Slot4,5);
        ttvalues.put(TableStructure.TTable.Slot5,1);
        ttvalues.put(TableStructure.TTable.Slot6,7);
        ttvalues.put(TableStructure.TTable.Slot7,3);
        db.insert(TableStructure.TTable.TableName, null, ttvalues);
        ttvalues.clear();
        values.put(TableStructure.Teacsub._ID,1);
        values.put(TableStructure.Teacsub.TSubject,"SPCC");
        values.put(TableStructure.Teacsub.Year,"TE");
        values.put(TableStructure.Teacsub.Tclass,"C");
        db.insert(TableStructure.Teacsub.TableName, null, values);
        values.clear();
        values.put(TableStructure.Teacsub._ID, 2);
        values.put(TableStructure.Teacsub.TSubject,"SPCC");
        values.put(TableStructure.Teacsub.Year,"TE");
        values.put(TableStructure.Teacsub.Tclass,"C1");
        db.insert(TableStructure.Teacsub.TableName, null, values);
        values.clear();
        values.put(TableStructure.Teacsub._ID, 3);
        values.put(TableStructure.Teacsub.TSubject,"SPCC");
        values.put(TableStructure.Teacsub.Year,"TE");
        values.put(TableStructure.Teacsub.Tclass,"C3");
        db.insert(TableStructure.Teacsub.TableName, null, values);
        values.clear();
        values.put(TableStructure.Teacsub._ID, 4);
        values.put(TableStructure.Teacsub.TSubject,"COA");
        values.put(TableStructure.Teacsub.Year,"SE");
        values.put(TableStructure.Teacsub.Tclass,"D");
        db.insert(TableStructure.Teacsub.TableName, null, values);
        values.clear();
        values.put(TableStructure.Teacsub._ID, 5);
        values.put(TableStructure.Teacsub.TSubject,"COA");
        values.put(TableStructure.Teacsub.Year,"SE");
        values.put(TableStructure.Teacsub.Tclass,"D1");
        db.insert(TableStructure.Teacsub.TableName, null, values);
        values.clear();
        values.put(TableStructure.Teacsub._ID, 6);
        values.put(TableStructure.Teacsub.TSubject,"COA");
        values.put(TableStructure.Teacsub.Year,"SE");
        values.put(TableStructure.Teacsub.Tclass,"D3");
        db.insert(TableStructure.Teacsub.TableName, null, values);
        values.clear();
        values.put(TableStructure.Teacsub._ID, 7);
        values.put(TableStructure.Teacsub.TSubject,"SE");
        values.put(TableStructure.Teacsub.Year,"TE");
        values.put(TableStructure.Teacsub.Tclass,"C");
        db.insert(TableStructure.Teacsub.TableName, null, values);
        values.clear();
        values.put(TableStructure.Teacsub._ID, 8);
        values.put(TableStructure.Teacsub.TSubject,"SE");
        values.put(TableStructure.Teacsub.Year,"TE");
        values.put(TableStructure.Teacsub.Tclass,"C2");
        db.insert(TableStructure.Teacsub.TableName, null, values);
        values.clear();
        values.put(TableStructure.TeacTT._ID, 1);
        values.put(TableStructure.TeacTT.Day,"Monday");
        values.put(TableStructure.TeacTT.Slot1,6);
        values.put(TableStructure.TeacTT.Slot2,6);
        values.put(TableStructure.TeacTT.Slot4,4);
        values.put(TableStructure.TeacTT.Slot5,1);
        values.put(TableStructure.TeacTT.Slot6,2);
        values.put(TableStructure.TeacTT.Slot7,2);
        db.insert(TableStructure.TeacTT.TableName,null,values);
        values.clear();
        values.put(TableStructure.TeacTT._ID, 2);
        values.put(TableStructure.TeacTT.Day,"Tuesday");
        values.put(TableStructure.TeacTT.Slot1,1);
        values.put(TableStructure.TeacTT.Slot4,4);
        values.put(TableStructure.TeacTT.Slot5,1);
        db.insert(TableStructure.TeacTT.TableName,null,values);
        values.clear();
        values.put(TableStructure.TeacTT._ID, 3);
        values.put(TableStructure.TeacTT.Day,"Wednesday");
        values.put(TableStructure.TeacTT.Slot1,5);
        values.put(TableStructure.TeacTT.Slot2,5);
        values.put(TableStructure.TeacTT.Slot4,1);
        values.put(TableStructure.TeacTT.Slot5,4);
        values.put(TableStructure.TeacTT.Slot6,3);
        values.put(TableStructure.TeacTT.Slot7,3);
        db.insert(TableStructure.TeacTT.TableName,null,values);
        values.clear();
        values.put(TableStructure.TeacTT._ID, 4);
        values.put(TableStructure.TeacTT.Day,"Thursday");
        values.put(TableStructure.TeacTT.Slot1,1);
        values.put(TableStructure.TeacTT.Slot5,4);
        db.insert(TableStructure.TeacTT.TableName,null,values);
        values.clear();
        values.put(TableStructure.TeacTT._ID,5);
        values.put(TableStructure.TeacTT.Day,"Friday");
        values.put(TableStructure.TeacTT.Slot1,4);
        values.put(TableStructure.TeacTT.Slot2,4);
        values.put(TableStructure.TeacTT.Slot6,1);
        values.put(TableStructure.TeacTT.Slot7,1);
        db.insert(TableStructure.TeacTT.TableName,null,values);
        values.clear();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TableStructure.SubjectEntry.TableName);
        db.execSQL("DROP TABLE IF EXISTS " + TableStructure.Marks.TableName);
        db.execSQL("DROP TABLE IF EXISTS "+ TableStructure.TeacTT.TableName);
        db.execSQL("DROP TABLE IF EXISTS "+ TableStructure.Teacsub.TableName);
    }
}

