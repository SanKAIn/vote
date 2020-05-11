<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/myvote.common.js" defer></script>
<script type="text/javascript" src="resources/js/profile.restaurants.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">Restaurants</h3>
        <br/>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th>Name</th>
                <th>Dish 1</th>
                <th>Dish 2</th>
                <th>Dish 3</th>
                <th>Dish 4</th>
                <th>Dish 5</th>
                <th>Voted</th>
                <th>to vote</th>
                <sec:authorize access="hasRole('ADMIN')">
                    <th></th>
                    <th></th>
                </sec:authorize>
            </tr>
            </thead>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
