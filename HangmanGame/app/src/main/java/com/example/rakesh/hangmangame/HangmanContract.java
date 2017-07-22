package com.example.rakesh.hangmangame;

import android.provider.BaseColumns;

/**
 * Created by rakesh on 19/7/17.
 */

public class HangmanContract {

    public class HangmanEntry implements BaseColumns{

        public static final String TABLE_ANIMALS = "ANIMALS";
        public static final String TABLE_COUNTRIES = "COUNTRIES";
        public static final String TABLE_COLOURS = "COLOURS";
        public static final String TABLE_FRUITS = "FRUITS";
        public static final String TABLE_VEGETABLES = "VEGETABLES";

        public static final String COLUMN_ANIMALS = "animals";
        public static final String COLUMN_COUNTRIES = "countries";
        public static final String COLUMN_COLOURS = "colours";
        public static final String COLUMN_FRUITS = "fruits";
        public static final String COLUMN_VEGETABLES = "vegetables";

    }
}
