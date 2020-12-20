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
	 });