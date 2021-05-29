<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head >
    <meta charset="utf-8">
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<h1>Welcome</h1>
<h1>Tool Auto</h1>

<form action="${pageContext.request.contextPath}/toolAddFromExcel" method="post" enctype = "multipart/form-data">
    <h1>File</h1>
    <input type="file" name="myFile" /><br /><br />
    <input type="submit" value="Import database">
</form>

<form action="${pageContext.request.contextPath}/toolDeleteAll" method="delete">
    <input type="submit" value="Delete Database"
           name="Submit" id="frm1_submit" />
</form>



</body>

</html>