<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="list" value="${requestScope.results}" />
<c:if test = "${list.size() == 0}">
    <h4>No results</h4>
</c:if>

<c:forEach var="friend" items="${list}">
    <div class="search-result-box-content-item">
        <img src="data:image/png;base64,${friend.avatar}">
        <span>
            <a href="">${friend.lastName} ${friend.firstName}</a>
            <a href="">California, New York</a>
            <a href="">Live in Sysney, Australia</a>
            <a href="">2 mutual friends: David Phan and Lee Palm</a>
        </span>
        <button><i class="fa fa-user-plus" aria-hidden="true"></i> Add friend</button>
    </div>
</c:forEach>
