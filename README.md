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

Command line:

-  download code from this repo
-  unpack
-  go to project root diretory
-  open command line: WinKey + R and type "cmd"
-  type "gradle init"
-  if build successfull, type "gradle jettyRun"
-  go to: localhost:8080/oms

h2 database:

NB! Once new db is installed in you home direcory, there is no tables and, therefore no data. Once you click "View details" on the home page, relevant table is automatically created. However, cause tables have no data, user will not see any details unless information is manually added.

NB! Used database files is copied to /src/main. So you can place this file to you home direcoty and use it. This files already has
necessary tables and data.

name: 'oms'
location: "~/oms"
username: "sa"
password: ""

Once project is laucnhed, go to http://localhost:8080/oms/console to access database.


NB! Connection to database is shown in the following files:

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
- screenshots from interface are in /src/main/pic/
- as there was no information about erorr handling in task description, please note that
     * no duplicate checks are implemented
     * it is NOT possible to add order if client does not exist in database
     * no erro are shown, user just redirested no relevant tag and do data is added
- as there was no info about deleting or editing order, these buttons were not created

Unused files:

- com.demo.h2.* (except DBUtils.java file)
- com.h2.*
- com.demo.controller.DropDownBoxController.java
- com.demo.edit.*
- all .jsp files except index.jsp
