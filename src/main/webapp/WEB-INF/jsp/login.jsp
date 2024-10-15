<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="include/header.jsp" />

<!-- a page header -->
<section class="custom-section">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center raleway-bold" style="color:white; font-size: 60px;">LOG IN</h1>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-10">
                <div class="form-container">
                    <c:if test="${param['error'] eq ''}">
                        <div class="row pt-5 justify-content-center">
                            <div class="col-6">
                                <div class="alert alert-danger" role="alert">Invalid Username or Password</div>
                            </div>
                        </div>
                    </c:if>

                    <div class="row pt-5 ">
                        <div class="col-12">
                            <form action="/user/loginProcessingURL" method="post">


                                <!-- Email Input Field -->
                                <div class="row align-items-center justify-content-center pb-3">
                                    <div class="col-2">
                                        <label for="emailId" class="col-form-label raleway-bold" style="color:white">Email</label>
                                    </div>
                                    <div class="col-4">
                                        <input type="text"
                                               id="emailId"
                                               name="username"
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
                                    <div class="col-2">
                                        <label for="passwordId" class="col-form-label raleway-bold" style="color:white">Password</label>
                                    </div>
                                    <div class="col-4">
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


                                <div class="row justify-content-center ">
                                    <div class="col-auto text-center">
                                        <button type="submit" class="btn btn-light">Submit</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>



<jsp:include page="include/footer.jsp" />