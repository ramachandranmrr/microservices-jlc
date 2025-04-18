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
         <div class="container">
            <table class="table table-striped table-bordered table-light myfont">
               <tr>
                  <th>Book ID</th>
                  <td> ${MyBookInfo.bookId } </td>
               </tr>
               <tr>
                  <th>Book Name</th>
                  <td>${MyBookInfo.bookName }</td>
               </tr>
               <tr>
                  <th>Author</th>
                  <td>${MyBookInfo.author }</td>
               </tr>
               <tr>
                  <th>Publications </th>
                  <td>${MyBookInfo.publications }</td>
               </tr>
               <tr>
                  <th>Category</th>
                  <td>${MyBookInfo.category }</td>
               </tr>
               <tr>
                  <th>Price </th>
                  <td>Rs. ${MyBookInfo.price }</td>
               </tr>
               <tr>
                  <th>Offer </th>
                  <td><span class="mytext-large btn-danger"> ${MyBookInfo.offer }% Off </span></td>
               </tr>
               <tr>
                  <th>Ratings </th>
                  <td>${MyBookInfo.avgRating }</td>
               </tr>
            </table>
         </div>
         <div class="container" align="center">
            <h1 class="text-center">
               <a href="continueShopping" class="btn btn-danger"> Continue Shopping</a>
            </h1>
         </div>
         <br/>
         <c:import url="myfooter.jsp"/>
   </body>
</html>