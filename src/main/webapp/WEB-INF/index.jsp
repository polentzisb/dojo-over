<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Question Dashboard</title>
</head>
<body>
	<div class="container">
		<h1>Questions Dashboard</h1>
		<table class="table">
                <thead>
                    <tr>
                        <th scope="col">Question</th>
                        <th scope="col">Tags</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${questions}" var="question">
                    <tr>
                        <td><a href="/questions/${question.id}">${question.questionText}</a></td>
                        <td><c:forEach items="${ question.tags }" var="tag">
									<a>- ${ tag.subject }</a>
								</c:forEach></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="/questions/new">New Question</a>
        </div>

</body>
</html>