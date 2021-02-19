drop table person if exists;
CREATE TABLE person(
  id int primary key,
  name varchar(50),
  age int,
  sex int
);
drop table Customers if exists;
CREATE TABLE Customers
(
  cust_id      char(10)  NOT NULL ,
  cust_name    char(50)  NOT NULL ,
  cust_address char(50)  NULL ,
  cust_city    char(50)  NULL ,
  cust_state   char(5)   NULL ,
  cust_zip     char(10)  NULL ,
  cust_country char(50)  NULL ,
  cust_contact char(50)  NULL ,
  cust_email   char(255) NULL 
);

drop table OrderItems if exists;
CREATE TABLE OrderItems
(
  order_num  int          NOT NULL ,
  order_item int          NOT NULL ,
  prod_id    char(10)     NOT NULL ,
  quantity   int          NOT NULL ,
  item_price decimal(8,2) NOT NULL 
);

drop table Orders if exists;
CREATE TABLE Orders
(
  order_num  int      NOT NULL ,
  order_date date     NOT NULL ,
  cust_id    char(10) NOT NULL 
);

drop table Products if exists;
CREATE TABLE Products
(
  prod_id    char(10)      NOT NULL ,
  vend_id    char(10)      NOT NULL ,
  prod_name  char(255)     NOT NULL ,
  prod_price decimal(8,2)  NOT NULL ,
  prod_desc  varchar(1000) NULL 
);

drop table Vendors if exists;
CREATE TABLE Vendors
(
  vend_id      char(10) NOT NULL ,
  vend_name    char(50) NOT NULL ,
  vend_address char(50) NULL ,
  vend_city    char(50) NULL ,
  vend_state   char(5)  NULL ,
  vend_zip     char(10) NULL ,
  vend_country char(50) NULL 
);

ALTER TABLE Customers ADD CONSTRAINT PK_Customers PRIMARY KEY (cust_id);
ALTER TABLE OrderItems ADD CONSTRAINT PK_OrderItems PRIMARY KEY (order_num, order_item);
ALTER TABLE Orders ADD CONSTRAINT PK_Orders PRIMARY KEY (order_num);
ALTER TABLE Products ADD CONSTRAINT PK_Products PRIMARY KEY (prod_id);
ALTER TABLE Vendors ADD CONSTRAINT PK_Vendors PRIMARY KEY (vend_id);

ALTER TABLE OrderItems
ADD CONSTRAINT FK_OrderItems_Orders FOREIGN KEY (order_num) REFERENCES Orders (order_num);
ALTER TABLE OrderItems
ADD CONSTRAINT FK_OrderItems_Products FOREIGN KEY (prod_id) REFERENCES Products (prod_id);
ALTER TABLE Orders
ADD CONSTRAINT FK_Orders_Customers FOREIGN KEY (cust_id) REFERENCES Customers (cust_id);
ALTER TABLE Products
ADD CONSTRAINT FK_Products_Vendors FOREIGN KEY (vend_id) REFERENCES Vendors (vend_id);
