var urlServicio = 
'/api';

var idMensaje = 0;

//Inicio del selector pagina
$(document).ready(function(){
	$.support.cors = true;
	
    $("#editbutton").hide();

	cargarMensajes();
	cargarSalones();
	cargarClientes();
});

	cargarMensajes = function (){
		$.ajax({
	 		url: urlServicio + "/Message/all",
			type: 'GET',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
			console.log(data);
				
				if ($("#tablaMensajes tbody").length == 0) {
						$("#tablaMensajes").append("<tbody></tbody>");
				} else {
					$("#tablaMensajes tbody").empty();
				}
				
				$.each(data, function (index, obj) {
					$("#tablaMensajes tbody").append(
						"<tr>" +

							
							"<td>" + obj.partyroom.name + "</td>" +
							"<td>" + obj.client.name + "</td>" +
							"<td>" + obj.messageText + "</td>" +
							
							
							"<td>" +
							  "<button type='button' " +
							     "onclick='cargarMensaje(" + obj.idMessage + ");'>"  +
								 "Editar" +
							  "</button>" +
							"</td>" +
							"<td>" +
							  "<button type='button' " +
							     "onclick='borrarMensaje(" + obj.idMessage + ");'>" +
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
		$('#messageTxt').val('');
		$('#PartyroomSlc').val('');
		$('#ClientSlc').val('');
	}
	
	cargarMensaje = function(id){
		console.log(id);
		
		$("#addbutton").hide();
		$("#editbutton").show();
		$("#PartyroomSlc").prop('disabled', true);
		$("#ClientSlc").prop('disabled', true);
		
		idMensaje = id;
		
		$.ajax({
	 		url: urlServicio + "/Message/" + idMensaje,
			type: 'GET',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
				var item= data;
				console.log(item);
        		$("#messageTxt").val(item.messageText);
        		$("#PartyroomSlc").val(item.partyroom.idPartyroom);
				$("#ClientSlc").val(item.client.idClient);
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		});
	}
	
	editarMensaje = function() {
		Mensaje = new Object();
		
		Mensaje.idMessage = idMensaje;
		Mensaje.messageText = $('#messageTxt').val();
		
		console.log(JSON.stringify(Mensaje));
		
		$.ajax({
	 		url: urlServicio+"/Message/update",
			type: 'PUT',
			
			
	     	contentType: "application/json; charset=utf-8",
			data: JSON.stringify(Mensaje),
	    	dataType: "json",
			success: function(data) {
				console.log("mensaje actualizado!");
				
				cargarMensajes();
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		});
		
		cargarMensajes();
		limpiarFormulario();
		idMensaje = 0;
		$("#addbutton").show();
		$("#editbutton").hide();
		$("#PartyroomSlc").prop('disabled', false);
		$("#ClientSlc").prop('disabled', false);
	}
	
	borrarMensaje = function(id) {
		
		$.ajax({
	 		url: urlServicio+"/Message/"+id,
			type: 'DELETE',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
				console.log("mensaje eliminado!");
				cargarMensajes();
			
			},
			error: function(result, sts, err) {
				console.log(result);
				cargarMensajes();
			}
		});
			
	}
	
	adicionarMensaje= function() {
		Mensaje = new Object();
		
		Mensaje.messageText = $('#messageTxt').val();
		Mensaje.partyroom = new Object();
		Mensaje.partyroom.idPartyroom = $('#PartyroomSlc').val();
		Mensaje.client = new Object();
		Mensaje.client.idClient = $('#ClientSlc').val();

		
		console.log(Mensaje);
		
		$.ajax({
	 		url: urlServicio+"/Message/save",
			type: 'POST',
	     	contentType: "application/json; charset=utf-8",
			data: JSON.stringify(Mensaje),
	    	dataType: "json", 
			statusCode: {
				201: function(response, status, jqXHR) {
					console.log("mensaje adicionado!");
					limpiarFormulario();
					cargarMensajes();
				}
			}
		})
		.done(function(data){
			console.log("mensaje adicionado!");
				cargarMensajes();
		});
		
	}
	
	cargarSalones = function() {
		$.ajax({
	 		url: urlServicio + "/Partyroom/all",
			type: 'GET',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
				$.each(data, function (index, obj) {
					$("#PartyroomSlc").append('<option value="'+obj.idPartyroom+'">'+obj.name+'</option>');
					 
					});
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		
	});
	}
	cargarClientes = function() {
		$.ajax({
	 		url: urlServicio + "/Client/all",
			type: 'GET',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
				$.each(data, function (index, obj) {
					$("#ClientSlc").append('<option value="'+obj.idClient+'">'+obj.name+'</option>');
					 
					});
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		
	});
	}