<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<!-- Page header -->
<<div class="container mt-5 comic-theme">
<h1 class="text-center mb-4 page-title">Edit Account</h1>
        </div>


<section>
    <div class="container">
        <div class="row pt-5">
            <div class="col-12">
                <form action="/admin/editUser" method="post">
                    <!-- CSRF Token -->
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <!-- Email Input Field -->
                    <div class="row align-items-center justify-content-center pb-3">
                        <div class="col-2">
                            <label for="email" class="col-form-label" style="color: white;">Email</label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="email"
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

                    <!-- Full Name Input Field -->
                    <div class="row align-items-center justify-content-center pb-3">
                        <div class="col-2">
                            <label for="name" class="col-form-label" style="color: white;">Full Name</label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="name"
                                   name="name"
                                   class="form-control <c:if test="${bindingResult.hasFieldErrors('name')}">is-invalid</c:if>"
                                   value="${form.name}">
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

                    <!-- Hidden ID Field -->
                    <input type="hidden" name="id" value="${form.id}">

                    <!-- Submit Button -->
                    <div class="row justify-content-center">
                        <div class="col-auto text-center">
                            <button type="submit" class="btn btn-light">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp" />
