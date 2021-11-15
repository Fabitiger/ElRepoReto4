var urlServicio = 
'/api';

var idSalon = 0;

//Inicio del selector pagina
$(document).ready(function(){
	$.support.cors = true;
	
    $("#editbutton").hide();

	cargarSalones();
	cargarCategorias();
});

	cargarSalones = function (){
		$.ajax({
	 		url: urlServicio + "/Partyroom/all",
			type: 'GET',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
			console.log(data);
				
				if ($("#tablaSalones tbody").length == 0) {
						$("#tablaSalones").append("<tbody></tbody>");
				} else {
					$("#tablaSalones tbody").empty();
				}
				
				$.each(data, function (index, obj) {
					$("#tablaSalones tbody").append(
						"<tr>" +
//							"<td>" + obj.idpartyroom + "</td>" +
							"<td>" + obj.owner + "</td>" +
							"<td>" + obj.name + "</td>" +
							"<td>" + obj.category.name + "</td>" +
							"<td>" + obj.capacity + "</td>" +
							"<td>" + obj.description + "</td>" +
							
							"<td>" +
							  "<button type='button' " +
							     "onclick='cargarSalon(" + obj.idPartyroom + ");'>"  +
								 "Editar" +
							  "</button>" +
							"</td>" +
							"<td>" +
							  "<button type='button' " +
							     "onclick='borrarSalon(" + obj.idPartyroom + ");'>" +
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
		$('#ownerTxt').val('');
		$('#nameTxt').val('');
		$('#categorySlc').val('');
		$('#capacityTxt').val('');
		$('#descriptionTxt').val('');
	}
	
	cargarSalon = function(id){
		console.log(id);
		
		$("#addbutton").hide();
		$("#editbutton").show();
		$("#categorySlc").prop('disabled', true);
		
		idSalon = id;
		
		$.ajax({
	 		url: urlServicio + "/Salon/" + idSalon,
			type: 'GET',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
				var item= data;
				console.log(item);
        		$("#ownerTxt").val(item.brand);
        		$("#nameTxt").val(item.description);
				$("#categorySlc").val(item.category.idCategory);
        		$("#capacityTxt").val(item.capacity);
        		$("#descriptionTxt").val(item.description);
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		});
	}
	
	editarSalon = function() {
		Salon = new Object();
		
		Salon.idPartyroom = idSalon;
		Salon.owner = $('#ownerTxt').val();
		Salon.name = $('#nameTxt').val();
		Salon.capacity = $('#capacityTxt').val();
		Salon.description = $('#descriptionTxt').val();
		
		console.log(JSON.stringify(Salon));
		
		$.ajax({
	 		url: urlServicio+"/Partyroom/update",
			type: 'PUT',
			
			
	     	contentType: "application/json; charset=utf-8",
			data: JSON.stringify(Salon),
	    	dataType: "json",
			success: function(data) {
				console.log("registro actualizado!");
				
				cargarSalones();
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		});
		
		cargarSalones();
		limpiarFormulario();
		idSalon = 0;
		$("#addbutton").show();
		$("#editbutton").hide();
		$("#gamaSlc").prop('disabled', false);
	}
	
	borrarSalon = function(id) {
		
		$.ajax({
	 		url: urlServicio+"/Partyroom/"+id,
			type: 'DELETE',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
				console.log("registro eliminado!");
				cargarSalones();
			
			},
			error: function(result, sts, err) {
				console.log(result);
				cargarSalones();
			}
		});
			
	}
	
	adicionarSalon= function() {
		Salon = new Object();
		
		Salon.owner = $('#ownerTxt').val();
		Salon.name = $('#nameTxt').val();
		Salon.category = new Object();
		Salon.category.idCategory = $('#categorySlc').val();
		Salon.capacity = $('#capacityTxt').val();
		Salon.description = $('#descriptionTxt').val();
		
		console.log(Salon);
		
		$.ajax({
	 		url: urlServicio+"/Partyroom/save",
			type: 'POST',
	     	contentType: "application/json; charset=utf-8",
			data: JSON.stringify(Salon),
	    	dataType: "json", 
			statusCode: {
				201: function(response, status, jqXHR) {
					console.log("registro adicionado!");
					limpiarFormulario();
					cargarSalones();
				}
			}
		})
		.done(function(data){
			console.log("registro adicionado!");
				cargarSalones();
		});
		
	}
	
	cargarCategorias = function() {
		$.ajax({
	 		url: urlServicio + "//all",
			type: 'GET',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
				$.each(data, function (index, obj) {
					$("#categorySlc").append('<option value="'+obj.idCategory+'">'+obj.name+'</option>');
					 
					});
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		
	});
	}