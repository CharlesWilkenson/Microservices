$(document).ready(function(){
	
			$.validator.addMethod('date', function(value, element, param) {
				return (value != 0) && (value <= 31) && (value == parseInt(value, 10));
			}, 'Please enter a valid date!');
			$.validator.addMethod('month', function(value, element, param) {
				return (value != 0) && (value <= 12) && (value == parseInt(value, 10));
			}, 'Please enter a valid month!');
			$.validator.addMethod('year', function(value, element, param) {
				return (value != 0) && (value >= 1900) && (value == parseInt(value, 10));
			}, 'Please enter a valid year not less than 1900!');
			$.validator.addMethod('username', function(value, element, param) {
				var nameRegex = /^[a-zA-Z0-9]+$/;
				return value.match(nameRegex);
			}, 'Only a-z, A-Z, 0-9 characters are allowed');

			var val	=	{
			    // Specify validation rules
			    rules: {
			     
				    codeEmploye: {
					        required: true,
					     
					      },
						  
						      codeClient: {
					        required: true,
					       
					      },
			      email: {
					        required: true,
					        email: true
					      },
					phone: {
						required:true,
						<!-- minlength:5, -->
						<!-- maxlength:10, -->
						<!-- digits:true -->
					},
					dateBirth:{
						<!-- date:true, -->
						required:true,
						<!-- minlength:2, -->
						<!-- maxlength:2, -->
						<!-- digits:true -->
					},
						placeBirth:{
				
						required:true,
						
					},
					citizenship:{
				
						required:true,
						minlength:4,
					},
					
						adress:{
				
						required:true,
						minlength:5,
					},
					
					identification:{
						<!-- month:true, -->
						required:true,
						<!-- minlength:2, -->
						<!-- maxlength:2, -->
						<!-- digits:true -->
					},
					sex:{
					<!-- 	year:true, -->
						required:true,
						
					},
					lastname:{
						username:true,
						required:true,
						minlength:4,
						maxlength:16,
					},
					
					firstname:{
						username:true,
						required:true,
						minlength:4,
						maxlength:16,
					},
					
					maritalStatus:{
				
						required:true,
						
					},
						number:{
				
						required:true,
						digits:true
						
					},
						soldeC:{
				
						required:true,
						digits:true 
						
					},
					soldeD:{
				
						required:true,
						digits:true 
						
					},
					employeType:{
				
						required:true,
						
					},
					fonction:{
				
						required:true,
						
					},
						type:{
				
						required:true,
						
					},
					
					
					decouvert:{
				
						required:true,
						digits:true
						
					},
					
					department:{
				
						required:true,
						
					},
						deviseC:{
				
						required:true,
						
					},
					
					deviseE:{
				
						required:true,
						
					},
					accountType:{
				
						required:true,
						
					},
						city:{
						required:true,
						minlength:3,
						
					},
					
						myCountry:{
						required:true,
						minlength:3,
						
					},
					
						adress:{
						required:true,
						minlength:5,
						
					},
					
					street:{
						required:true,
						minlength:3,
						<!-- maxlength:16, -->
					}
			    },
			    // Specify validation error messages
			    messages: {
					fname: 		"First name is required",
					email: {
						required: 	"Email is required",
						email: 		"Please enter a valid e-mail",
					},
					phone:{
						required: 	"Phone number is requied",
						<!-- minlength: 	"Please enter 10 digit mobile number", -->
						<!-- maxlength: 	"Please enter 10 digit mobile number", -->
						<!-- digits: 	"Only numbers are allowed in this field" -->
					},
					dateBirth:{
						required: 	"Date is required",
						<!-- minlength: 	"Date should be a 2 digit number, e.i., 01 or 20", -->
					<!-- 	maxlength: 	"Date should be a 2 digit number, e.i., 01 or 20", -->
						<!-- digits: 	"Date should be a number" -->
					},
					street:{
						required: 	"Street is required",
					minlength: 	"Street should be minimum 4 characters",
						
					},
					city:{
						required: 	"City is required",
					minlength: 	"City should be minimum 4 characters",
					},
					
					citizenship:{
						required: 	"Choose the country",
					minlength: 	"Country should be minimum 4 characters",
					},
					lastname:{
						required: 	"Lastname is required",
						minlength: 	"Lastname should be minimum 4 characters",
						maxlength: 	"Lastname should be maximum 16 characters",
					},
					
						firstname:{
						required: 	"firstname is required",
						minlength: 	"firstname should be minimum 4 characters",
						maxlength: 	"firstname should be maximum 16 characters",
					},
					
					
						employeType:{
						required: "Type of client is required",
					
					},
					
						maritalStatus:{
						required: "Choose the MaritalStatus",
					
					},
					identification:{
						required: "ID is required",
					
					},
					
					codeEmploye:{
						required: "Code is required",
					
					},
					
					
						codeClient:{
						required: "Code is required",
					
					},
					
						sex:{
						required: "Choose the sex",
					
					},
						deviseC:{
						required: 	"Devise is required",
					
					},
						deviseE:{
						required: 	"Devise is required",
					
					},
					department:{
						required: 	"Department is required",
					
					},
					number:{
						required: 	"Number is required",
						digits: 	"Only numbers are allowed in this field" 
					
					},
					type:{
						required: 	"Type is required",
					
					},
					decouvert:{
						required: 	"Decouvert is required",
					digits: 	"Only numbers are allowed in this field" 
					},
					
						accountType:{
						required: 	"Type is required",
					
					},
					soldeC:{
						required: 	"Solde is required",
						digits: 	"Only numbers are allowed in this field" 
					},
					soldeE:{
						required: 	"Solde is required",
						digits: 	"Only numbers are allowed in this field" 
					},
					fonction:{
						required: "Fonction is required",
				
					}
			    }
				
			}
			$("#myForm").multiStepForm(
			{
				// defaultStep:0,
				    beforeSubmit : function(form, submit){
					console.log("called before submiting the form");
					console.log(form);
					console.log(submit);
				},
				validations:val,
			}
			).navigateTo(0);
			
			
			
		});
		
		
		
		
			$( document ).ready( function () {
			
			

			$( "#code" ).validate( {
						 errorClass: "my-error-class",
						   validClass: "my-valid-class",
					
						rules: {
						
							code:{
								required:true,
								
				                 }
							
																	
						},
						messages: {
						
					code:{
								required:"Code is required",
								
							}
						
							
						
						},
						errorElement: "em",
						errorPlacement: function ( error, element ) {
							// Add the `help-block` class to the error element
							error.addClass( "help-block" );

							// Add `has-feedback` class to the parent div.form-group
							// in order to add icons to inputs
							element.addClass( "has-feedback" ).css("color", "#FF0000");;

							if ( element.prop( "type" ) === "checkbox" ) {
								error.insertAfter( element.parent( "label" ) );
							} else {
								error.insertAfter( element );
							}

						
						},
				
					
					} );
				} );