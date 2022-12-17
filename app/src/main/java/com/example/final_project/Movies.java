package com.example.final_project;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {
    int id;
    String title;
    String image;
    String date;
    String language;


    public Movies() {
    }

    public Movies(int id, String title, String image, String date, String language) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.date = date;
        this.language = language;
    }


    protected Movies(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image = in.readString();
        date = in.readString();
        language = in.readString();
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
    }
}


