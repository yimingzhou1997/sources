<%@page import="java.util.Base64"%>
<%@page import="java.util.Base64.Encoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="js/jquery.min.js"></script>
<body>
	<h2>Wellcome</h2>
<body>
	<%
		String path = "C:\\Users\\Administrator\\Pictures\\Saved Pictures\\20171210083321.png";
		File file = new File(path);
		byte[] array = new byte[(int) file.length()];
		Encoder encoder = Base64.getEncoder();
		String code = encoder.encodeToString(array);
		System.out.println(code);
	%>
	<form action="login">
		登录名：<input type="text" name="username" /> <input type="submit"
			value="登录" />
	</form>
	<img alt="" src="<%=code%>">
	<script type="text/javascript">
		/* $(function() {
			taxi(1024);
		})
		function taxi(iis) {
			if (iis > 0) {
				var av = iis / 2;
				console.log(av);
				taxi(av);
			} else {
				return;
			}
		} */
	</script>
</body>
</body>
</html>