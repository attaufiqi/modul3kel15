package modul3kel07.notebook.example.com.modul3kel15.ui.detailCar;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import modul3kel07.notebook.example.com.modul3kel15.data.model.DataCar;
import modul3kel07.notebook.example.com.modul3kel15.data.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {
    private final DetailView mView ;
    public  DetailPresenter(DetailView detailView){ mView =detailView;}

    public void getCarById(DataCar dataCar) {
        RetrofitClient.getInstance()
                .getApi()
                .GetCarById(dataCar.getId())
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            JsonObject body = response.body();
                            JsonArray array = body.get("result").getAsJsonArray();
                            Type type =new TypeToken<List<DataCar>>(){}.getType();
                            List<DataCar> dataCar = new Gson().fromJson(array, type);
                            mView.showSuccessCarById(dataCar);
                        } else {
                            mView.showErrorCarById("ERROR Ya BOSS !!");
                        }
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.d("DATA", t.getMessage());
                        mView.showErrorCarById(t.getMessage());
                    }
                });
    }
}