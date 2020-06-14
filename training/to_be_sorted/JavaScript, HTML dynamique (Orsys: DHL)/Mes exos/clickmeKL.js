
	var fct2 = function() {
			alert("CLICK")
	}
	
	function initKL() {
		var el = document.getElementById("clickme");
		el.addEventListener('click',fct2,false);
	
		f = document.querySelector("form");
		console.log('init submit for form: ' + f.id);
		f.addEventListener('submit', "nav('google.fr')")
	}
	
	function resetForm(objId) {
		f = document.getElementById(objId);
		console.log('reset form: ' + f.id);
		f.reset();
	}
	
	function switchFormVisibility(objId) {
		f = document.querySelector("form");
		console.log('reset form: ' + f.id);
		
		var visibility = f.style.display;
		
		if (visibility == "none") {
			f.style.display = "block"
		} else {
			f.style.display = "none"
		}
		
	}
	function nav(uri) {
		window.open("www."+uri);
	}
/*
	detect le clic sur submit => google.fr
	*/