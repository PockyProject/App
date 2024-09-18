package com.example.pocky.domain.repository.orderList;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.pocky.domain.repository.Database;
import com.example.pocky.domain.repository.favor.Favor;
import com.example.pocky.domain.repository.favor.FavorDao;

import java.util.List;

public class OrderRepository {
    private OrderDao orderDao;
    private LiveData<List<Order>> Orders;

    public OrderRepository(Application application) {
        Database db = Database.getInstance(application);
        orderDao = db.orderDao();
        Orders = orderDao.getAllOrder();
    }

    public LiveData<List<Order>> getAllFavors() {
        return Orders;
    }

    public void insert(Order order) {
        new OrderRepository.InsertFavorAsyncTask(orderDao).execute(order);
    }

    public void delete(Order order) {
        new OrderRepository.DeleteFavorAsyncTask(orderDao).execute(order);
    }

    // 비동기 처리
    private static class InsertFavorAsyncTask extends AsyncTask<Order, Void, Void> {
        private OrderDao orderDao;

        private InsertFavorAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.insert(orders[0]);
            return null;
        }
    }

    private static class DeleteFavorAsyncTask extends AsyncTask<Order, Void, Void> {
        private OrderDao orderDao;

        private DeleteFavorAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.delete(orders[0]);
            return null;
        }
    }

    // 여러 개의 Favor 객체를 저장하는 메서드
    public void insertAll(Order... orders) {
        Database.databaseWriteExecutor.execute(() -> {
            orderDao.insertAll(orders);
        });
    }
}
