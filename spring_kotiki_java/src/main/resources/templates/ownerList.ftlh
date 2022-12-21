<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CMS - Owners</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<a href="/">Back</a>
<br>
<div class="newOwner">
    <a href="/owners/add" ><input type="button" value="Add new owner" ></a>
</div>


<h2>Owner list(count : ${taskSize})</h2>
<table border="1px">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Date of birth</td>
    </tr>
    <#list ownerList as ow>
        <tr>
            <td>${ow.id}</td>
            <td>${ow.name}</a></td>
            <td>${ow.date}</td>
            <td><a href="${'/owners/delete/'+ow.id}">Delete</a></td> <td> <a href="${'/owners/'+ow.id}">Manage</a></td>
        </tr>
    </#list>
</table>
</body>
</html>