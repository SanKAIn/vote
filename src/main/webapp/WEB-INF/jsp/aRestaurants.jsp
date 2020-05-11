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
<script type="text/javascript" src="resources/js/admin.restaurants.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">Restaurants</h3>
        <br/>
        <sec:authorize access="hasRole('ADMIN')">
            <button class="btn btn-primary" onclick="add()">
                <span class="fa fa-plus"></span>
                Add
            </button>
        </sec:authorize>
<%--        <a href="/add">Add</a>--%>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th>Name</th>
                <th>Voted</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismis="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="restaurantName" class="col-form-label">Название ресторана</label>
                        <input type="text" class="form-control" id="restaurantName" name="name" value=""
                               placeholder="Название ресторана">
                    </div>

                    <form id="dish1">
                        <input type="hidden" id="dish1id" name="dish1id" value="null">
                        <label for="dish1name" class="col-form-label">Блюдо 1</label>
                        <input type="text" class="form-control" id="dish1name" name="dish1name" placeholder="Название блюда">
                        <input type="number" class="form-control" id="dish1cost" name="dish1cost" placeholder="Цена блюда">
                        <button type="button" class="btn btn-primary" onclick="save2(1)">
                            <span class="fa fa-close"></span>Save
                        </button>
                        <button type="button" class="btn btn-secondary" onclick="deleteRow2(1)">
                            <span class="fa fa-check"></span>Delete
                        </button>
                    </form>

                    <form id="dish2">
                        <input type="hidden" id="dish2id" name="dish2id">
                        <label for="dish2name" class="col-form-label">Блюдо 2</label>
                        <input type="text" class="form-control" id="dish2name" name="dish2name" placeholder="Название блюда">
                        <input type="number" class="form-control" id="dish2cost" name="dish2cost" placeholder="Цена блюда">
                        <button type="button" class="btn btn-primary" onclick="save2(2)">
                            <span class="fa fa-close"></span>Save
                        </button>
                        <button type="button" class="btn btn-secondary" onclick="deleteRow2(2)">
                            <span class="fa fa-check"></span>Delete
                        </button>
                    </form>

                    <form id="dish3">
                        <input type="hidden" id="dish3id" name="dish3id">
                        <label for="dish3name" class="col-form-label">Блюдо 3</label>
                        <input type="text" class="form-control" id="dish3name" name="dish3name" placeholder="Название блюда">
                        <input type="number" class="form-control" id="dish3cost" name="dish3cost" placeholder="Цена блюда">
                        <button type="button" class="btn btn-primary" onclick="save2(3)">
                            <span class="fa fa-close"></span>Save
                        </button>
                        <button type="button" class="btn btn-secondary" onclick="deleteRow2(3)">
                            <span class="fa fa-check"></span>Delete
                        </button>
                    </form>

                    <form class="form-group" id="4">
                        <input type="hidden" id="dish4id" name="dish4id">
                        <label for="dish4name" class="col-form-label">Блюдо 4</label>
                        <input type="text" class="form-control" id="dish4name" name="dish4name" placeholder="Название блюда">
                        <input type="number" class="form-control" id="dish4cost" name="dish4cost" placeholder="Цена блюда">
                        <button type="button" class="btn btn-primary" onclick="save2(4)">
                            <span class="fa fa-close"></span>Save
                        </button>
                        <button type="button" class="btn btn-secondary" onclick="deleteRow2(4)">
                            <span class="fa fa-check"></span>Delete
                        </button>
                    </form>

                    <form class="form-group" id="5">
                        <input type="hidden" id="dish5id" name="dish5id">
                        <label for="dish5name" class="col-form-label">Блюдо 5</label>
                        <input type="text" class="form-control" id="dish5name" name="dish5name" placeholder="Название блюда">
                        <input type="number" class="form-control" id="dish5cost" name="dish5cost" placeholder="Цена блюда">
                        <button type="button" class="btn btn-primary" onclick="save2(5)">
                            <span class="fa fa-close"></span>Save
                        </button>
                        <button type="button" class="btn btn-secondary" onclick="deleteRow2(5)">
                            <span class="fa fa-check"></span>Delete
                        </button>
                    </form>

                    <input type="hidden" id="vote" name="vote" value="0">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>Отмена
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>Сохранить
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
