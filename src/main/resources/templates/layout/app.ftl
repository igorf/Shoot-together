<!--#assign security=JspTaglibs["classpath:security.tld"] /-->
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] >

<#macro layout title>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="/css/app.css" />
        <script type="text/javascript" src="/js/jquery.min.js"></script>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="/">Shoot together</a>

                <ul class="nav navbar-nav navbar-right">
                        <@security.authorize access="hasRole('ROLE_ADMIN')">
                            <li class="nav-item"><a class="nav-link" href="/admin">admin panel</a></li>
                        </@security.authorize>
                        <@security.authorize access="isAuthenticated()">
                            <li class="nav-item"><a class="nav-link" href="/profile">my profile</a></li>
                            <li class="nav-item"><a class="nav-link" href="/logout">logout</a></li>
                        </@security.authorize>
                        <@security.authorize access="! isAuthenticated()">
                            <li class="nav-item"><a class="nav-link" href="/login">login</a></li>
                        </@security.authorize>
                </ul>
            </div>
        </nav>

        <div class="container">
            <div class="page-header">
            </div>
            <#nested>
        </div>
    </body>
</html>
</#macro>