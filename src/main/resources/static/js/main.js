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
				$('.editUserForm #roles').val(user.roles);
				$('.editUserForm  #enabled').val(user.enabled);
			});
			$('.editUserForm #editUserModal').modal();
		});

	 });