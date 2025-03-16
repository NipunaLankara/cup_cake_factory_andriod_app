package DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import Adapters.CategoryClass;
import Adapters.CupCakeClass;
import Adapters.OrderClass;
import Adapters.UserClass;

public class DBController  {

    private Context context;

    private SQLiteDatabase dbDatabase;

    private SQLiteDatabase readDatabase;

    public DBController(Context context){
        this.context=context;
    }

    public DBController OpenDB(){
        DBConnector dbConnector =new DBConnector(context);
        dbDatabase=dbConnector.getWritableDatabase();
        readDatabase = dbConnector.getReadableDatabase();
        return this;
    }

    public boolean createNewUser(UserClass userClass) {
        try {


            String query = "INSERT INTO user_details VALUES (?, ?, ?, ?)";

            dbDatabase.execSQL(query, new Object[]{userClass.getUserId(), userClass.getUserName(), userClass.getPassword(), userClass.getUserType()});

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

      /*  try {
            SQLiteDatabase database = getWritableDatabase();

            String query = "INSERT INTO user_details VALUES ('" + userClass.getUserId() + "'," + userClass.getUserName() + "','" + userClass.getPassword() + "','" + userClass.getUserType() + "')";
            database.execSQL(query);

            return true;
        }
          catch (Exception ex)
            {
                ex.printStackTrace();
                return false;
            }*/


    public ArrayList<UserClass> loginDetails (String userId,String password) {
        ArrayList<UserClass> userList = new ArrayList<UserClass>();

//        SQLiteDatabase database = getWritableDatabase();

        String query = "SELECT * FROM user_details WHERE user_id = '" + userId + "' and password = '" + password + "'";

        Cursor cursor = dbDatabase.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            do {
                UserClass userClass = new UserClass();
                userClass.setUserId(cursor.getString(0));
                userClass.setPassword(cursor.getString(2));
                userClass.setUserType(cursor.getString(3));
                userList.add(userClass);

            } while (cursor.moveToNext());

        }
        return  userList;
    }

    public  boolean addNewCategory(CategoryClass categoryClass) {
        try {
//            SQLiteDatabase database = getWritableDatabase();

            String query = "INSERT INTO category_details VALUES (?, ?)";

            dbDatabase.execSQL(query, new Object[]{categoryClass.getCategoryId(),categoryClass.getCategoryName()});

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<CategoryClass> getAllCategory () {

        ArrayList<CategoryClass> categoryList = new ArrayList<CategoryClass>();

        String query = "SELECT * FROM category_details";
        Cursor cursor = readDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                CategoryClass categoryClass = new CategoryClass();
                categoryClass.setCategoryId(cursor.getString(0));
                categoryClass.setCategoryName(cursor.getString(1));

                categoryList.add(categoryClass);

            } while (cursor.moveToNext());

        }
        return categoryList;

    }

    public void deleteCategory (String id) {

        dbDatabase.delete("category_details","category_id = ?",new String[]{id});
        dbDatabase.close();

    }

    public CategoryClass getSingleCategory (String id) {

        String query = "SELECT * FROM category_details WHERE category_id = '" + id + "'";

        Cursor cursor = dbDatabase.rawQuery(query,null);

        if (cursor != null) {
            cursor.moveToFirst();

            CategoryClass categoryClass = new CategoryClass(cursor.getString(0),cursor.getString(1));

            return categoryClass;

        }
        return null;
    }

    public int updateSingleCategory (CategoryClass categoryClass) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("category_name",categoryClass.getCategoryName());

        int status = dbDatabase.update("category_details", contentValues, "category_id = ?", new String[]{categoryClass.getCategoryId()});
        dbDatabase.close();

        return status;

    }

    public Vector<String> getCategoryName () {
        Vector<String> vector = new Vector<String>();

        String query = "SELECT category_name FROM category_details";

        Cursor cursor = dbDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                vector.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }

