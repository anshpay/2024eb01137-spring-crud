<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Authors</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<nav>
    <strong>Library Manager</strong>
    <a href="/books">Books</a>
    <a href="/authors">Authors</a>
    <a class="button" href="/authors/new">Add Author</a>
</nav>
<main>
    <h1>Authors</h1>
    <c:if test="${not empty success}"><div class="alert success">${success}</div></c:if>
    <table>
        <thead><tr><th>ID</th><th>Name</th><th>Specialization</th><th>Email</th><th>Action</th></tr></thead>
        <tbody>
        <c:forEach var="author" items="${authors}">
            <tr>
                <td>${author.id}</td>
                <td>${author.name}</td>
                <td>${author.specialization}</td>
                <td>${author.email}</td>
                <td><a href="/authors/${author.id}/edit">Update</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
</body>
</html>
