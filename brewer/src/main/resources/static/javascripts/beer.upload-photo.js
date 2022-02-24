var Brewer = Brewer || {};

Brewer.UploadPhoto = (function() {
	
	function UploadPhoto() {
		this.inputPhotoName = $('input[name=photo]');
		this.inputContentType = $('input[name=contentType]');
		this.newPhoto = $('input[name=newPhoto]');
		
		this.htmlPhotoBeerTemplate = $('#photo-beer').html();
		this.template = Handlebars.compile(this.htmlPhotoBeerTemplate);
		
		this.containerPhotoBeer = $('.js-container-photo-beer');
		
		this.uploadDrop = $('#upload-drop');
		
		
	}
	
	UploadPhoto.prototype.start = function() {
		var settings = {
				type: 'json',
				filelimit: 1,
				allow: '*.(jpg|jpeg|png)',
				action: this.containerPhotoBeer.data('url-photos'),
				complete: onUploadComplete.bind(this),
				beforeSend: addCsrfToken
		}
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
		
		if(this.inputPhotoName.val()) {
			toRenderPhoto.call(this, { name: this.inputPhotoName.val(), contentType: this.inputContentType.val() });
		}
	}
	
	function onUploadComplete(response) {
		this.newPhoto.val('true');
		toRenderPhoto.call(this, response)
	}
	
	function toRenderPhoto(response) {
		this.inputPhotoName.val(response.name);
		this.inputContentType.val(response.contentType);
		
		this.uploadDrop.addClass('hidden');
		
		var photo = '';
		if (this.newPhoto.val() == 'true') {
			photo = 'temp/';
		}
		photo += response.name;
		
		var htmlPhotoBeer = this.template({ photo: photo });
		this.containerPhotoBeer.append(htmlPhotoBeer);
		
		$('.js-remove-photo').on('click', onRemovePhoto.bind(this));
	}
	
	function onRemovePhoto() {
		$('.js-photo-beer').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputPhotoName.val('');
		this.inputContentType.val('');
		this.newPhoto.val('false');
	}
	
	function addCsrfToken(xhr) {
		var token = $('input[name=_csrf]').val();
		var header = $('input[name=_csrf_header]').val();
		xhr.setRequestHeader(header, token);
	}
	
	return UploadPhoto;
	
})();

$(function() {
	var uploadPhoto = new Brewer.UploadPhoto();
	uploadPhoto.start();
});
