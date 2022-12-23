package com.example.final_project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movies implements Parcelable {
    @PrimaryKey
    int id;
    @NonNull
    String title;
    String image;
    String date;
    String language;
    Boolean favb;



    public Movies() {
    }

    public Movies(int id, String title, String image, String date, String language, Boolean favb) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.date = date;
        this.language = language;
        this.favb = favb;
    }


    protected Movies(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image = in.readString();
        date = in.readString();
        language = in.readString();
        byte tmpFavb = in.readByte();
        favb = tmpFavb == 0 ? null : tmpFavb == 1;
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(image);
        parcel.writeString(date);
        parcel.writeString(language);
        parcel.writeByte((byte) (favb == null ? 0 : favb ? 1 : 2));
    }
}


