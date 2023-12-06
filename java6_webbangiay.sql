create database asm_java6_webbangiay
go
use asm_java6_webbangiay
go
-- Script cho bảng "roles"
CREATE TABLE Roles (
    Id NVARCHAR(255) PRIMARY KEY NOT NULL,
    Name NVARCHAR(255) NOT NULL
);

-- Script cho bảng "Accounts"
CREATE TABLE Accounts (
    Username NVARCHAR(255) PRIMARY KEY NOT NULL,
    Password NVARCHAR(255) NOT NULL,
    Fullname NVARCHAR(255) NOT NULL,
    Email NVARCHAR(255) NOT NULL,
    Photo NVARCHAR(255)
);

-- Script cho bảng "Authorities" với khóa ngoại ở ngoài
CREATE TABLE Authorities (
    Id INT PRIMARY KEY IDENTITY NOT NULL,
    Username NVARCHAR(255) NOT NULL,
    RoleId NVARCHAR(255) NOT NULL,
    CONSTRAINT UK_Authorities_Username_Roleid UNIQUE (Username, Roleid),
    FOREIGN KEY (RoleId) REFERENCES roles(id),
    FOREIGN KEY (Username) REFERENCES Accounts(username)
);

-- Script cho bảng "Categories"
CREATE TABLE Categories (
    Id NVARCHAR(255) PRIMARY KEY NOT NULL,
    Name NVARCHAR(255) NOT NULL
);

-- Script cho bảng "Orders"
CREATE TABLE Orders (
    Id BIGINT PRIMARY KEY IDENTITY NOT NULL,
    Address NVARCHAR(255),
    CreateDate DATE,
    Username NVARCHAR(255),
    FOREIGN KEY (Username) REFERENCES Accounts(username)
);
-- Script cho bảng "Products"
CREATE TABLE Products (
    Id INT PRIMARY KEY IDENTITY NOT NULL,
    Name NVARCHAR(255) NOT NULL,
    Image NVARCHAR(255),
    Price FLOAT NOT NULL,
    CreateDate DATE,
    Available BIT NOT NULL,
    CategoryId NVARCHAR(255),
    FOREIGN KEY (Categoryid) REFERENCES Categories(id)
);

-- Script cho bảng "Orderdetails"
CREATE TABLE OrderDetails (
    Id BIGINT PRIMARY KEY IDENTITY NOT NULL,
    Price FLOAT NOT NULL,
    Quantity INT NOT NULL,
    ProductId INT NOT NULL,
    OrderId BIGINT NOT NULL,
    FOREIGN KEY (Productid) REFERENCES Products(id),
    FOREIGN KEY (Orderid) REFERENCES Orders(id)
);


-- insert
-- Insert data into "Roles" table
INSERT INTO Roles (Id, Name) VALUES 
('CUST', 'Customers'),
('DIRE', 'Directors'),
('STAF', 'Staffs');

-- Insert data into "Accounts" table
INSERT INTO Accounts (Username, Password, Fullname, Email, Photo) VALUES 
('user1', 'pass1', 'User One', 'user1@example.com', NULL),
('user2', 'pass2', 'User Two', 'user2@example.com', NULL),
('user3', 'pass3', 'User Three', 'user3@example.com', NULL),
('user4', 'pass4', 'User Four', 'user4@example.com', NULL),
('user5', 'pass5', 'User Five', 'user5@example.com', NULL);

-- Insert data into "Authorities" table
INSERT INTO Authorities (Username, RoleId) VALUES 
('user1', 'CUST'),
('user2', 'DIRE'),
('user3', 'STAF'),
('user4', 'CUST'),
('user5', 'DIRE');

-- Insert data into "Categories" table
INSERT INTO Categories (Id, Name) VALUES 
('CAT1', 'Category One'),
('CAT2', 'Category Two'),
('CAT3', 'Category Three'),
('CAT4', 'Category Four'),
('CAT5', 'Category Five');

-- Insert data into "Orders" table
INSERT INTO Orders (Address, CreateDate, Username) VALUES 
('Address1', '2023-11-27', 'user1'),
('Address2', '2023-11-27', 'user2'),
('Address3', '2023-11-27', 'user3'),
('Address4', '2023-11-27', 'user4'),
('Address5', '2023-11-27', 'user5');

-- Insert data into "Products" table
INSERT INTO Products (Name, Image, Price, CreateDate, Available, CategoryId) VALUES 
('Product1', 'image1.jpg', 10.99, '2023-11-27', 1, 'CAT1'),
('Product2', 'image2.jpg', 20.99, '2023-11-27', 1, 'CAT2'),
('Product3', 'image3.jpg', 30.99, '2023-11-27', 1, 'CAT3'),
('Product4', 'image4.jpg', 40.99, '2023-11-27', 1, 'CAT4'),
('Product5', 'image5.jpg', 50.99, '2023-11-27', 1, 'CAT5');


-- Insert data into "OrderDetails" table
INSERT INTO OrderDetails (Price, Quantity, ProductId, OrderId) VALUES 
(10.99, 2, 1, 1),
(20.99, 1, 2, 2),
(30.99, 3, 3, 3),
(40.99, 4, 4, 4),
(50.99, 5, 5, 5);








---- kết thúc 

