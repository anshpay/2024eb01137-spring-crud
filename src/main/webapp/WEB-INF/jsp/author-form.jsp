<!DOCTYPE html>
<html>
<head>
    <title>Add Author</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<nav><strong>Library Manager</strong><a href="/books">Books</a><a href="/authors">Authors</a></nav>
<main class="form-page">
    <h1>Add Author</h1>
    <p class="alert error">${error}</p>
    <form method="post" action="/authors">
        <label>Name <input name="name" value="${author.name}" required></label>
        <label>Specialization <input name="specialization" value="${author.specialization}" required></label>
        <label>Email <input type="email" name="email" value="${author.email}" required></label>
        <button type="submit">Save Author</button>
    </form>
</main>
</body>
</html>
