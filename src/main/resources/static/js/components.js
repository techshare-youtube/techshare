$(function() {
	// Toast
	$(document).on('click', '#toast-close', function() {
		$('#toast').addClass('d-none');
	});
	// Collapse
	$(document).on('click', '#toggler', function() {
		$(this).find('.toggler-text').each(function(_index, element) {
			$(element).toggleClass('d-none');
		});
	});
});