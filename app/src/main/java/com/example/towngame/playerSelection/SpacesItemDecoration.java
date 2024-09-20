package com.example.towngame.playerSelection;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private final int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // Item position
        int spanCount = 3; // Number of columns
        int spacing = space; // Desired spacing
        boolean includeEdge = true;

        int column = position % spanCount; // Item column

        if (includeEdge) {
            // Добавляем отступы для крайних элементов
            outRect.left = spacing - column * spacing / spanCount; // Отступ слева для крайних элементов
            outRect.right = (column + 1) * spacing / spanCount; // Отступ справа для всех элементов

            outRect.bottom = spacing; // Нижний отступ
        } else {
            // Логика отступов без включения крайних элементов
            outRect.left = column * spacing / spanCount;
            outRect.right = spacing - (column + 1) * spacing / spanCount;

            // Добавляем верхний отступ только начиная со второй строки
            if (position >= spanCount) {
                outRect.top = spacing;
            }
        }
    }
}