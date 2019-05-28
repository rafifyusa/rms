<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix = "rms" uri = "/WEB-INF/tags/link.tld"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">

  <title>RMS</title>
  <meta name="description" content="Index">
  <meta name="author" content="Mitrais">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
  <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
  <rms:link type="stylesheet" href="css/styles.css?v=1.0"/>

  <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
  <![endif]-->
</head>

<body>
    <div class="mdl-layout mdl-js-layout mdl-color--grey-100 box-center">
    	<main class="mdl-layout__content">
                <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                    <h2 class="mdl-card__title-text">User List</h2>
                </div>
                <div class="mdl-card__supporting-text">
                    <table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
                        <thead>
                        <tr>
                            <th class="mdl-data-table__cell--non-numeric">User Name</th>
                            <th>Password</th>
                            <th> Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items = "${users}" var="user">
                            <tr>
                                <td class="mdl-data-table__cell--non-numeric"><c:out value = "${user.userName}"/></td>
                                <td><c:out value = "${user.password}"/></td>
                                <td>
                                    <button type="button" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" id="editButton<c:out value = "${user.id}"/>" onclick="showEditModal(<c:out value = "${user.id}"/>)">Edit</button>
                                    <form action="UserServlet?action=delete&id=<c:out value = "${user.id}"/>" method="post">
                                        <button type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Delete</button>
                                    </form>
                                </td>
                            </tr>

                            <dialog class="mdl-dialog" id="editModal<c:out value = "${user.id}"/>">
                                <div class="mdl-dialog__content">
                                    <form action="UserServlet?action=edit&id=<c:out value = "${user.id}"/>" method="post">
                                        <div class="mdl-textfield mdl-js-textfield">
                                            <input class="mdl-textfield__input" type="text" name="username" id="username<c:out value = "${user.id}"/>" />
                                            <label class="mdl-textfield__label" for="username<c:out value = "${user.id}"/>"><c:out value = "${user.userName}"/></label>
                                        </div>
                                        <div class="mdl-textfield mdl-js-textfield">
                                            <input class="mdl-textfield__input" type="password" name="userpass" id="userpass<c:out value = "${user.id}"/>" />
                                            <label class="mdl-textfield__label" for="userpass<c:out value = "${user.id}"/>"><c:out value = "${user.password}"/></label>
                                        </div>
                                        <div class="mdl-card__actions mdl-card--border">
                                            <button type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Save</button>
                                            <button type="button" class="mdl-button close">Cancel</button>
                                        </div>
                                    </form>
                                </div>
                            </dialog>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>


            <button type="button" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" id="insertButton" onclick="showInsertModal()">Add new user</button>

            <c:if test="${editResponse}">
                <span style="color: red"> Edit Successful</span>
            </c:if>
            <c:if test="${!editResponse && editResponse != null}">
                <span style="color: red"> Edit Failed</span>
            </c:if>
            <c:if test="${insertResponse}">
                <span style="color: red"> Insert Successful</span>
            </c:if>
            <c:if test="${!insertResponse && insertResponse != null}">
                <span style="color: red"> Insert Failed</span>
            </c:if>
            <c:if test="${deleteResponse}">
                <span style="color: red"> Delete Successful</span>
            </c:if>
            <c:if test="${!deleteResponse && deleteResponse != null}">
                <span style="color: red"> Delete Failed</span>
            </c:if>

            <dialog class="mdl-dialog" id="insertModal">
                <div class="mdl-dialog__content">
                    <form action="UserServlet?action=insert" method="post">
                        <div class="mdl-textfield mdl-js-textfield">
                            <input class="mdl-textfield__input" type="text" name="username" id="username" />
                            <label class="mdl-textfield__label" for="username">Username</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield">
                            <input class="mdl-textfield__input" type="password" name="userpass" id="userpass" />
                            <label class="mdl-textfield__label" for="userpass">Password</label>
                        </div>
                        <div class="mdl-card__actions mdl-card--border">
                            <button type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Save</button>
                            <button type="button" class="mdl-button close">Cancel</button>
                        </div>
                    </form>
                </div>
            </dialog>
    	</main>
    </div>
  <script src="/rms-servlet-web/js/scripts.js?2"></script>
</body>
</html>