        return vector;

    }

    public String getCategoryId (String categoryName) {
        String category_Id = "";

        String query = "SELECT category_id FROM category_details WHERE category_name = '" + categoryName + "'";

        Cursor cursor = dbDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            category_Id = cursor.getString(0);
        }
        return category_Id;
    }

    public boolean addNewCupcakeItem (CupCakeClass cupCakeClass) {

        try {

            String query = "INSERT INTO cupcake_details VALUES (?,?,?,?,?)";

            dbDatabase.execSQL(query, new Object[]{cupCakeClass.getCupcakeId(), cupCakeClass.getCupcakeName(),
                    cupCakeClass.getCategoryId(), cupCakeClass.getPrice(), cupCakeClass.getQuantity()});

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public List<CupCakeClass> getAllCupcakes () {

        ArrayList<CupCakeClass> cupCakeList = new ArrayList<CupCakeClass>();

        String query = "SELECT * FROM cupcake_details";

        Cursor cursor = readDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                CupCakeClass cupCakeClass = new CupCakeClass();
                cupCakeClass.setCupcakeId(cursor.getString(0));
                cupCakeClass.setCupcakeName(cursor.getString(1));
                cupCakeClass.setCategoryId(cursor.getString(2));
                cupCakeClass.setPrice(cursor.getInt(3));
                cupCakeClass.setQuantity(cursor.getInt(4));

                cupCakeList.add(cupCakeClass);

            } while (cursor.moveToNext());

        }
        return cupCakeList;

    }

    public void deleteCupcakeItem (String id) {

        dbDatabase.delete("cupcake_details","cupcake_id = ?",new String[]{id});
        dbDatabase.close();

    }

    public List<CupCakeClass> searchCupcakesItem(String categoryId)
    {
        ArrayList<CupCakeClass> cupCakeList=new ArrayList<CupCakeClass>();
        try
        {
            Cursor cursor=dbDatabase.rawQuery("SELECT * FROM cupcake_details WHERE category_id='"+ categoryId+"' ",null);
            if(cursor.moveToFirst())//T
            {
                do{
                    CupCakeClass cupCakeClass=new CupCakeClass();
                    cupCakeClass.setCupcakeId(cursor.getString(0));
                    cupCakeClass.setCupcakeName(cursor.getString(1));
                    cupCakeClass.setCategoryId(cursor.getString(2));
                    cupCakeClass.setPrice(cursor.getInt(3));
                    cupCakeClass.setQuantity(cursor.getInt(4));

                    cupCakeList.add(cupCakeClass);

                }while (cursor.moveToNext());//F
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return cupCakeList;
    }

    public int getCupcakeQuantity(String name) {
        int cupcakeQuantity = 0;
        try {
            Cursor cursor = dbDatabase.rawQuery("SELECT quantity FROM cupcake_details  WHERE cupcake_name='" + name + "'", null);
            if (cursor.moveToFirst()) {
                cupcakeQuantity = cursor.getInt(0);
            }
            cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cupcakeQuantity;
    }

    public void buyCupcake(String cupcakeId,int qty) {

        try
        {
            dbDatabase.execSQL("UPDATE cupcake_details SET quantity=quantity-"+ qty+ " WHERE cupcake_id='"+cupcakeId+"'");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public  boolean addOrderDetails(OrderClass orderClass) {
        try {
//            SQLiteDatabase database = getWritableDatabase();

            String query = "INSERT INTO order_details VALUES (?,?,?,?,?)";

            dbDatabase.execSQL(query,new Object[]{orderClass.getOrderId(),orderClass.getCupcakeId(),orderClass.getUserId(),orderClass.getQuantity(),orderClass.getTotal()});

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<OrderClass> getAllOrders () {

        ArrayList<OrderClass> orderList = new ArrayList<OrderClass>();

        String query = "SELECT * FROM order_details";

        Cursor cursor = readDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                OrderClass orderClass = new OrderClass();
                orderClass.setOrderId(cursor.getString(0));
                orderClass.setCupcakeId(cursor.getString(1));
                orderClass.setUserId(cursor.getString(2));
                orderClass.setQuantity(cursor.getInt(3));
                orderClass.setTotal(cursor.getInt(4));

                orderList.add(orderClass);

            } while (cursor.moveToNext());

        }
        return orderList;

    }

    public void deleteOrder (String id) {

        dbDatabase.delete("order_details","order_id = ?",new String[]{id});
        dbDatabase.close();

    }

    public List<OrderClass> viewUserOrder(String userId)
    {
        ArrayList<OrderClass> orderList = new ArrayList<OrderClass>();
        try
        {
            Cursor cursor=dbDatabase.rawQuery("SELECT * FROM order_details WHERE user_id='"+ userId+"' ",null);
            if(cursor.moveToFirst())
            {
                do{

                    OrderClass orderClass = new OrderClass();
                    orderClass.setOrderId(cursor.getString(0));
                    orderClass.setCupcakeId(cursor.getString(1));
                    orderClass.setUserId(cursor.getString(2));
                    orderClass.setQuantity(cursor.getInt(3));
                    orderClass.setTotal(cursor.getInt(4));

                    orderList.add(orderClass);

                }while (cursor.moveToNext());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return orderList;
    }

}
