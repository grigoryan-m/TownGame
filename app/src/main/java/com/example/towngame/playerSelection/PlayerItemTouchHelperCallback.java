package com.example.towngame.playerSelection;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class PlayerItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final PlayerAdapter adapter;

    public PlayerItemTouchHelperCallback(PlayerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true; // Включаем перетаскивание при длительном нажатии
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false; // Отключаем свайп для удаления элементов, если не нужно
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {


        // Позволяем перемещать элементы только вверх/вниз или влево/вправо
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        // Обработка перемещения элементов
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();

        // Меняем местами элементы в списке
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        // Ничего не делаем, т.к. свайпы отключены
    }
}
