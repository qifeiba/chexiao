package com.sunwuyou.swymcx.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sunwuyou.swymcx.model.FieldSaleItemBatchEx;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin
 * 2018/7/25.
 * content
 */

public class FieldSaleItemBatchDAO {
    private SQLiteDatabase db;
    private DBOpenHelper helper;
    private Cursor cursor;

    public FieldSaleItemBatchDAO() {
        super();
        this.helper = new DBOpenHelper();
    }

    public List<FieldSaleItemBatchEx> queryFieldSaleBatchs(long arg12, boolean arg14) {
        this.db = this.helper.getReadableDatabase();
        String v4 = "select fb.serialid,fb.fieldsaleid,fb.batch,fb.isout,fb.num,fb.goodsid,fb.unitid, b.stocknumber as stocknumber,b.bigstocknumber as bigstocknumber,fb.price,gu.unitname, fb.productiondate,gu.ratio, g.name as goodsname, g.barcode, g.specification, g.isusebatch  from kf_fieldsaleitembatch fb  left join kf_goodsbatch b on b.goodsid=fb.goodsid and b.batch=fb.batch  left join sz_goodsunit gu on gu.goodsid=fb.goodsid and gu.unitid=fb.unitid left join sz_goods g on g.id=fb.goodsid where fb.fieldsaleid=? and fb.isout=?";
        try {
            cursor = db.rawQuery(v4, new String[]{String.valueOf(arg12).toString(), arg14 ? "1" : "0"});
            ArrayList<FieldSaleItemBatchEx> v0 = new ArrayList<FieldSaleItemBatchEx>();
            while (cursor.moveToNext()) {
                FieldSaleItemBatchEx itemBatchEx = new FieldSaleItemBatchEx();
                itemBatchEx.setSerialid(cursor.getLong(0));
                itemBatchEx.setFieldsaleid(cursor.getLong(1));
                itemBatchEx.setBatch(cursor.getString(2));
                itemBatchEx.setIsout(cursor.getInt(3) == 1);
                itemBatchEx.setNum(cursor.getDouble(4));
                itemBatchEx.setGoodsid(cursor.getString(5));
                itemBatchEx.setUnitid(cursor.getString(6));
                itemBatchEx.setStocknumber(cursor.getDouble(7));
                itemBatchEx.setBigstocknumber(cursor.getString(8));
                itemBatchEx.setPrice(cursor.getDouble(9));
                itemBatchEx.setUnitname(cursor.getString(10));
                itemBatchEx.setProductiondate(cursor.getString(11));
                itemBatchEx.setRatio(cursor.getDouble(12));
                itemBatchEx.setGoodsname(cursor.getString(13));
                itemBatchEx.setBarcode(cursor.getString(14));
                itemBatchEx.setSpecification(cursor.getString(15));
                itemBatchEx.setIsusebatch(cursor.getInt(16) == 1);
                v0.add(itemBatchEx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return new ArrayList<>();
    }

    public List<FieldSaleItemBatchEx> queryItemBatchs(long arg11, String arg13, boolean arg14) {
        this.db = helper.getReadableDatabase();
        String v4 = "select fb.serialid,fb.fieldsaleid,fb.batch,fb.isout,fb.num,fb.goodsid,fb.unitid, b.stocknumber as stocknumber,b.bigstocknumber as bigstocknumber,fb.price,gu.unitname, fb.productiondate,gu.ratio, g.name as goodsname, g.barcode, g.specification, g.isusebatch  from kf_fieldsaleitembatch fb  left join kf_goodsbatch b on b.goodsid=fb.goodsid and b.batch=fb.batch  left join sz_goodsunit gu on gu.goodsid=fb.goodsid and gu.unitid=fb.unitid left join sz_goods g on g.id=fb.goodsid where fb.fieldsaleid=? and fb.goodsid=? and fb.isout=?";
        ArrayList<FieldSaleItemBatchEx> list = new ArrayList<>();
        try {
            cursor = db.rawQuery(v4, new String[]{String.valueOf(arg11), arg13, arg14 ? "1" : "0"});
            while (cursor.moveToNext()) {
                FieldSaleItemBatchEx v2 = new FieldSaleItemBatchEx();
                v2.setSerialid(cursor.getLong(0));
                v2.setFieldsaleid(cursor.getLong(1));
                v2.setBatch(cursor.getString(2));
                v2.setIsout(cursor.getInt(3) == 1);
                v2.setNum(cursor.getDouble(4));
                v2.setGoodsid(cursor.getString(5));
                v2.setUnitid(cursor.getString(6));
                v2.setStocknumber(cursor.getDouble(7));
                v2.setBigstocknumber(cursor.getString(8));
                v2.setPrice(cursor.getDouble(9));
                v2.setUnitname(cursor.getString(10));
                v2.setProductiondate(cursor.getString(11));
                v2.setRatio(cursor.getDouble(12));
                v2.setGoodsname(cursor.getString(13));
                v2.setBarcode(cursor.getString(14));
                v2.setSpecification(cursor.getString(15));
                v2.setIsusebatch(cursor.getInt(16) == 1);
                list.add(v2);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return list;
    }

    public void deleteAll(long arg7) {
        this.db = this.helper.getWritableDatabase();
        String v0 = "delete from kf_fieldsaleitembatch where fieldsaleid=?";
        try {
            this.db.execSQL(v0, new String[]{String.valueOf(arg7)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    public boolean dealCancelItemBatchs(long arg11, String arg13, List<FieldSaleItemBatchEx> arg14) {
        this.db = this.helper.getReadableDatabase();
        this.db.beginTransaction();
        try {
            this.db.delete("kf_fieldsaleitembatch", "fieldsaleid=? and goodsid=?", new String[]{String.valueOf(arg11), arg13});
            for (int i = 0; i < arg14.size(); i++) {
                if (arg14.get(i).getNum() != 0) {
                    ContentValues v2 = new ContentValues();
                    v2.put("fieldsaleid", arg14.get(i).getFieldsaleid());
                    v2.put("goodsid", arg14.get(i).getGoodsid());
                    v2.put("unitid", arg14.get(i).getUnitid());
                    v2.put("batch", arg14.get(i).getBatch());
                    String v4 = "isout";
                    String v3_1 = arg14.get(i).getIsout() ? "1" : "0";
                    v2.put(v4, v3_1);
                    v2.put("num", arg14.get(i).getNum());
                    v2.put("price", arg14.get(i).getPrice());
                    v2.put("productiondate", arg14.get(i).getProductiondate());
                    this.db.insert("kf_fieldsaleitembatch", null, v2);
                }
            }
            this.db.setTransactionSuccessful();
            this.db.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return true;
    }
}
