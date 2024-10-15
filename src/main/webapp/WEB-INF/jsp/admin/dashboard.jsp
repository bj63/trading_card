<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../include/header.jsp" />

<div class="container mt-5 comic-theme">
    <h1 class="text-center mb-4 page-title">Admin Dashboard</h1>
        </div><

<section>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-12">
                <div class="form-container-two mx-auto">
                    <div class="mb-3">
                        <a href="${pageContext.request.contextPath}/admin/createCard" class="btn btn-primary btn-sm">Create Card</a>
                        <a href="${pageContext.request.contextPath}/admin/userSearch" class="btn btn-secondary btn-sm">Search User</a>
                        <a href="${pageContext.request.contextPath}/admin/cardSearch" class="btn btn-secondary btn-sm">Search Card</a>

                    </div>

                    <table class="table table-bordered table-responsive raleway-normal">
                        <thead class="table-dark">
                        <tr>
                            <th>Card ID</th>
                            <th>Player Name</th>
                            <th>Player Jersey</th>
                            <th>Team</th>
                            <th>Price</th>
                            <th>Available Copies</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${cards}" var="card">
                            <tr>
                                <td>${card.cardId}</td>
                                <td>${card.playerName}</td>
                                <td>${card.cardNumber}</td>
                                <td>${card.teamName}</td>
                                <td>${card.buyPrice}</td>
                                <td>${card.availableCopies}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/editCard?cardId=${card.cardId}" class="btn btn-light">Edit</a>
                                    <form action="${pageContext.request.contextPath}/admin/deleteCard" method="post" style="display:inline;">
                                        <input type="hidden" name="cardId" value="${card.cardId}" />
                                        <button type="submit" onclick="return confirm('Are you sure you want to delete this card?');" class="btn btn-danger btn-sm">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp" />
