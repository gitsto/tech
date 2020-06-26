
	var fct2 = function() {
			alert("CLICK")
	}
	
	function initKL() {
		var el = document.getElementById("clickme");
		el.addEventListener('click',fct2,false);	
	}