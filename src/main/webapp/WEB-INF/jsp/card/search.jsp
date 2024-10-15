<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<!-- Page Header -->
<section class="custom-section">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center raleway-bold" style="color:white; font-size: 60px;">CARD SEARCH</h1>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row justify-content-center pt-5 pb-3">
            <div class="col-8 text-center">
                <form action="/card">
                    <div class="mb-3">
                        <input type="text" value="${searchTerm}" class="form-control" id="cardSearch" name="search" placeholder="Search For A Card"/>
                    </div>
                    <select class="form-select" aria-label="Default select example" name="team">
                        <option selected>Or Select A Team</option>
                        <option value="Team A" ${selectedTeam == 'Team A' ? 'selected' : ''}>Team A</option>
                        <option value="Team B" ${selectedTeam == 'Team B' ? 'selected' : ''}>Team B</option>
                        <!-- Add more teams as needed -->
                    </select>
                    <button type="submit" class="btn btn-light">Search</button>
                </form>
            </div>
        </div>
    </div>
</section>

<section>
    <div class="container custom-section">
        <div class="row pt-5">
            <div class="col-12">
                <h2 class="text-center raleway-normal" style="color: white;">Cards Found (${cards.size()})</h2>
            </div>
        </div>
    </div>
    <div class="row raleway-normal custom-section">
        <div class="col-12">
            <table class="table">
                <tr>
                    <th style="color: white;">Card Id</th>
                    <th style="color: white;">Player Name</th>
                    <th style="color: white;">Team</th>
                    <th style="color: white;">Price</th>
                    <th style="color: white;">Available Copies</th>
                    <th style="color: white;">Actions</th>
                </tr>
                <c:forEach items="${cards}" var="card">
                    <tr>
                        <td style="color: white;">${card.id}</td>
                        <td style="color: white;">${card.playerName}</td>
                        <td style="color: white;">${card.teamName}</td>
                        <td style="color: white;">${card.buyPrice}</td>
                        <td style="color: white;">${card.availableCopies}</td>
                        <td style="color: white;">
                            <a href="${pageContext.request.contextPath}/card/detail/${card.id}" class="btn btn-sm btn-info me-2 btn-light">View Details</a>
                            <form action="/user/trade" method="post" class="d-inline">
                                <input type="hidden" name="cardId" value="${card.id}">
                                <button type="submit" class="btn btn-sm btn-light">Trade</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp" />

