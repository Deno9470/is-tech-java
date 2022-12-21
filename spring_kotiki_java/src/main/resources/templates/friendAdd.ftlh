<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>CMS - Friends</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<a href="${'/cats/'+cat.id}">Back</a>
<h1>Friend add form</h1>
<#if inputError??>
    <div style="color:red;font-style:italic;">
        ${inputError}
    </div>
</#if>
<form action=""  method="post">
    <p><input type="text" id="first" name="first" value='${cat.id}' readonly hidden/></p>
    <p>Mark the friendship between <b> ${cat.name} </b> and <@spring.formSingleSelect "friend.second", notFriends, " "/></p>

    <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
</form>
</body>
</html>