<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>CMS - Owners</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<a href="/owners/">Back</a>
<h1>Form</h1>

<#if inputError??>
    <div style="color:red;font-style:italic;">
        ${inputError}
    </div>
</#if>
<form action=""  method="post">
<!--    <p>Id: <@spring.formInput "owner.id"/></p> -->
    <p>Name:  <@spring.formInput "owner.name"/></p>
    <p>Date of birth:  <@spring.formInput "owner.date"/></p>
    <p>Password: <@spring.formInput "owner.password"/> </p>
    <p class="radioEnums">Role:  <@spring.formSingleSelect "owner.role", roles, " "/></p>
    <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
</form>
</body>
</html>