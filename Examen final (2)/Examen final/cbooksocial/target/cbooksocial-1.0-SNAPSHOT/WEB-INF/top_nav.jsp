<%@page import="net.learnbyproject.helper.Keys"%>
<%@page import="net.learnbyproject.model.User"%>
<nav class="container-fluid" id="main-nav-user-home">
    <div class="row">
        <form action="search" method="get" autocomplete="off">
            <div class="col-md-6" id="user-tools-left">
                <a href="/news-feed" id="main-logo">C</a>
                <div id="search-box">
                    <input type="text" name="search-friend" id="search-friend">
                    <button type="submit"><i class="fa fa-search"></i></button>
                </div>
            </div>
        </form>
        <div class="col-md-6" id="user-tools-right">
            <ul>
                <li>
                    <a href="profile">
                        <img class="avatar24" src="data:image/png;base64,${sessionScope[Keys.USER].avatar}">
                        ${sessionScope[Keys.USER].firstName}
                    </a>
                </li>
                <li>
                    <a href="/"><i class="fa fa-users" aria-hidden="true"></i></a>
                    <span class="mess-notif">9</span>
                </li>
                <li>
                    <a id="show-chat-box-button" href="/"><i class="fa fa-weixin" aria-hidden="true"></i></a>
                    <span class="mess-notif">25</span>
                </li>
                <li>
                    <a href="/"><i class="fa fa-globe" aria-hidden="true"></i></a>
                    <span class="mess-notif">5</span>
                </li>

                ${sessionScope[Keys.USER] != null ? "<li><a href='logout'><i class='fa fa-power-off'></i></a></li>" : ""}
            </ul>
        </div>
    </div>
</nav>