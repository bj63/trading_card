<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../include/header.jsp" />

<!-- Page header -->
<<div class="container mt-5 comic-theme">
<h1 class="text-center mb-4 page-title">ard Search</h1>
        </div>


<section>
    <div class="container">
        <div class="row justify-content-center pt-5 pb-3">
            <div class="col-8 text-center">
                <form action="${pageContext.request.contextPath}/admin/cardSearch">
                    <div class="mb-3">
                        <input type="text" value="${searchTerm}" name="searchTerm" class="form-control" placeholder="Search by player name or team">
                    </div>
                    <button type="submit" class="btn btn-light">Search</button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <table class="table table-bordered table-responsive raleway-normal">
                    <thead class="table-dark">
                    <tr>
                        <th>Card ID</th>
                        <th>Player Name</th>
                        <th>Team</th>
                        <th>Price</th>
                        <th>Available Copies</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cards}" var="card">
                        <tr>
                            <td>${card.id}</td>
                            <td>${card.playerName}</td>
                            <td>${card.teamName}</td>
                            <td>${card.buyPrice}</td>
                            <td>${card.availableCopies}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/editCard?cardId=${card.id}" class="btn btn-light">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp" />
