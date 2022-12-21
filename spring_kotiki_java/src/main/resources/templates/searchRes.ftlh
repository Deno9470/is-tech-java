<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>CMS - Search</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<a href="/">Back</a>
<br>
<#if inputError??>
    <div style="color:red;font-style:italic;">
        ${inputError}
    </div>
</#if>
Result :
<table border="2px">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Date of birth</td>
        <td>Breed</td>
        <td>Color</td>
    </tr>
    <#list searchList as cat>
        <tr>
            <td>${cat.id}</td>
            <td>${cat.name}</td>
            <td>${cat.date}</td>
            <td>${cat.breed}</td>
            <td>${cat.color}</td>
        </tr>
    </#list>

</table>
</body>
</html>