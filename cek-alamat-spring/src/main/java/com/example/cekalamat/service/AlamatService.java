package com.example.cekalamat.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.cekalamat.model.AlamatApiResponse;
import com.example.cekalamat.model.AlamatApiResponse.Item;

import java.util.List;
import java.util.Locale;

@Service
public class AlamatService {

    private final RestClient restClient;

    public AlamatService(RestClient.Builder builder,
                         @Value("${alamat.base-url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public boolean cekAlamat(String provinsi, String kabkota) {
        List<Item> provinces = getProvinces();

        Item prov = provinces.stream()
                .filter(p -> normalize(p.text()).equals(normalize(provinsi)))
                .findFirst()
                .orElse(null);
        if (prov == null) {
            return false;
        }

        return getCities(prov.id()).stream()
                .anyMatch(c -> normalize(c.text()).equals(normalize(kabkota)));
    }

    private List<Item> getProvinces() {
        AlamatApiResponse body = restClient.get()
                .uri("/provinsi/get/")
                .retrieve()
                .body(AlamatApiResponse.class);
        return resultOf(body);
    }

    private List<Item> getCities(String provinsiId) {
        AlamatApiResponse body = restClient.get()
                .uri(uri -> uri.path("/kabkota/get/")
                        .queryParam("d_provinsi_id", provinsiId)
                        .build())
                .retrieve()
                .body(AlamatApiResponse.class);
        return resultOf(body);
    }

    private static List<Item> resultOf(AlamatApiResponse body) {
        return (body == null || body.result() == null) ? List.of() : body.result();
    }

    private static String normalize(String s) {
        return s == null ? "" : s.trim().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
    }
}
