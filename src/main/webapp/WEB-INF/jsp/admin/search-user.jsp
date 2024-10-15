<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../include/header.jsp" />

<!-- Page header -->
<div class="container mt-5 comic-theme">
    <h1 class="text-center mb-4 page-title">User Search</h1>
        </div>


<section>
    <div class="container">
        <div class="row justify-content-center pt-5 pb-3">
            <div class="col-8 text-center">
                <form action="${pageContext.request.contextPath}/admin/userSearch">
                    <div class="mb-3">
                        <input type="text" value="${searchTerm}" name="searchTerm" class="form-control" placeholder="Search by username or email">
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
                        <th>User ID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.role}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/userDetails?userId=${user.id}" class="btn btn-light">Details</a>
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
