<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../include/header.jsp" />

<!-- Page Header -->
    <div class="container mt-5 comic-theme">
        <h1 class="text-center mb-4 page-title">${card.playerName}</h1>


<section>
    <div class="container">
        <div class="row justify-content-center custom-section raleway-normal">
            <!-- Column for the cover image -->
            <div class="col-md-4 d-flex align-items-center">
                <img src="${card.imageUrl}" alt="Card Image" class="img-fluid" />
            </div>

            <!-- Column for the details -->
            <div class="col-md-8">
                <table class="table">
                    <tr>
                        <td style="font-weight: bolder;">Card ID:</td>
                        <td>${card.cardId}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder;">Player Name:</td>
                        <td>${card.playerName}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder;">Player Jersey #:</td>
                        <td>${card.cardNumber}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder;">Team:</td>
                        <td>${card.teamName}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder;">Buy Price:</td>
                        <td><fmt:formatNumber value="${card.buyPrice}" type="currency" /></td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder;">Available Copies:</td>
                        <td>${card.availableCopies}</td>
                    </tr>
                    <tr>
                        <td colspan="2" class="text-center">
                            <form action="/user/trade" method="post">
                                <input type="hidden" name="cardId" value="${card.cardId}">
                                <button type="submit" class="btn btn-light">Trade</button>
                            </form>
                        </td>
                    <tr>
                        <td colspan="2" class="text-center">
                            <form action="/order/addToCart" method="post">
                                <input type="hidden" name="cardId" value="${card.cardId}">
                                <button type="submit" class="btn btn-light">Buy</button>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp" />
