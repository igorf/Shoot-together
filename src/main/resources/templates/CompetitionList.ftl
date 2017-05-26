<!DOCTYPE HTML>
<head>
    <title>Competition list</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <#list competitions as competition>
        ${competition.id}: ${competition.title}
    </#list>
</body>
</html>