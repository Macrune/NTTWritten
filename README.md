# Cek Alamat API (Spring Boot)

REST API `POST /cekAlamat` yang memvalidasi apakah sebuah kabupaten/kota berada di
dalam provinsi, dengan memanggil API publik `https://alamat.thecloudalert.com/api/`
(`provinsi/get/` dan `kabkota/get/`).

## Prasyarat
Java 17+ dan Maven 3.6+

## Menjalankan
```bash
mvn spring-boot:run     # http://localhost:8080
mvn test                # unit test (API publik di-mock)
```

## Contoh
```bash
curl -X POST http://localhost:8080/cekAlamat \
  -H "Content-Type: application/json" \
  -d '{"provinsi":"Bali","kabkota":"Kabupaten Badung"}'
# {"code":"1","message":"Sesuai"}

curl -X POST http://localhost:8080/cekAlamat \
  -H "Content-Type: application/json" \
  -d '{"provinsi":"Bangka Belitung","kabkota":"Kota Surabaya"}'
# {"code":"0","message":"Tidak Sesuai"}
```

| Kondisi | HTTP | Response |
|---|---|---|
| Sesuai | 200 | `{"code":"1","message":"Sesuai"}` |
| Tidak sesuai | 200 | `{"code":"0","message":"Tidak Sesuai"}` |

Pencocokan nama tidak membedakan huruf besar/kecil dan mengabaikan spasi berlebih.

## Soal 2 (optional)
Lihat `soal2/pseudo SOAL 2.txt`.

## Catatan sertifikat SSL
Server `alamat.thecloudalert.com` tidak mengirim sertifikat intermediate-nya, sehingga Java
gagal (`PKIX path building failed`) walau browser/Postman berhasil.

1. Unduh intermediate: http://certumdvtlsg2r39ca.repository.certum.pl/certumdvtlsg2r39ca.cer
2. Buat truststore:
   `keytool -importcert -alias certum -file certumdvtlsg2r39ca.cer -keystore src/main/resources/truststore.jks -storepass changeit -noprompt`
3. Jalankan seperti biasa. `application.properties` + `CertumTrustConfig` memakai truststore itu
   (verifikasi SSL tetap aktif).
