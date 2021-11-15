var urlServicio = 
'/api';

var idCategory = 0;

//Inicio del selector pagina
$(document).ready(function(){
	$.support.cors = true;
	
    $("#editbutton").hide();

	cargarCategories();
});

	cargarCategories = function (){
		$.ajax({
	 		url: urlServicio + "/Category/all",
			type: 'GET',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
			console.log(data);
				
				if ($("#tablaCategories tbody").length == 0) {
						$("#tablaCategories).append("<tbody></tbody>");
				} else {
					$("#tablaCategories tbody").empty();
				}
				
				$.each(data, function (index, obj) {
					$("#tablaCategories tbody").append(
						"<tr>" +
							"<td>" + obj.name + "</td>" +
							"<td>" + obj.description + "</td>" +
							"<td>" +
							  "<button type='button' " +
							     "onclick='cargarCategory(" + obj.idCategory + ");'>"  +
								 "Editar" +
							  "</button>" +
							"</td>" +
							"<td>" +
							  "<button type='button' " +
							     "onclick='borrarCategory(" + obj.idCategory + ");'>" +
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
		$('#descriptionTxt').val('');
	}
	
	cargarCategory = function(id){
		console.log(id);
		
		$("#addbutton").hide();
		$("#editbutton").show();
		
		idCategory = id;
		
		$.ajax({
	 		url: urlServicio + "/Category/" + idCategory,
			type: 'GET',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
				var item= data;
				console.log(item);
        		$("#nameTxt").val(item.name);
        		$("#descriptionTxt").val(item.description);
			
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		});
	}
	
	editarCategory = function() {
		Category = new Object();
		
		Category.idCategory = idCategory;
		Category.name = $('#nameTxt').val();
		Category.description = $('#descriptionTxt').val();
		
		console.log(JSON.stringify(Category));
		
		$.ajax({
	 		url: urlServicio+"/Category/update",
			type: 'PUT',
			
			
	     	contentType: "application/json; charset=utf-8",
			data: JSON.stringify(Category),
	    	dataType: "json",
			success: function(data) {
				console.log("registro actualizado!");
				
				cargarCategories();
			},
			error: function(result, sts, err) {
				console.log(result);
			}
		});
		
		cargarCategories();
		limpiarFormulario();
		idCategory = 0;
		$("#addbutton").show();
		$("#editbutton").hide();
	}
	
	borrarCategory = function(id) {
		
		$.ajax({
	 		url: urlServicio+"/Category/"+id,
			type: 'DELETE',
	     	contentType: "application/json; charset=utf-8",
	    	dataType: "json", 
			success: function(data) {
				console.log("registro eliminado!");
				cargarCategories();
			
			},
			error: function(result, sts, err) {
				console.log(result);
				cargarCategories();
			}
		});
			
	}
	
	adicionarCategory= function() {
		Category = new Object();
		
		Category.name = $('#nameTxt').val();
		Category.description = $('#descriptionTxt').val();
		
		console.log(Category);
		
		$.ajax({
	 		url: urlServicio+"/Category/save",
			type: 'POST',
	     	contentType: "application/json; charset=utf-8",
			data: JSON.stringify(Category),
	    	dataType: "json", 
			statusCode: {
				201: function(response, status, jqXHR) {
					console.log("registro adicionado!");
					limpiarFormulario();
					cargarCategories();
				}
			}
		})
		.done(function(data){
			console.log("registro adicionado!");
				cargarCategories();
		});
		
	}
	