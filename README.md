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

In OMS you can store, cretae and cahange

