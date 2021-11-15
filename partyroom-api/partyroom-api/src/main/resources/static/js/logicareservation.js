var urlServicio = 
'/api';

var idReservacion = 0;

//Inicio del selector pagina
$(document).ready(function(){
	$.support.cors = true;
	
    $("#editbutton").hide();

	cargarReservaciones();
	cargarSalones();
	cargarClientes();
});

	cargarReservaciones = function (){
		$.ajax({
	 		url: urlServicio + "/Reservation/all",
			type: 'GET',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
			console.log(data);
				
				if ($("#tablaReservaciones tbody").length == 0) {
						$("#tablaReservaciones").append("<tbody></tbody>");
				} else {
					$("#tablaReservaciones tbody").empty();
				}
				
				$.each(data, function (index, obj) {
					$("#tablaReservaciones tbody").append(
						"<tr>" +

							"<td>" + obj.startDate + "</td>" +
							"<td>" + obj.devolutionDate + "</td>" +
							"<td>" + obj.status+ "</td>" +
							"<td>" + obj.score+ "</td>" +
							"<td>" + obj.client.name + "</td>" +
							"<td>" + obj.partyroom.name + "</td>" +
						
							
							
							"<td>" +
							  "<button type='button' " +
							     "onclick='cargarReservacion(" + obj.idReservation + ");'>"  +
								 "Editar" +
							  "</button>" +
							"</td>" +
							"<td>" +
							  "<button type='button' " +
							     "onclick='borrarReservacion(" + obj.idReservation + ");'>" +
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
		$('#startDateTxt').val('');
		$('#devolutionDateTxt').val('');
		$('#statusSlc').val('');
		$('#partyroomSlc').val('');
		$('#clientSlc').val('');
	}
	
	cargarReservacion = function(id){
		console.log(id);
		
		$("#addbutton").hide();
		$("#editbutton").show();
		$('#startDateTxt').val('');
		$('#devolutionDateTxt').val('');
		$("#statusDiv").css({ display: "block" });
		$("#PartyroomSlc").prop('disabled', true);
		$("#ClientSlc").prop('disabled', true);
		
		idReservacion = id;
		
		$.ajax({
	 		url: urlServicio + "/Reservation/" + idReservacion,
			type: 'GET',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
				var item= data;
				console.log(item);
				$("#startDateTxt").val(item.startDate);
				$("#devolutionDateTxt").val(item.devolutionDate);
				$("#statusSlc").val(item.status);
        		$("#PartyroomSlc").val(item.partyroom.idPartyroom);
				$("#ClientSlc").val(item.client.idClient);
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		});
	}
	
	editarReservacion = function() {
		Reservacion = new Object();
		
		Reservacion.idReservation = idReservacion;
		Reservacion.startDate = $('#startDateTxt').val();
		Reservacion.devolutionDate = $('#devolutionDateTxt').val();
		Reservacion.status = $('#statusSlc').val();
		
		console.log(JSON.stringify(Reservacion));
		
		$.ajax({
	 		url: urlServicio+"/Reservation/update",
			type: 'PUT',
			
			
	     	contentType: "application/json; charset=utf-8",
			data: JSON.stringify(Reservacion),
	    	dataType: "json",
			success: function(data) {
				console.log("reservación actualizada!");
				
				cargarReservaciones();
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		});
		
		cargarReservaciones();
		limpiarFormulario();
		idReservacion = 0;
		$("#addbutton").show();
		$("#editbutton").hide();
		$("#statusDiv").css({ display: "none" });
		$("#PartyroomSlc").prop('disabled', false);
		$("#ClientSlc").prop('disabled', false);
	}
	
	borrarReservacion = function(id) {
		
		$.ajax({
	 		url: urlServicio+"/Reservation/"+id,
			type: 'DELETE',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
				console.log("reservación eliminada!");
				cargarReservaciones();
			
			},
			error: function(result, sts, err) {
				console.log(result);
				cargarReservaciones();
			}
		});
			
	}
	
		crearReservacion= function() {
		Reservacion = new Object();
		
		Reservacion.startDate = $('#startDateTxt').val();
		Reservacion.devolutionDate = $('#devolutionDateTxt').val();
		Reservacion.partyroom = new Object();
		Reservacion.partyroom.idPartyroom = $('#PartyroomSlc').val();
		Reservacion.client = new Object();
		Reservacion.client.idClient = $('#ClientSlc').val();

		
		console.log(Reservacion);
		
		$.ajax({
	 		url: urlServicio+"/Reservation/save",
			type: 'POST',
	     	contentType: "application/json; charset=utf-8",
			data: JSON.stringify(Reservacion),
	    	dataType: "json", 
			statusCode: {
				201: function(response, status, jqXHR) {
					console.log("Reservación creada!");
					limpiarFormulario();
					cargarReservaciones();
				}
			}
		})
		.done(function(data){
			console.log("Reservación creada!");
				cargarReservaciones();
		});
		
	}
	
	cargarSalones = function() {
		$.ajax({
	 		url: urlServicio + "/Car/all",
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