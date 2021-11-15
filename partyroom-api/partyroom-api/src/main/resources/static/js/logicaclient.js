var urlServicio = 
'/api';

var idClient = 0;

//Inicio del selector pagina
$(document).ready(function(){
	$.support.cors = true;
	
    $("#editbutton").hide();

	cargarClientes();
});

	cargarClientes = function (){
		$.ajax({
	 		url: urlServicio + "/Client/all",
			type: 'GET',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
			console.log(data);
				
				if ($("#tablaClientes tbody").length == 0) {
						$("#tablaClientes").append("<tbody></tbody>");
				} else {
					$("#tablaClientes tbody").empty();
				}
				
				$.each(data, function (index, obj) {
					$("#tablaClientes tbody").append(
						"<tr>" +

							"<td>" + obj.name + "</td>" +
							"<td>" + obj.age + "</td>" +
							"<td>" + obj.email + "</td>" +
							"<td>" + obj.password + "</td>" +
							
							"<td>" +
							  "<button type='button' " +
							     "onclick='cargarCliente(" + obj.idClient + ");'>"  +
								 "Editar" +
							  "</button>" +
							"</td>" +
							"<td>" +
							  "<button type='button' " +
							     "onclick='borrarCliente(" + obj.idClient + ");'>" +
								 "Eliminar" +
							  "</button>" +
							"</td>" +
						"</tr>"
					);
				
				});
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		});
	}
	
	limpiarFormulario = function() {
		$('#nameTxt').val('');
		$('#ageTxt').val('');
		$('#emailTxt').val('');
		$('#passwordTxt').val('');
	}
	
	cargarCliente = function(id){
		console.log(id);
		
		$("#addbutton").hide();
		$("#editbutton").show();
		$("#emailTxt").prop('disabled', true);
		
		idCliente = id;
		
		$.ajax({
	 		url: urlServicio + "/Client/" + idCliente,
			type: 'GET',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
				var item= data;
				console.log(item);
        		$("#nameTxt").val(item.name);
        		$("#ageTxt").val(item.age);
				$("#emailTxt").val(item.email);
        		$("#passwordTxt").val(item.password);
        		
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		});
	}
	
	editarCliente = function() {
		Cliente = new Object();
		
		Cliente.idClient = idCliente
		Cliente.name = $('#nameTxt').val();
		Cliente.age = $('#ageTxt').val();
		Cliente.password= $('#passwordTxt').val();
		
		
		console.log(JSON.stringify(Cliente));
		
		$.ajax({
	 		url: urlServicio+"/Client/update",
			type: 'PUT',
			
			
	     	contentType: "application/json; charset=utf-8",
			data: JSON.stringify(Cliente),
	    	dataType: "json",
			success: function(data) {
				console.log("registro actualizado!");
				
				cargarClientes();
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		});
		
		cargarClientes();
		limpiarFormulario();
		idCliente = 0;
		$("#addbutton").show();
		$("#editbutton").hide();
		$("#emailTxt").prop('disabled', false);
	}
	
	borrarCliente = function(id) {
		
		$.ajax({
	 		url: urlServicio+"/Client/"+id,
			type: 'DELETE',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
				console.log("registro eliminado!");
				cargarClientes();
			
			},
			error: function(result, sts, err) {
				console.log(result);
				cargarClientes();
			}
		});
			
	}
	
	adicionarCliente= function() {
		Cliente = new Object();
		
		Cliente.name = $('#nameTxt').val();
		Cliente.age = $('#ageTxt').val();
		Cliente.email = $('#emailTxt').val();
		Cliente.password = $('#passwordTxt').val();
		console.log(Cliente);
		
		$.ajax({
	 		url: urlServicio+"/Client/save",
			type: 'POST',
	     	contentType: "application/json; charset=utf-8",
			data: JSON.stringify(Cliente),
	    	dataType: "json", 
			statusCode: {
				201: function(response, status, jqXHR) {
					console.log("registro adicionado!");
					limpiarFormulario();
					cargarClientes();
				}
			}
		})
		.done(function(data){
			console.log("registro adicionado!");
				cargarClientes();
		});
		
	}
	