package com.azore.compustore.fiuady.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Armín on 27/03/2017.
 */

class CategoryProductCursor extends CursorWrapper{

    public CategoryProductCursor(Cursor cursor) {super(cursor);}


    public CategoryProduct getCategoryProduct(){

        Cursor cursor = getWrappedCursor();
        return  new CategoryProduct(cursor.getInt(cursor.getColumnIndex((InventoryDbSchema.Categories_Table.Columns.ID))),
        cursor.getString(cursor.getColumnIndex(InventoryDbSchema.Categories_Table.Columns.DESCRIPTION)));

    }

}


public final class Inventory {
    private InventoryHelper inventoryHelper;
    private SQLiteDatabase db;

    public  Inventory(Context context){
        inventoryHelper= new InventoryHelper(context);
        db= inventoryHelper.getWritableDatabase();

    }


       public List<CategoryProduct> getAllCategoriesProduct() {
           List<CategoryProduct> list = new ArrayList<CategoryProduct>();

           //  Cursor cursor = db.rawQuery("SELECT * FROM categories ORDER BY id", null);

           CategoryProductCursor cursor = new CategoryProductCursor((db.rawQuery("SELECT * FROM product_categories ORDER BY id", null)));

           while (cursor.moveToNext()) {

               //list.add(new Category(cursor.getInt(cursor.getColumnIndex((InventoryDBSchema.CategoriesTable.Columns.ID))),
               //   cursor.getString(cursor.getColumnIndex((InventoryDBSchema.CategoriesTable.Columns.DESCRIPTION)))));

               list.add((cursor.getCategoryProduct()));  // metodo wrappcursor

           }
           cursor.close();

           return list;


   }



    public  void  updateCategory(CategoryProduct category)
    {

        ContentValues values = new ContentValues();
        values.put(InventoryDbSchema.Categories_Table.Columns.DESCRIPTION, category.getDescription());// asegura que siempre da correcto

        db.update(InventoryDbSchema.Categories_Table.NAME, values, InventoryDbSchema.Categories_Table.Columns.ID + " = ?", new String[]{Integer.toString(category.getId())});

    }

}
