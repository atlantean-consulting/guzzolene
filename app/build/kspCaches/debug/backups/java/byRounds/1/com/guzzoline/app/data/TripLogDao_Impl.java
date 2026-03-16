package com.guzzoline.app.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TripLogDao_Impl implements TripLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TripLogEntity> __insertionAdapterOfTripLogEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public TripLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTripLogEntity = new EntityInsertionAdapter<TripLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `trip_log` (`id`,`tripName`,`origin`,`destination`,`distance`,`mpg`,`gasPrice`,`totalCost`,`timestamp`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TripLogEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTripName());
        statement.bindString(3, entity.getOrigin());
        statement.bindString(4, entity.getDestination());
        statement.bindDouble(5, entity.getDistance());
        statement.bindDouble(6, entity.getMpg());
        statement.bindDouble(7, entity.getGasPrice());
        statement.bindDouble(8, entity.getTotalCost());
        statement.bindLong(9, entity.getTimestamp());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM trip_log WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final TripLogEntity entry, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTripLogEntity.insert(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final int id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TripLogEntity>> getAll() {
    final String _sql = "SELECT * FROM trip_log ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"trip_log"}, new Callable<List<TripLogEntity>>() {
      @Override
      @NonNull
      public List<TripLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTripName = CursorUtil.getColumnIndexOrThrow(_cursor, "tripName");
          final int _cursorIndexOfOrigin = CursorUtil.getColumnIndexOrThrow(_cursor, "origin");
          final int _cursorIndexOfDestination = CursorUtil.getColumnIndexOrThrow(_cursor, "destination");
          final int _cursorIndexOfDistance = CursorUtil.getColumnIndexOrThrow(_cursor, "distance");
          final int _cursorIndexOfMpg = CursorUtil.getColumnIndexOrThrow(_cursor, "mpg");
          final int _cursorIndexOfGasPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "gasPrice");
          final int _cursorIndexOfTotalCost = CursorUtil.getColumnIndexOrThrow(_cursor, "totalCost");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<TripLogEntity> _result = new ArrayList<TripLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TripLogEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTripName;
            _tmpTripName = _cursor.getString(_cursorIndexOfTripName);
            final String _tmpOrigin;
            _tmpOrigin = _cursor.getString(_cursorIndexOfOrigin);
            final String _tmpDestination;
            _tmpDestination = _cursor.getString(_cursorIndexOfDestination);
            final double _tmpDistance;
            _tmpDistance = _cursor.getDouble(_cursorIndexOfDistance);
            final double _tmpMpg;
            _tmpMpg = _cursor.getDouble(_cursorIndexOfMpg);
            final double _tmpGasPrice;
            _tmpGasPrice = _cursor.getDouble(_cursorIndexOfGasPrice);
            final double _tmpTotalCost;
            _tmpTotalCost = _cursor.getDouble(_cursorIndexOfTotalCost);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new TripLogEntity(_tmpId,_tmpTripName,_tmpOrigin,_tmpDestination,_tmpDistance,_tmpMpg,_tmpGasPrice,_tmpTotalCost,_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAllOnce(final Continuation<? super List<TripLogEntity>> $completion) {
    final String _sql = "SELECT * FROM trip_log ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TripLogEntity>>() {
      @Override
      @NonNull
      public List<TripLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTripName = CursorUtil.getColumnIndexOrThrow(_cursor, "tripName");
          final int _cursorIndexOfOrigin = CursorUtil.getColumnIndexOrThrow(_cursor, "origin");
          final int _cursorIndexOfDestination = CursorUtil.getColumnIndexOrThrow(_cursor, "destination");
          final int _cursorIndexOfDistance = CursorUtil.getColumnIndexOrThrow(_cursor, "distance");
          final int _cursorIndexOfMpg = CursorUtil.getColumnIndexOrThrow(_cursor, "mpg");
          final int _cursorIndexOfGasPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "gasPrice");
          final int _cursorIndexOfTotalCost = CursorUtil.getColumnIndexOrThrow(_cursor, "totalCost");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<TripLogEntity> _result = new ArrayList<TripLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TripLogEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTripName;
            _tmpTripName = _cursor.getString(_cursorIndexOfTripName);
            final String _tmpOrigin;
            _tmpOrigin = _cursor.getString(_cursorIndexOfOrigin);
            final String _tmpDestination;
            _tmpDestination = _cursor.getString(_cursorIndexOfDestination);
            final double _tmpDistance;
            _tmpDistance = _cursor.getDouble(_cursorIndexOfDistance);
            final double _tmpMpg;
            _tmpMpg = _cursor.getDouble(_cursorIndexOfMpg);
            final double _tmpGasPrice;
            _tmpGasPrice = _cursor.getDouble(_cursorIndexOfGasPrice);
            final double _tmpTotalCost;
            _tmpTotalCost = _cursor.getDouble(_cursorIndexOfTotalCost);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new TripLogEntity(_tmpId,_tmpTripName,_tmpOrigin,_tmpDestination,_tmpDistance,_tmpMpg,_tmpGasPrice,_tmpTotalCost,_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
