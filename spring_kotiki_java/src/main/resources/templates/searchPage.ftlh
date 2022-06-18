<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>CMS - Search</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body style="display: inline-block">
<a href="/">Back</a>
<br>
Set parameters for search :
<form action=""  method="post">
    <p>Name:  <@spring.formInput "modelCat.name"/></p>
    <p class="radioEnums">Breed:  <@spring.formSingleSelect "modelCat.breed", breeds, " "/></p>
    <p class="radioEnums">Color:  <@spring.formSingleSelect "modelCat.color", colors, " "/></p>

    <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
</form>
</body>
</html>