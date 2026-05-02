<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Book</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<nav><strong>Library Manager</strong><a href="/books">Books</a><a href="/authors">Authors</a></nav>
<main class="form-page">
    <h1>Add Book</h1>
    <c:if test="${not empty error}"><div class="alert error">${error}</div></c:if>
    <form method="post" action="/books">
        <label>Title <input name="title" value="${book.title}" required></label>
        <label>ISBN <input name="isbn" value="${book.isbn}" required></label>
        <label>Published Year <input type="number" name="publishedYear" value="${book.publishedYear}" min="1800" required></label>
        <label>Author
            <select name="authorId" required>
                <c:forEach var="author" items="${authors}">
                    <option value="${author.id}">${author.name}</option>
                </c:forEach>
            </select>
        </label>
        <button type="submit">Save Book</button>
    </form>
</main>
</body>
</html>
