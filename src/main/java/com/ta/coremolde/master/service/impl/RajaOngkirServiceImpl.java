package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.service.RajaOngkirService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RajaOngkirServiceImpl implements RajaOngkirService {
    //TODO Move key to application.properties

    @Override
    public String getProvinces() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .get()
                    .url("https://api.rajaongkir.com/basic/province")
                    .addHeader("key", "dba85a3bca60bb8ea7cebf2990394bcb")
                    .build();
            Response response = client.newCall(request).execute();

            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error");
        }
    }

    @Override
    public String getCities(Integer province) {
        String url = "https://api.rajaongkir.com/basic/city";

        if (province != null) {
            url = url.concat("?province=" + province);
        }

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .addHeader("key", "dba85a3bca60bb8ea7cebf2990394bcb")
                    .build();
            Response response = client.newCall(request).execute();

            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error");
        }
    }

}
