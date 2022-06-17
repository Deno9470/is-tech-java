<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CMS - Owners</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<a href="/owners/">Back</a>
<h2>${owner.name}</h2>
<div class="photo"><img src="https://thispersondoesnotexist.com/image" height="10%" width="10%" alt="person"> </div>
<p>This is not you? <br>
<div class="but"><input type="button" value="Update" onClick="document.location.reload(true)"></div> </p>
<table border="1px">
    <tr>
        <td>Id</td>
        <td>${owner.id}</td>
    </tr>
    <tr>
        <td>Name</td>
        <td>${owner.name}</td>
    </tr>
    <tr>
        <td>Date</td>
        <td>${owner.date}</td>
    </tr>
</table>
<br>
<div class="newOwner">
    <a href="${'/cats/'+owner.id+'/add'}" ><input type="button" value="Add new cat"></a>
    <a href="${'/owners/delete/${owner.id}'}" ><input type="button" class="delete" value="Delete owner's profile"></a>
</div>
<h2>Cats list of ${owner.name}</h2>
<table border="1px">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Date of birth</td>
        <td>Breed</td>
        <td>Color</td>
    </tr>
    <#list catList as cat>
        <tr>
            <td>${cat.id}</td>
            <td>${cat.name}</td>
            <td>${cat.date}</td>
            <td>${cat.breed}</td>
            <td>${cat.color}</td>
            <td> <a href="${'/cats/delete/'+cat.id}">Delete</a></td>
            <td> <a href="${'/cats/'+cat.id}">Manage</a></td>
        </tr>
    </#list>
</table>
</body>
</html>