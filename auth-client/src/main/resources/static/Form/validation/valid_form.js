
/****************************VALIDATION MAILSENDER*************************/
		$.validator.setDefaults( {
			submitHandler: function () {
				$(form).ajaxSubmit();
			}
		} );
		

		/****************************VERIFICATION MESSAGE*************************/

		$( document ).ready( function () {
			$.validator.addMethod("valid", function(value, element) {
				if (this.optional(element)) {
					return true;
				}
			    return /^[a-zA-Z \s]+$/.test(value);
			}, "Charactere invalide");
			

			$( "#login" ).validate( {
						 errorClass: "my-error-class",
						   validClass: "my-valid-class",
					
						rules: {
						
							mail:{
								required:true,
								
				                 },
							
							passowrd:{
								required:true,
							
				
							},
							
							
																		
						},
						messages: {
							mail:{
								required:"Entrer votre email",
								
							},
							password:{
								required:"Ecrivez votre message",
								
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

						
						},
				
					
					} );
				} );