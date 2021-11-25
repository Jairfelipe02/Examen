<%@page import="net.learnbyproject.helper.Keys"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="list" value="${sessionScope[Keys.SUGGESTED_FRIEND_LIST]}" />
<c:forEach var="friend" items="${list}">
    <div class="friend-item">
        <img class="avatar-small" 
             src="data:image/png;base64,${friend.avatar}">
        <span>
            <a href="#">${friend.lastName} ${friend.firstName}</a> 
            <span style="display:block;">123 mutual friends </span>
            <button id="${friend.id}"><i class="fa fa-user-plus" aria-hidden="true"></i> 
                Add friend
            </button>
        </span>
        <a href="#" class="ignore-friend"><i class="fa fa-times" aria-hidden="true"></i></a>
    </div>
</c:forEach>
<script>
    $(function () {
        $('.friend-item button').click(function () {
            var friendId = $(this).attr('id');
            $.ajax({
                url: 'get-suggested-friend-list',
                type: 'POST',
                data: {
                    action: 'add-friend',
                    friendId: friendId
                },
                success: function (data) {
                    $('#suggested-friend-list').html(data);
                    $('#friend-list').load("friend-list");
                },
                error: function () {
                    alert('Cannot add friend');
                }
            });
        });
    });
</script>