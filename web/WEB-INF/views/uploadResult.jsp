<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false"%>

<script>
    var result = '${savedName}';
    console.log("&&&&&&&&&&&");
    console.log(result);
    parent.addFilePath(result);
</script>
