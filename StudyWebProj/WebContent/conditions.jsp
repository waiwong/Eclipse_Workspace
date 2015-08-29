<html>
<head>
<title>EL Conditions</title>
<style>
body, td {
	font-family: verdana;
	font-size: 10pt;
}
</style>
</head>
<body>
	<h2>EL Conditions</h2>
	<table border="1">
		<tr>
			<td><b>Concept</b></td>
			<td><b>EL Condition</b></td>
			<td><b>Result</b></td>
		</tr>
		<tr>
			<td>Numeric less than</td>
			<td>${'${'}1&lt;2}</td>
			<td>${1 < 2}</td>
		</tr>
		<tr>
			<td>Numeric greater than</td>
			<td>${'${'}1&gt;2}</td>
			<td>${1 > 2}</td>
		</tr>
		<tr>
			<td>Numeric less than</td>
			<td>${'${'}1lt2}</td>
			<td>${1 lt 2}</td>
		</tr>
		<tr>
			<td>Numeric greater than</td>
			<td>${'${'}1gt2}</td>
			<td>${1 gt 2}</td>
		</tr>
		<tr>
			<td>Numeric Greater than or equal</td>
			<td>${'${'}1&gt;=1}</td>
			<td>${1 >= 1}</td>
		</tr>
		<tr>
			<td>Numeric Less than or equal</td>
			<td>${'${'}1&lt;=1}</td>
			<td>${1 <= 1}</td>
		</tr>
		<tr>
			<td>Numeric less than or equal</td>
			<td>${'${'}1le1}</td>
			<td>${1 le 1}</td>
		</tr>
		<tr>
			<td>Numeric greater than or equal</td>
			<td>${'${'}1ge1}</td>
			<td>${1 ge 1}</td>
		</tr>
		<tr>
			<td>Numeric equal to</td>
			<td>${'${'}1==1}</td>
			<td>${1 == 1}</td>
		</tr>
		<tr>
			<td>Numeric equal to</td>
			<td>${'${'}1eq1}</td>
			<td>${1 eq 1}</td>
		</tr>
		<tr>
			<td>Numeric not equal to</td>
			<td>${'${'}1!=2}</td>
			<td>${1 != 2}</td>
		</tr>
		<tr>
			<td>Numeric not equal to</td>
			<td>${'${'}1ne2}</td>
			<td></td>
			<%-- 			<td>${1 ne 2}</td> --%>
		</tr>
		<tr>
			<td>Alphabetic less than</td>
			<td>${'${'}'abe'&lt;'ade'}</td>
			<td>${'abe' < 'ade'}</td>
		</tr>
		<tr>
			<td>Alphabetic greater than</td>
			<td>${'${'}'abe'&gt;'ade'}</td>
			<td>${'abe' > 'ade'}</td>
		</tr>
		<tr>
			<td>Alphabetic equal to</td>
			<td>${'${'}'abe'eq'abe'}</td>
			<td>${'abe' eq 'abe'}</td>
		</tr>
		<tr>
			<td>Alphabetic not equal to</td>
			<td>${'${'}'abe'ne'ade'}</td>
			<%-- 			<td>${'abe' ne 'ade'}</td> --%>
			<td></td>
		</tr>
	</table>
</body>
</html>