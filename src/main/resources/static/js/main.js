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
	 });