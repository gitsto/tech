function isEmpty(str) {
    return (!str || 0 === str.length);
}

function validForm(evt) {
	tgt = evt.target || evt.srcElement;
	isvalidated = true;
	for (var idx=0; idx<tgt.length; idx++) {
		
		if (typeof tgt[idx] === 'object') {
			type = tgt[idx].type;
			switch (type) {
				case 'email':
				case 'text':
				case 'tel' : 
					isvalidated = isvalidated && !isEmpty(tgt[idx].value);
					break;
				case 'radio' :
				case 'checkbox' :
					isvalidated = isvalidated && tgt[idx].checked;
			}
		}
	}

	var msg = isvalidated ? "Formulaire OK" : "Formulaire PAS OK";
	if (!isvalidated) evt.preventDefault();
	 
	//var dv = document.createElement("div");
	var dv = document.getElementById("formresult");
	dv.innerHTML = msg;
	//tgt.parentNode.appendChild(dv); 

}

function initForms() {
		allforms = document.querySelectorAll("form");
		for(var index in allforms) { 
			var oneform = allforms[index];
			if (typeof oneform === 'object') { 
				oneform.addEventListener("submit" , function(e) {validForm(e)} , false);
			}
		}
}
