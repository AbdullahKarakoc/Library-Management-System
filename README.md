# Library Management System
## 1. INTRODUCTION
### 1.1 Purpose

* Restful Api, which allows users to register to the system and retrieve existing books from the library database.
### 1.2 Summary
* A USER account must be created to access the books.
* Users can only read books.
* An ADMIN account must be created to add, delete and edit books.
* Users and Admins can search for books by ID and name.
* Admins can add, delete and update books.
* Book issue and return transactions can be made with user and book IDs.
* User can issue a maximum of 5 books at the same time.
* If the user does not return the rented books within 10 days, he/she will pay a fine amount for each day.
* The date and who made the changes made to the books are kept.



  </br>

### 1.3 Model
>**Book:** </br>
name: (min:1, max:50) is required/only characters</br> 
release: (Release must be in the past) is required/only date</br> 
category: (min:5, max:20) is required/only given category</br>


>**Author:** </br> 
name: (min:10, max:40) is required/only characters</br>
surname: (min:10, max:40) is required/only characters</br>
birthdate: (Birthdate must be in the past) is required/only date</br>

>**Publisher:** </br>
name: (min:2, max:30) is required/only characters</br>
country: is required/only characters

>**Member:** </br>
name: (min:1, max:50) is required/only characters</br>
surname: (min:1, max:50) is required/only characters</br>
phone: (+90|0) is required/only Turkish phone</br>
email: Must be email type</br>
password: 1 uppercase, 1 lowercase, 1 special character and size 8-20 </br> 
roles: (ADMIN|USER) is required

</br></br></br>
## 2. STRUCTURE
### 2.1 Architecture
> **REST** architecture implemented
* REST is an architecture that works over the HTTP protocol, which enables communication between client and server. REST is a transfer method used in software built on service-oriented architecture. It enables the application to communicate by carrying XML and JSON data between the client and the server. Services that use the REST architecture are called RESTful services.</br>

### 2.2 Principle
> Efforts were made to remain faithful to **SOLID** principles.
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

## 3. TECHNOLOGIES

### 3.1 Environment and Tools
* Java 21
* Spring Boot 3.2.5
* Maven
  </br>
### 3.2 Library and Dependencies
* Spring Boot Data Jpa
* Auditing
* Hibernate
* Spring Boot Security
* Lombok
* Model Mapper
* SpringDoc OpenApi (Swagger 3)
* Spring Boot Validation
* Http Authentication and Authorization (Basic Auth)
  </br>
### 3.3 Database
* PostgreSql
  </br></br></br>

### 4. ENDPOİNT
## Book
| HTTP Method | Endpoint                            | Description                               | Authority    |
|-------------|-------------------------------------|-------------------------------------------|--------------|
| GET         | /api/books                          | Get all books                             | ADMIN & USER |
| GET         | /api/books/{id}                     | Get books by id                           | ADMIN & USER |
| GET         | /api/books/search                   | Get books by name/Request param(bookName) | ADMIN & USER |
| POST        | /api/books                          | Creates new book                          | ADMIN        |
| PUT         | /api/books/{id}                     | Updates existing book by id               | ADMIN        |
| DELETE      | /api/books/{id}                     | Deletes book by id                        | ADMIN        |
| PUT         | /api/books/issue/{userId}/{bookId}  | Issue books with User Id and Book Id      | ADMIN & USER |
| PUT         | /api/books/return/{userId}/{bookId} | Returns books with User Id and Book Id    | ADMIN & USER |





## Member
| HTTP Method | Endpoint        | Description                                             | Authority           |
|-------------|-----------------|---------------------------------------------------------|---------------------|
| GET         | /api/users      | Get all user information                                | ADMIN               |
| GET         | /api/users/me   | Get the requesting user information                     | ADMIN & USER        |
| POST        | /api/users      | Creates new user                                        | ADMIN & USER        |
| PUT         | /api/users/{id} | Updates the user according to Id (except Mail and Role) | ADMIN & USER        |
| DELETE      | /api/users/{id  | Deletes user by id                                      | ADMIN & USER        |


## 5. TEST
### Book
>{
"name": "Suç ve Ceza",</br>
"release": "1894-03-28",</br>
"bookCategory": "DETECTIVE",</br>
"authors": {</br>
"name": "Fyodor Dostoyevski",</br>
"surname": "Dostoyevski",</br>
"birthdate": "1861-07-13"</br>
},</br>
"publishers": {</br>
"name": "İş Bankası Kültür Yayınları",</br>
"country": "Turkey"</br>
}
}

### Member

>{
"name": "Admin",</br>
"surname": "admin",</br>
"phone":"+905423351265",</br>
"email":"admin@gmail.com",</br>
"password":"Admin1453*",</br>
"roles":"ADMIN"</br>
}

>{
"name": "Ali",</br>
"surname": "Çınar",</br>
"phone":"+905423351265",</br>
"email":"ali@gmail.com",</br>
"password":"Ali1453*",</br>
"roles":"USER"</br>
}

</br></br></br>


## 6. ERD
![](images/ERD.png)

</br></br></br>



