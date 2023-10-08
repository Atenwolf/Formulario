package jose.machuca.formulariovista

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "my_database.db"
        private const val VERSION = 1


        const val TABLE_NAME = "Personas"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_APELLIDO = "apellido"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_DIR = "dir"
        const val COLUMN_EDAD = "edad"
        const val COLUMN_CEL = "cel"
        const val COLUMN_DATE = "date"
        const val COLUMN_BOOLEAN = "activo"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_APELLIDO TEXT," +
                "$COLUMN_EMAIL TEXT," +
                "$COLUMN_DIR TEXT," +
                "$COLUMN_EDAD INTEGER," +
                "$COLUMN_CEL INTEGER," +
                "$COLUMN_DATE TEXT," +
                "$COLUMN_BOOLEAN INTEGER" +
                ")"
        db.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}
