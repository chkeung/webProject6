<!DOCTYPE html>
<html>
    <head>
        <title>Online Bidding System</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Create a item</h2>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="ticketForm">
            <form:label path="itemName">Name</form:label><br/>
            <form:input type="text" path="itemName" /><br/><br/>

            <form:label path="description">Description</form:label><br/>
            <form:textarea path="description" rows="5" cols="30" /><br/><br/>

            <form:label path="expectedPrice">Expected Price</form:label><br/>
            <form:input type="text" path="expectedPrice" /><br/><br/>

            <form:label path="status">Status</form:label><br/>
            <form:select path="status">
                <form:option value="available">Available</form:option>
                <form:option value="ended">Ended</form:option>
            </form:select>
            <br/>
            <br/>


            <b>Attachments</b><br/>
            <input type="file" name="attachments" multiple="multiple"/><br/><br/>
            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>
