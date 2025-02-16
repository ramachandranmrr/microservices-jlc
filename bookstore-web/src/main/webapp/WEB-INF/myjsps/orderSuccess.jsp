<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
   <head>
      <title>JLC Bookstore</title>
      <link href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css" rel="stylesheet">
      <link href="mycss/bookstore.css" rel="stylesheet">
   </head>
   <body>
      <div class="card">
         <c:import url="myheader.jsp"/>
      </div>
      <body>
         <br/><br/>
         <br/><br/>
         <div class="container">
            <h1 class="text-center"> Your Order has been placed successfully </h1>
         </div>
         <br/><br/>
         <br/><br/>
         <div class="container" align="center">
            <h1 class="text-center">
               <a href="continueShopping" class="btn btn-danger"> Continue Shopping</a>
            </h1>
         </div>
         <br/><br/><br/><br/> <br/>
         <c:import url="myfooter.jsp"/>
   </body>
</html>