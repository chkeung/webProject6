<!DOCTYPE html>
<html>
    <head>
        <title>Add Comment</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h1>Add Comment to item #${ticket.id}: <c:out value="${ticket.itemName}" /></h1>

        <form:form method="post" > 
            <form:input type="hidden" path="name"  value="${ticket.id}"/> <br />
            <form:textarea path="message"></form:textarea> <br />
            <% 
                String  s1  = (String) session.getAttribute("var2");        
            %>
            <form:input type="hidden" path="customer"  value="<%= s1 %>"/> <br />
            <input type="submit" name="addComment" value="Add" />
        </form:form>
    </body>
</html>
