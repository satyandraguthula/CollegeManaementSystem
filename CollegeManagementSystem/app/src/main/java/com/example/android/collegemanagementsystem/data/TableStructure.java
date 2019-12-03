package com.example.android.collegemanagementsystem.data;

import android.provider.BaseColumns;

/**
 * Created by satyandra on 16/3/15.
 */
public class TableStructure {
    public static final class SubjectEntry implements BaseColumns{
        public static final String TableName="Subjects";
        public static final String Column_subject="Subject";
        public static final String Column_type="type";
        public static final String Column_teacher="Teacher";
    }
    public  static final class Marks implements BaseColumns{
        public static final String TableName="Marks";
        public static final String Column_ut1="UT1";
        public static final String Column_ut2="UT2";
    }
    public static final class TTable implements BaseColumns{
        public static final String TableName="TimeTable";
        public static final String Day="Day";
        public static final String Slot1="Slot1";
        public static final String Slot2="Slot2";
        public static final String Slot3="Slot3";
        public static final String Slot4="Slot4";
        public static final String Slot5="Slot5";
        public static final String Slot6="Slot6";
        public static final String Slot7="Slot7";
        public static final String Slot8="Slot8";
    }
    public static final class TeacTT implements BaseColumns{
        public static final String TableName="TeacTT";
        public static final String Day="Day";
        public static final String Slot1="Slot1";
        public static final String Slot2="Slot2";
        public static final String Slot3="Slot3";
        public static final String Slot4="Slot4";
        public static final String Slot5="Slot5";
        public static final String Slot6="Slot6";
        public static final String Slot7="Slot7";
        public static final String Slot8="Slot8";
    }
    public static final class Teacsub implements  BaseColumns
    {
        public static final String TableName="Teacsub";
        public static final String TSubject="Subject";
        public static final String Year="Year";
        public static final String Tclass="class";
    }
}
