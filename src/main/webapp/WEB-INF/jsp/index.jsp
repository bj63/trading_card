<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Include the navigation bar -->
<jsp:include page="include/header.jsp" />
<div class="container mt-5 comic-theme">
    <h1 class="text-center mb-4 page-title">Welcome to Trading Card Collection</h1>

    <div class="card-grid">
        <c:forEach items="${cards}" var="card">
            <div class="flip-card">
                <div class="flip-card-inner">
                    <div class="flip-card-front">
                        <img src="${card.imageUrl}" class="card-img-top" alt="${card.playerName}">
                    </div>
                    <div class="flip-card-back">
                        <h5 class="card-title">${card.playerName}</h5>
                        <p class="card-subtitle">Team: ${card.teamName}</p>
                        <p class="card-text">Card Number: ${card.cardNumber}</p>
                        <p class="card-subtitle">Available Copies: ${card.availableCopies}</p>
                        <p class="card-text">Buy Price: ${card.buyPrice}</p>
                        <div class="button-group">
                            <a href="/card/detail/${card.cardId}" class="btn btn-primary comic-btn">View Details</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="include/footer.jsp" />
