# OMS - Order Management System

In OMS you can store and create clients (named "Employee" in code), products and orders. Also clients and products are editable.
Orders are sorted by order number, barcode and client security code.

Installation:

Eclipse:

- make sure you have gradle installed in eclipse
- download code from this repo
- unpack
- import gradle project
- in gradle tasks - 'init'
- run on jetty - jettyRun
- go to: localhost:8080/oms


h2 database:

name: 'oms'
location: "~/Documents/GitHub/OMS/src/main/oms"
username: "sa"
password: ""



NB! Currently database file is located in my local direcoty (see location). To connect to database, please check location in each method of 
the following files:

- com/demo/h2/DBUtils.java
- demo/dao/EmployeeDAOImpl.java
- demo/dao/ProductDAOImpl.java
- demo/dao/OrderDAOImpl.java

Other comments:

- when you add order, please makse sure that client security code is in table "Client". Otherwise NullPointerException raised.
- security code, order number and barcode are type of Integer.
- price and converted price are type of Integer.
- date are type of String.
- basic unfctionality implemented in com.demo.dao.*

Unused files:

- com.demo.h2.*
- com..h2.*
- com.demo.controller.DropDownBoxController.java
- com.demo.edit.*
- all .jsp files except index.jsp
