<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CMS - Cats</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<a href="${'/owners/'+cat.owner_id}">Back</a>
  <h2>${cat.name}</h2>
<br>
<div class="photo"><img src="https://thiscatdoesnotexist.com/" height="10%" width="10%" alt="cat"> </div>
<p>Not your cat? <br>
   <input type="button" value="Update" onClick="document.location.reload(true)"></p>

<table border="1px">
    <tr>
        <td>Id</td>
        <td>${cat.id}</td>
    </tr>
    <tr>
            <td>Name</td>
            <td>${cat.name}</td>
    </tr>
    <tr>
        <td>Date</td>
        <td>${cat.date}</td>
    </tr>
    <tr>
        <td>Owner</td>
        <td>${owner_name}(${cat.owner_id})</td>
    </tr>
    <tr>
        <td>Breed</td>
        <td>${cat.breed}</td>
    </tr>
    <tr>
        <td>Color</td>
        <td>${cat.color}</td>
    </tr>
</table>
<br>
<div class="newOwner">
    <a href="${'/cats/'+cat.id+'/friend'}" ><input type="button" value="Add new friend" ></a>
    <a href="${'/cats/delete/${cat.id}'}" ><input type="button" class="delete" value="Delete cat's profile"></a>
</div>
<h2>Friend list of ${cat.name}</h2>
<table border="1px">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Date of birth</td>
        <td>Breed</td>
        <td>Color</td>
    </tr>
    <#list friendList as bro>
        <tr>
            <td>${bro.id}</td>
            <td>${bro.name}</td>
            <td>${bro.date}</td>
            <td>${bro.breed}</td>
            <td>${bro.color}</td>
            <td> <a href="${'/cats/'+cat.id+'/delete/'+bro.id}">Delete</a></td>
        </tr>
    </#list>
</table>
</body>
</html>