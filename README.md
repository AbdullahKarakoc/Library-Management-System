# Library Management System
## 1. GİRİŞ
### 1.1 Amaç

* Kullanıcıların sisteme kayıt olup kütüphane veritabanından varolan kitapları çekebilen Restful Api.

### 1.2 Özet
* Kullanıcılar sadece kitap okuma işlemi yapabilir.
* Kullanıcılar ve Adminler İd ve isime göre kitap arama yapabilir.
* Adminler kitap ekleyebilir silebilir ve güncelleyebilirler.


>**Book:** </br>
name: (min:1, max:50) is required/only characters</br> 
release: (min:1, max:2024) is required/only number</br> 
type: (min:5, max:20) is required/only characters</br></br>
>**Author:** </br> 
name: (min:10, max:40) is required/only characters</br> 
age: (min:3, max:150) is required/only number</br>
country: (min:2, max:40) is required/only characters</br></br>
>**Publisher:** </br>
name: (min:2, max:30) is required/only characters

>**User:** </br> 
email: Must be email type</br>
password: 1 büyük, 1 küçük, 1 özel karakter ve 8-20 arası boyut </br> 
roles: is required




  </br></br></br>
## 2. YAPI
### 2.1 Mimari
> **REST** mimarisi uygulandı
* REST, client-server arasındaki haberleşmeyi sağlayan HTTP protokolü üzerinden çalışan bir mimaridir. REST ,servis yönelimli mimari üzerine oluşturulan yazılımlarda kullanılan bir transfer yöntemidir.İstemci ve sunucu arasında XML ve JSON verilerini taşıyarak uygulamanın haberleşmesini sağlar.REST mimarisini kullanan servislere ise RESTful servis denir.</br>

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

</br></br></br>

## 3. TEKNOLOJİLER
### 3.1 Ortam ve Araçlar
* Java 21
* Spring Boot 3.2.5
* Maven
  </br>
### 3.2 Kütüphane ve Bağımlılıklar
* Spring Boot Data Jpa
* Spring Boot Security
* Lombok
* Model Mapper
* SpringDoc OpenApi (Swagger 3)
* Spring Boot Validation
* Http Authentication and Authorization
  </br>
### 3.3 Veri Tabanı
* PostgreSql
  </br></br></br>

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

</br></br></br>

### 5. TEST
## Book
>{
"name": "Suç ve Ceza",
"release": 1879,
"type": "Macera",
"authors": {
"name": "da Vinci",
"age": 80,
"country": "Italya"
},
"publishers": {
"name": "Sınav yayınevi"
}
}

## User
>{
"email": "davinci1453@gmail.com",
"password": "DaVinci1453*",
"roles": "USER"
}


### 6. ERD
![Erd resmi](https://github.com/AbdullahKarakoc/Library-Management-System/blob/main/images/ERD.png)

</br></br></br>



