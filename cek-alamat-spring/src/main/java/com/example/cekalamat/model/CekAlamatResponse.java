package com.example.cekalamat.model;

public record CekAlamatResponse(String code, String message) {

    public static CekAlamatResponse sesuai() {
        return new CekAlamatResponse("1", "Sesuai");
    }

    public static CekAlamatResponse tidakSesuai() {
        return new CekAlamatResponse("0", "Tidak Sesuai");
    }
}
