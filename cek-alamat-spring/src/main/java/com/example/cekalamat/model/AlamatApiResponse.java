package com.example.cekalamat.model;

import java.util.List;

public record AlamatApiResponse(int status, String message, List<Item> result) {

    public record Item(String id, String text) {
    }
}
