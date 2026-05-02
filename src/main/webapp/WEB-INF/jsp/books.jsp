<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<nav>
    <strong>Library Manager</strong>
    <a href="/books">Books</a>
    <a href="/authors">Authors</a>
    <a class="button" href="/books/new">Add Book</a>
</nav>
<main>
    <h1>Books</h1>
    <c:if test="${not empty success}"><div class="alert success">${success}</div></c:if>
    <section>
        <h2>Book List</h2>
        <table>
            <thead><tr><th>ID</th><th>Title</th><th>ISBN</th><th>Year</th><th>Author</th><th>Action</th></tr></thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.isbn}</td>
                    <td>${book.publishedYear}</td>
                    <td>${book.author.name}</td>
                    <td><a href="/books/${book.id}/edit">Update</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
    <section>
        <h2>Inner Join Result</h2>
        <table>
            <thead><tr><th>Book</th><th>ISBN</th><th>Author</th><th>Specialization</th></tr></thead>
            <tbody>
            <c:forEach var="row" items="${bookAuthorDetails}">
                <tr>
                    <td>${row.title}</td>
                    <td>${row.isbn}</td>
                    <td>${row.authorName}</td>
                    <td>${row.specialization}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
</main>
</body>
</html>
