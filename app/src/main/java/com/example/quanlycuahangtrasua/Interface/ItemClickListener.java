package com.example.quanlycuahangtrasua.Interface;

import com.google.firebase.database.core.view.View;

public interface ItemClickListener {
    void onClick(View view, int position, boolean isLongClick);
}
