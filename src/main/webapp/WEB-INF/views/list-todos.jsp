<%@ include file="common/header.jspf" %>

		<%@ include file="common/navigation.jspf" %>
		<div class="container"> <!-- seaman cu un Annotation, adica are specific meaning => container ne zice ca ne adauga niste formatare, borders etc -->
			<table class = "table table-striped">
				<caption>Your Todos are</caption>
				<thead>	
					<tr> 
						<th>Description</th>
						<th>Target Date</th>
						<th>Is Completed?</th>
						<th></th>
					</tr>
				</thead>
				
				<tbody>
					<!-- for(Todo todo: todos) -->
					<c:forEach items ="${todos}" var ="todo"> 
						<tr>  
							<td>${todo.desc}</td>
							<td>
								<fmt:formatDate pattern ="dd/MM/yyyy" value = "${todo.targetDate}"/>
							</td>
							<td>${todo.done}</td>			<!-- Conventie cum ca nu poti scrie isDone asa cum e campul din JavaClass -->
							<td>
								<a class ="btn btn-success" href="/update-todo?id=${todo.id}">Update</a>
								<a class ="btn btn-danger" href="/delete-todo?id=${todo.id}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div>
				<a class = "btn btn-success" href="/add-todo">Add todo item</a>
			</div>
		</div>

<%@ include file="common/header.jspf" %>