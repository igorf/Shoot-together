<#macro basic_layout title>
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
        <#nested>
        <script type="text/javascript" src="/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>
</#macro>