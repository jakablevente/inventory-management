	 $('document').ready(function(){
		 $('.eBtn').on('click',function(event){
			event.preventDefault();
			
			var href=$(this).attr('href');

			$.get(href,function (category, status){
				$('.myForm #id').val(category.id);
				$('.myForm #name').val(category.categoryName);
			});
			
			$('.myForm #editModal').modal();
	
		 });
	
		$('.eBrand').on('click',function(event){
			event.preventDefault();
			
			href=$(this).attr('href');
			
			$.get(href,function(brand, status){
				$('.editBrandForm #id').val(brand.id);
				$('.editBrandForm #name').val(brand.name);
			});
			$('.editBrandForm #editBrandModal').modal();
		});
		
		$('.editProd').on('click',function(event){
			event.preventDefault();
			
			href=$(this).attr('href');
			
			$.get(href,function(product, status){
				$('.editProductForm #id').val(product.id);
				$('.editProductForm #name').val(product.name);
				$('.editProductForm #price').val(product.price);
				$('.editProductForm #qty').val(product.qty);
				$('.editProductForm #catSelect').val(product.category.id)
				$('.editProductForm #brandSelect').val(product.brands.id)
			});
			$('.editProductForm #editProductModal').modal();
		});
		
			$('.editCustomer').on('click',function(event){
			event.preventDefault();
			
			href=$(this).attr('href');
			
			$.get(href,function(customer, status){
				$('.editCustomerForm #id').val(customer.id);
				$('.editCustomerForm #name').val(customer.name);
				$('.editCustomerForm #email').val(customer.email);
				$('.editCustomerForm #phone').val(customer.phone);
				$('.editCustomerForm #address').val(customer.address);
			});
			$('.editCustomerForm #editCustomerModal').modal();
		});
		
		$('.editUser').on('click',function(event){
			event.preventDefault();
			
			href=$(this).attr('href');
			
			$.get(href,function(user, status){
				$('.editUserForm #id').val(user.id);
				$('.editUserForm #username').val(user.username);
				$('.editUserForm #password').val(user.password);
				$('.editUserForm #roles').val(user.roles.getRoles().id);
				$('.editUserForm #enabled1 ').prop('checked', true);

			});
			$('.editUserForm #editUserModal').modal();
		});
		
		   $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });



	$('#productSubmit').click(function(){
		var isFormValid = true;

	$("#addProductModal input").each(function(){
    if ($.trim($(this).val()).length == 0 ||($(this).val()) < 1 ){
        $(this).addClass("is-invalid");
        isFormValid = false;
        $(this).focus();
    }
    else{
        $(this).removeClass("is-invalid");
     }
    });
return isFormValid;
	});
	
		$('#categorySubmit').click(function(){
		var isFormValid = true;

	$("#addCategoryModal input").each(function(){
    if ($.trim($(this).val()).length == 0 ||($(this).val()) < 1 ){
        $(this).addClass("is-invalid");
        isFormValid = false;
        $(this).focus();
    }
    else{
        $(this).removeClass("is-invalid");
     }
    });
return isFormValid;
	});
	
	$('#brandSubmit').click(function(){
		var isFormValid = true;

	$("#addBrandModal input").each(function(){
    if ($.trim($(this).val()).length == 0 ||($(this).val()) < 1 ){
        $(this).addClass("is-invalid");
        isFormValid = false;
        $(this).focus();
    }
    else{
        $(this).removeClass("is-invalid");
     }
    });
return isFormValid;
	});
	
	$('#customerSubmit').click(function(){
		var isFormValid = true;

	$("#addCustomerModal input").each(function(){
    if ($.trim($(this).val()).length == 0 ||($(this).val()) < 1 ){
        $(this).addClass("is-invalid");
        isFormValid = false;
        $(this).focus();
    }
    else{
        $(this).removeClass("is-invalid");
     }
    });
return isFormValid;
	});
	


	 });