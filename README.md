# Library Management System
## 1. GİRİŞ
### 1.1 Amaç

* Kullanıcıların sisteme kayıt olup kütüphane veritabanından varolan kitapları çekebilen Restful Api.

### 1.2 Özet
* Kullanıcılar sadece kitap okuma işlemi yapabilir.
* Kullanıcılar ve Adminler İd ve isime göre kitap arama yapabilir.
* Adminler kitap ekleyebilir silebilir ve güncelleyebilirler.

## 2. YAPI
### 2.1 Mimari
> **REST** mimarisi uygulandı
* REST, client-server arasındaki haberleşmeyi sağlayan HTTP protokolü üzerinden çalışan bir mimaridir. REST ,servis yönelimli mimari üzerine oluşturulan yazılımlarda kullanılan bir transfer yöntemidir.İstemci ve sunucu arasında XML ve JSON verilerini taşıyarak uygulamanın haberleşmesini sağlar.REST mimarisini kullanan servislere ise RESTful servis denir.

### 2.2 Prensip
> **SOLID** prensiplerine sadık kalınmaya çalışıldı.

```
1. (S)ingle Responsibility Principle (SRP: Tek Sorumluluk Prensibi )
```
```
2. (O)pen/Closed Principle (OCP: Açık Kapalı Prensibi)
```
```
3. (L)iskov ‘s Substitution Principle (LSP: Liskov’un Yerine Geçme Prensibi )
```
```
4. (I)nterface Segregation Principle (ISP: Arayüz Ayrıştırma Prensibi )
```
```
5. (D)ependency Inversion Principle (DIP: Bağımlılık Ters Çevirme Prensibi )
```

## 3. TEKNOLOJİLER
### 3.1 Ortam
* Java
* Spring Boot
* Maven
### 3.2 Kütüphane ve Bağımlılıklar
* Spring Boot Data Jpa
* Spring Boot Security
* Lombok
* Model Mapper
* SpringDoc OpenApi (Swagger 3)
* Spring Boot Validation

### 3.3 Veri Tabanı
* PostgreSql

### 4. ENDPOİNT

## Book
| HTTP Method | Endpoint                | Açıklama                              | Yetki                |
| ----------- | ----------------------- | ------------------------------------- | -------------------- |
| GET         | /api/books              | Tüm kitapları çeker                   | ADMIN & USER         |
| GET         | /api/booksById/{id}     | Id'e göre kitap çeker                 | ADMIN & USER         |
| GET         | /api/booksByName/{name} | Name'e göre kitap çeker               | ADMIN & USER         |
| POST        | /api/books              | Yeni kitap oluşturur                  | ADMIN                |
| PUT         | /api/books/{id}         | Id'e göre varolan kitabı günceller    | ADMIN                |
| DELETE      | /api/books/{id}         | Id'e göre kitabı siler                | ADMIN                |

## User
| HTTP Method | Endpoint                | Description                           | Yetki              |
| ----------- | ----------------------- | ------------------------------------- | ------------------ |
| GET         | /api/users              | Tüm kullanıcıları bilgilerini çeker   | ADMIN              |
| GET         | /api/users/single       | İstek atan kullancı bilgisini çeker   | ADMIN & USER       |
| POST        | /api/users              | Yeni kullanıcı oluşturur              | ADMIN & USER       |


### 5. ERD
![Erd resmi](https://github.com/AbdullahKarakoc/Library-Management-System/blob/main/images/ERD.png)

