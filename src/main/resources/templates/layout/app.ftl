<!--#assign security=JspTaglibs["classpath:security.tld"] /-->
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] >

<#macro layout title>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="https://bootswatch.com/flatly/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="/css/app.css" />
        <script type="text/javascript" src="/bower_components/jquery/dist/jquery.min.js"></script>
    </head>

    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">
                        <span class="glyphicon glyphicon-globe"></span>
                        Shoot together
                    </a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
                    <ul class="nav navbar-nav navbar-right">
                        <@security.authorize access="isAuthenticated()">
                            <li><a href="/logout">logout</a></li>
                        </@security.authorize>
                        <@security.authorize access="! isAuthenticated()">
                            <li><a href="/login">login</a></li>
                        </@security.authorize>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="page-header">
            </div>
            <#nested>
        </div>
        <script type="text/javascript" src="/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>
</#macro>