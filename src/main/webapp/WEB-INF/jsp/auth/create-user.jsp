<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<div class="container mt-5 comic-theme">
    <h1 class="text-center mb-4 page-title">CREATE ACCOUNT</h1>
        </div>


<section>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-10">
                <div class="form-container">
                    <form action="/user/create-user" method="post">


                        <!-- Email Input Field -->
                        <div class="row align-items-center justify-content-center pb-3">
                            <div class="col-2 pe-4 text-end">
                                <label for="emailId" class="col-form-label raleway-bold" style="color: white;">Email</label>
                            </div>
                            <div class="col-4 ps-1">
                                <input type="text"
                                       id="emailId"
                                       name="email"
                                       class="form-control <c:if test="${bindingResult.hasFieldErrors('email')}">is-invalid</c:if>"
                                       value="${form.email}">
                            </div>
                        </div>
                        <c:if test="${bindingResult.hasFieldErrors('email')}">
                            <div class="row align-items-center justify-content-center">
                                <div class="offset-2 col-4">
                                    <div style="color:red">
                                        <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                                            ${error.defaultMessage}<br>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                        <!-- Password Input Field -->
                        <div class="row align-items-center justify-content-center pb-3">
                            <div class="col-2 pe-4 text-end">
                                <label for="passwordId" class="col-form-label raleway-bold" style="color: white;">Password</label>
                            </div>
                            <div class="col-4 ps-1">
                                <input type="text" id="passwordId" name="password" class="form-control <c:if test="${bindingResult.hasFieldErrors('password')}">is-invalid</c:if>" value="${form.password}">
                            </div>
                        </div>
                        <c:if test="${bindingResult.hasFieldErrors('password')}">
                            <div class="row align-items-center justify-content-center">
                                <div class="offset-2 col-4">
                                    <div style="color:red">
                                        <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                                            ${error.defaultMessage}<br>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                        <!-- Name Input Field -->
                        <div class="row align-items-center justify-content-center pb-3">
                            <div class="col-2 pe-4 text-end">
                                <label for="nameId" class="col-form-label raleway-bold" style="color: white;">Full Name</label>
                            </div>
                            <div class="col-4 ps-1">
                                <input type="text" id="nameId" name="name" class="form-control <c:if test="${bindingResult.hasFieldErrors('name')}">is-invalid</c:if>" value="${form.name}">
                            </div>
                        </div>
                        <c:if test="${bindingResult.hasFieldErrors('name')}">
                            <div class="row align-items-center justify-content-center">
                                <div class="offset-2 col-4">
                                    <div style="color:red">
                                        <c:forEach items="${bindingResult.getFieldErrors('name')}" var="error">
                                            ${error.defaultMessage}<br>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                        <!-- Newsletter Signup Checkbox -->
                        <div class="row align-items-center justify-content-center pb-3">
                            <div class="col-6 text-center">
                                <div class="form-check d-flex justify-content-center">
                                    <input class="form-check-input me-2" type="checkbox" value="" id="flexCheckDefault">
                                    <label class="form-check-label raleway-bold" for="flexCheckDefault" style="color: white;">
                                        I want to sign up for newsletter emails!
                                    </label>
                                </div>
                            </div>
                        </div>


                        <div class="row justify-content-center ">
                            <div class="col-auto text-center">
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>



<jsp:include page="../include/footer.jsp" />