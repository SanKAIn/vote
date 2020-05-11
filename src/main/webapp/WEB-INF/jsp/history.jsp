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
<script type="text/javascript" src="resources/js/myvote.history.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">HISTORY</h3>
        <div class="card border-dark">
            <div class="card-body pb-0">
                <form id="filter">
                    <div class="row">
                        <div class="offset-1 col-2">
                            <label for="startDate">Start Date</label>
                            <input class="form-control" name="startDate" id="startDate" autocomplete="off">
                        </div>
                        <div class="offset-2 col-2">
                            <label for="endDate">End Date</label>
                            <input class="form-control" name="endDate" id="endDate" autocomplete="off">
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-footer text-right">
                <button class="btn btn-danger" onclick="clearFilter()">
                    <span class="fa fa-remove"></span>
                    Cancel
                </button>
                <button class="btn btn-primary" onclick="updateFilteredTable()">
                    <span class="fa fa-filter"></span>
                    Filter
                </button>
            </div>
        </div>
        <br/>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th>Date</th>
                <th>Name</th>
                <th>Menu</th>
                <th>Votes</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
