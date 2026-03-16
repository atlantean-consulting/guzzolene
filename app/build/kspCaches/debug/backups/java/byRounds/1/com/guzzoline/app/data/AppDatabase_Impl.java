package com.guzzoline.app.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile TripLogDao _tripLogDao;

  private volatile SavedTripDao _savedTripDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `trip_log` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `tripName` TEXT NOT NULL, `origin` TEXT NOT NULL, `destination` TEXT NOT NULL, `distance` REAL NOT NULL, `mpg` REAL NOT NULL, `gasPrice` REAL NOT NULL, `totalCost` REAL NOT NULL, `timestamp` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `saved_trips` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `origin` TEXT NOT NULL, `destination` TEXT NOT NULL, `distance` REAL NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'dd91a64a3e34f9e6a8d16b6d3ddd6604')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `trip_log`");
        db.execSQL("DROP TABLE IF EXISTS `saved_trips`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsTripLog = new HashMap<String, TableInfo.Column>(9);
        _columnsTripLog.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTripLog.put("tripName", new TableInfo.Column("tripName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTripLog.put("origin", new TableInfo.Column("origin", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTripLog.put("destination", new TableInfo.Column("destination", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTripLog.put("distance", new TableInfo.Column("distance", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTripLog.put("mpg", new TableInfo.Column("mpg", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTripLog.put("gasPrice", new TableInfo.Column("gasPrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTripLog.put("totalCost", new TableInfo.Column("totalCost", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTripLog.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTripLog = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTripLog = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTripLog = new TableInfo("trip_log", _columnsTripLog, _foreignKeysTripLog, _indicesTripLog);
        final TableInfo _existingTripLog = TableInfo.read(db, "trip_log");
        if (!_infoTripLog.equals(_existingTripLog)) {
          return new RoomOpenHelper.ValidationResult(false, "trip_log(com.guzzoline.app.data.TripLogEntity).\n"
                  + " Expected:\n" + _infoTripLog + "\n"
                  + " Found:\n" + _existingTripLog);
        }
        final HashMap<String, TableInfo.Column> _columnsSavedTrips = new HashMap<String, TableInfo.Column>(5);
        _columnsSavedTrips.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSavedTrips.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSavedTrips.put("origin", new TableInfo.Column("origin", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSavedTrips.put("destination", new TableInfo.Column("destination", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSavedTrips.put("distance", new TableInfo.Column("distance", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSavedTrips = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSavedTrips = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSavedTrips = new TableInfo("saved_trips", _columnsSavedTrips, _foreignKeysSavedTrips, _indicesSavedTrips);
        final TableInfo _existingSavedTrips = TableInfo.read(db, "saved_trips");
        if (!_infoSavedTrips.equals(_existingSavedTrips)) {
          return new RoomOpenHelper.ValidationResult(false, "saved_trips(com.guzzoline.app.data.SavedTripEntity).\n"
                  + " Expected:\n" + _infoSavedTrips + "\n"
                  + " Found:\n" + _existingSavedTrips);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "dd91a64a3e34f9e6a8d16b6d3ddd6604", "1c3feb3441a8cf7c16d1b907db3b0538");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "trip_log","saved_trips");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `trip_log`");
      _db.execSQL("DELETE FROM `saved_trips`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(TripLogDao.class, TripLogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SavedTripDao.class, SavedTripDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public TripLogDao tripLogDao() {
    if (_tripLogDao != null) {
      return _tripLogDao;
    } else {
      synchronized(this) {
        if(_tripLogDao == null) {
          _tripLogDao = new TripLogDao_Impl(this);
        }
        return _tripLogDao;
      }
    }
  }

  @Override
  public SavedTripDao savedTripDao() {
    if (_savedTripDao != null) {
      return _savedTripDao;
    } else {
      synchronized(this) {
        if(_savedTripDao == null) {
          _savedTripDao = new SavedTripDao_Impl(this);
        }
        return _savedTripDao;
      }
    }
  }
}
