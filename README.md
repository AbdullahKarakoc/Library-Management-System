# Library Management System
## 1. GİRİŞ
### 1.1 Amaç

* Kullanıcıların sisteme kayıt olup kütüphane veritabanından varolan kitapları çekebilen Restful Api.

### 1.2 Özet
* Kitaplara erişmek için USER hesabı oluşturulmalı.
* Kullanıcılar sadece kitap okuma işlemi yapabilir.
* Kitap eklemek silmek ve düzenlemek için ADMIN hesabı oluşturulmalı.
* Kullanıcılar ve Adminler İd ve isime göre kitap arama yapabilir.
* Adminler kitap ekleyebilir silebilir ve güncelleyebilirler.


>**Book:** </br>
name: (min:1, max:50) is required/only characters</br> 
release: (min:1, max:2024) is required/only number</br> 
category: (min:5, max:20) is required/only characters</br></br>
>**Author:** </br> 
name: (min:10, max:40) is required/only characters</br>
surname: (min:10, max:40) is required/only characters</br>
birthdate: (Birthdate must be in the past) is required/only number</br>
>**Publisher:** </br>
name: (min:2, max:30) is required/only characters</br>
address: is required/only characters or number


>**Member:** </br>
name: (min:1, max:50) is required/only characters</br>
surname: (min:1, max:50) is required/only characters</br>
phone: (+90|0) is required/only Turkish phone</br>
email: Must be email type</br>
password: 1 büyük, 1 küçük, 1 özel karakter ve 8-20 arası boyut </br> 
roles: (ADMIN|USER) is required




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
* Hibernate
* Spring Boot Security
* Lombok
* Model Mapper
* SpringDoc OpenApi (Swagger 3)
* Spring Boot Validation
* Http Authentication and Authorization (Basic Auth)
  </br>
### 3.3 Veri Tabanı
* PostgreSql
  </br></br></br>

### 4. ENDPOİNT
## Book
| HTTP Method | Endpoint                      | Açıklama                                             | Yetki        |
|-------------|-------------------------------|------------------------------------------------------|--------------|
| GET         | /api/books                    | Tüm kitapları çeker                                  | ADMIN & USER |
| GET         | /api/booksById/{id}           | Id'e göre kitap çeker                                | ADMIN & USER |
| GET         | /api/booksByName/{name}       | Name'e göre kitap çeker                              | ADMIN & USER |
| POST        | /api/books                    | Yeni kitap oluşturur                                 | ADMIN        |
| PUT         | /api/books/{id}               | Id'e göre varolan kitabı günceller                   | ADMIN        |
| DELETE      | /api/books/{id}               | Id'e göre kitabı siler                               | ADMIN        |
| POST        | /api/bookIssue/{userId}       | User Id'e göre kitap kiralar/Request param(bookName) | ADMIN & USER |
| POST        | /bookReturn/{userId}/{bookId} | User Id ve Book Id ile kitap iade eder               | ADMIN & USER |





## Member
| HTTP Method | Endpoint        | Description                                         | Yetki              |
|-------------|-----------------|-----------------------------------------------------| ------------------ |
| GET         | /api/users      | Tüm kullanıcıları bilgilerini çeker                 | ADMIN              |
| GET         | /api/users/me   | İstek atan kullancı bilgisini çeker                 | ADMIN & USER       |
| POST        | /api/users      | Yeni kullanıcı oluşturur                            | ADMIN & USER       |
| PUT         | /api/users/{id} | Id'e göre kullanıcıyı günceller(Mail ve Role hariç) | ADMIN & USER       |
| DELETE      | /api/users/{id} | Id'e göre kullanıcıyı siler                         | ADMIN & USER       |
</br></br></br>

## 5. TEST
### Book
>{
"name": "Suç ve Ceza",</br>
"release": "1894-03-28",</br>
"bookCategory": "POLISIYE",</br>
"authors": {</br>
"name": "Fyodor Dostoyevski",</br>
"surname": "Dostoyevski",</br>
"birthdate": "1861-07-13"</br>
},</br>
"publishers": {</br>
"name": "İş Bankası Kültür Yayınları",</br>
"address": "no:78/4 Yeni mahalle Çankaya Ankara/Turkey"</br>
}
}

### User
>{
"name": "Nur",</br>
"surname": "Çınar",</br>
"phone":"+905423351265",</br>
"email":"nur@gmail.com",</br>
"password":"Nur1453*",</br>
"roles":"USER"</br>
}
</br></br></br>


## 6. ERD
![](images/ERD.png)

</br></br></br>



