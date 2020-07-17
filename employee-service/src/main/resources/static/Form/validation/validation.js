/****************************VERIFICATION FOURNISSEUR*************************/
/*	$.validator.addMethod("combo", function(value, element) {
		   if (combo == 1) {
		        return false;
		        
		     }
	    return true;
	},"You should select a country");
*/
 function validateForm(formID, Obj){
			$.validator.addMethod("combo", function(value, element) {

	        var books = $('#combo');
	        if (books.val() === '') {
	            alert("Please select an item from the list and then proceed!");
	            $('#combo').focus();

	            return false;
	        }
	        else return true;
	    });}

 
 
$( document ).ready( function () {
			
			$( "#fournForm" ).validate( {
				 errorClass: "my-error-class",
				   validClass: "my-valid-class",
			
				rules: {
					fournID:{
						required:true,
						minlength: 3
					}, 
					fournNom:{
						combo:true,
						required:true,
						minlength: 3,
					
					},
					fournPrenom: {
						required: true,
						minlength: 3
					},
					fournNif: {
						required: true,
						minlength: 5
					},
					fournAdresse: {
						required: true,
						minlength: 5,
						
					},
					fournEmail: {
						required: true,
						email: true
					},
					fournTel: {
						required: true,
						minlength:8
					},
					fournNomComplet:{
						required:true,
						minlength:3
						
					},
				
					fournTel: {
						required:true,
						minlength:8
					},
					prodNomComplet:{
						required:true,
						minlength:3
					
					},
				
					prodTel: {
						required:true,
						minlength:8
					},
				},
				messages: {
					fournID: {
						required:"Entrer l'ID",
					
						minlength:"Minimun 3 Charactères"
					},
					fournNom:{
						required:"Entrer le nom",
						minlength:"Minimun 3 Charactères",
					
					},
					fournPrenom: {
						required:"Entrer le prenom",
						minlength:"Minimun 3 Charactères"
					},
					fournNif: {
						required:"Entrer le nif",
						minlength:"Minimun 10 Charactères"
					},
					fournEmail: {
						required:"Entrer l'email",
						email:"Email invalide"
					},
					fournAdresse: {
						required:"Entrer l'adresse",
						minlength:"Minimun 5 Charactères"
					},
					fournTel: {
						required:"Entrer le Tel",
						minlength:"Minimun 8 Charactères"
					},
					prodNomComplet:{
						required:"Entrer le nom complet",
						minlength:"Minimun 3 Charactères"
					
					},
				
					prodTel: {
						required:"Entrer le Tel",
						minlength:"Minimun 8 Charactères"
					},
				},
				errorElement: "em",
				errorPlacement: function ( error, element ) {
					// Add the `help-block` class to the error element
					error.addClass( "help-block" );

					// Add `has-feedback` class to the parent div.form-group
					// in order to add icons to inputs
					element.addClass( "has-feedback" );

					if ( element.prop( "type" ) === "checkbox" ) {
						error.insertAfter( element.parent( "label" ) );
					} else {
						error.insertAfter( element );
					}

					// Add the span element, if doesn't exists, and apply the icon classes to it.
					if ( !element.next( "span" )[ 0 ] ) {
						$( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element ).css("color", "#FF0000");
					}
				},
				success: function ( label, element ) {
					// Add the span element, if doesn't exists, and apply the icon classes to it.
					if ( !$( element ).next( "span" )[ 0 ] ) {
						$( "<span class='glyphicon glyphicon-ok form-control-feedback'></span>" ).insertAfter( $( element ) ).css("color", "green");
					}
				},
				highlight: function ( element, errorClass, validClass ) {
					$( element ).addClass( "has-error" ).removeClass( "has-success" );
					$( element ).next( "span" ).addClass( "glyphicon-remove" ).removeClass( "glyphicon-ok" ).css("color", "#FF0000");
				},
				unhighlight: function ( element, errorClass, validClass ) {
					$( element ).addClass( "has-success" ).removeClass( "has-error" );
					$( element ).next( "span" ).addClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" ).css("color", "green");
				}
			} );
		} );


