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
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Question Profile</title>
</head>
<body>
	<div class="container">
		<h1>${question.questionText}</h1>
		<h2>
			Tags:
			<c:forEach items="${ question.tags }" var="tag">
									 ${ tag.subject }
								</c:forEach>
		</h2>
		<div class="question_info">
			<div class="left">
				<table class="table table-hover">
				<thead>
						<tr>
							<th>Answers</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items='${ question.answers }' var='answer'>
							<tr>
								<td>
									<h5>${ answer.answerText }</h5>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="right">
				<h2>Add your Answer:</h2>
				<form:form action="/questions/${question.id}" method="post"	modelAttribute="answer">
					<div class="row">
						<div class="col-md-6 mb-3">
							<form:label path="answerText">Question: </form:label>
							<form:textarea path="answerText" />
							<form:errors path="answerText" />
						</div>
					</div>
					<input type="submit" value="Answer it!" />
			</div>
			</form:form>

		</div>
	</div>
	</div>

</body>
</html>