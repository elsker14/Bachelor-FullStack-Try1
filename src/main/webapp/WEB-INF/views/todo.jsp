<%@ include file="common/header.jspf" %>

		<%@ include file="common/navigation.jspf" %>
		<div class = "container">
			<H1>Add a todo</H1>
			<form:form method="post" commandName ="todo">	<!-- folosim FORM din tag library ul introdus -->
				
				<form:hidden path = "id"/>					<!-- valori ascunse care nu le vreau pe ecran, dar vreau sa le returnez -->
				<!--<form:hidden path = "user"/>-->				<!-- hidden values nu sunt neaparat bune, fiindca userul poate fi interceptat usor si schimbat, asa ca o sa ai probleme de securitate -->
				
				<fieldset class="form-group">				<!-- todo este COMMAND OBJECT, adica cel pe care l-am declarat in functia addTodo din Controller ca parametru -->
					<form:label path="desc">Description</form:label> <!-- path e campul din Todo.java pe care il umplem cu ceva  -->
					<form:input path="desc" type="text" class="form-control" required="required" />
					<form:errors path="desc" cssClass = "text-warning"/>
				</fieldset>
				
				<fieldset class="form-group">			
					<form:label path="targetDate">Target Date</form:label> 
					<form:input path="targetDate" type="text" class="form-control" required="required" />
					<form:errors path="targetDate" cssClass = "text-warning"/>
				</fieldset>
				
				<input class="btn btn-success" type="submit" value="Submit"/>
			</form:form>
		</div>
		
<%@ include file="common/header.jspf" %>