<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>CMS - Cats</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<a href="${'/owners/'+owner_id}">Back</a>
<h1>Cat add form</h1>
<#if inputError??>
    <div style="color:red;font-style:italic;">
        ${inputError}
    </div>
</#if>
<form action=""  method="post">
    <!--    <p>Id: <@spring.formInput "cat.id"/></p>  -->
    <p>Name:  <@spring.formInput "cat.name"/></p>
    <p>Date of birth:  <@spring.formInput "cat.date"/></p>
    <p>Owner:  <input type="text" id="owner_id" name="owner_id" value='${owner_id}' readonly/></p>
    <p class="radioEnums">Breed:  <@spring.formSingleSelect "cat.breed", breeds, " "/></p>
    <p class="radioEnums">Color:  <@spring.formSingleSelect "cat.color", colors, " "/></p>

    <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
</form>
</body>
</html>