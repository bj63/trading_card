<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../include/header.jsp" />

<!-- Page header -->
    <div class="container mt-5 comic-theme">
        <h1 class="text-center mb-4 page-title">Create Card</h1>
        </div>

<section>
    <div class="container">
        <div class="row pt-5">
            <div class="col-md-8 offset-md-2">
                <form action="/admin/createCard" method="post" enctype="multipart/form-data">

                    <!-- CSRF token -->
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <!-- Error message handling -->
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger">
                                ${errorMessage}
                        </div>
                    </c:if>

                    <!-- Player Name Input Field -->
                    <div class="mb-3">
                        <label for="playerName" class="form-label" style="color:white;">Player Name</label>
                        <input type="text"
                               id="playerName"
                               name="playerName"
                               class="form-control <c:if test='${bindingResult.hasFieldErrors("playerName")}'>is-invalid</c:if>"
                               value="${form.playerName}"
                               required>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('playerName')}">
                        <div style="color:red">
                            <c:forEach items="${bindingResult.getFieldErrors('playerName')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>

                    <!-- Team Name Input Field -->
                    <div class="mb-3">
                        <label for="teamName" class="form-label" style="color:white;">Team</label>
                        <input type="text"
                               id="teamName"
                               name="teamName"
                               class="form-control <c:if test='${bindingResult.hasFieldErrors("teamName")}'>is-invalid</c:if>"
                               value="${form.teamName}"
                               required>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('teamName')}">
                        <div style="color:red">
                            <c:forEach items="${bindingResult.getFieldErrors('teamName')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>

                    <!-- Price Input Field -->
                    <div class="mb-3">
                        <label for="buyPrice" class="form-label" style="color:white;">Price</label>
                        <input type="number"
                               step="0.01"
                               id="buyPrice"
                               name="buyPrice"
                               class="form-control <c:if test='${bindingResult.hasFieldErrors("buyPrice")}'>is-invalid</c:if>"
                               value="${form.buyPrice}"
                               required>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('buyPrice')}">
                        <div style="color:red">
                            <c:forEach items="${bindingResult.getFieldErrors('buyPrice')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>
                    <!-- Card Number Input Field -->
                    <div class="mb-3">
                        <label for="cardNumber" class="form-label" style="color:white;">Player Jersey Number</label>
                        <input type="number"
                               id="cardNumber"
                               name="cardNumber"
                               class="form-control <c:if test='${bindingResult.hasFieldErrors("cardNumber")}'>is-invalid</c:if>"
                               value="${form.cardNumber}"
                               required>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('cardNumber')}">
                        <div style="color:red">
                            <c:forEach items="${bindingResult.getFieldErrors('cardNumber')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>

                    <!-- Available Copies Input Field -->
                    <div class="mb-3">
                        <label for="availableCopies" class="form-label" style="color:white;">Available Copies</label>
                        <input type="number"
                               id="availableCopies"
                               name="availableCopies"
                               class="form-control <c:if test='${bindingResult.hasFieldErrors("availableCopies")}'>is-invalid</c:if>"
                               value="${form.availableCopies}"
                               required>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('availableCopies')}">
                        <div style="color:red">
                            <c:forEach items="${bindingResult.getFieldErrors('availableCopies')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>

                    <!-- Image Upload Field -->
                    <div class="mb-3">
                        <label for="image" class="form-label" style="color:white;">Image</label>
                        <input type="file"
                               id="image"
                               value="${form.image}"
                               name="image"
                               class="form-control">
                    </div>

                    <!-- Submit Button -->
                    <div class="row justify-content-center">
                        <div class="col-auto text-center">
                            <button type="submit" class="btn btn-primary">Create Card</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp" />

