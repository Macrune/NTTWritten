package com.example.cekalamat.model;

import jakarta.validation.constraints.NotBlank;

public record CekAlamatRequest(
        @NotBlank(message = "Field provinsi wajib diisi") String provinsi,
        @NotBlank(message = "Field kabkota wajib diisi") String kabkota) {
}
