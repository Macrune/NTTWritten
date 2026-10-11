package com.example.cekalamat.controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cekalamat.model.CekAlamatRequest;
import com.example.cekalamat.model.CekAlamatResponse;
import com.example.cekalamat.service.AlamatService;

@RestController
public class CekAlamatController {

    private final AlamatService alamatService;

    public CekAlamatController(AlamatService alamatService) {
        this.alamatService = alamatService;
    }

    @PostMapping(value = "/cekAlamat",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CekAlamatResponse cekAlamat(@Valid @RequestBody CekAlamatRequest request) {
        boolean sesuai = alamatService.cekAlamat(request.provinsi(), request.kabkota());
        return sesuai ? CekAlamatResponse.sesuai() : CekAlamatResponse.tidakSesuai();
    }
}
