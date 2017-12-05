<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
		<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
		<title>Maven + Spring MVC</title>
		<script src="/resources/core/js/hello.js"></script>
		<link href="/resources/core/css/hello.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		${user.getUsername()}
	</body>
</html>