package com.example.l03.projektpaszport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.net.URI;

/**
 * Created by Bartek on 2016-10-24.
 */
public class Zdjecie {

    private String sciezka;

    public String getObrazekURI(int requestCode, int resultCode, Intent data,Context context){
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == 10) {
                Uri selectedImageUri = data.getData();
                sciezka = getRealPathFromURI(selectedImageUri,context);
            }
        }
        return sciezka;
    }


    private String getRealPathFromURI(Uri uri, Context context) {
        if (uri == null) {
            return null;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }

    //TODO: funkcja zwracająca zdjęcie do wyświetlenia

}
