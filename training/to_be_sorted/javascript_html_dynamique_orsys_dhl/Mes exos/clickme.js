
	function init() {
		var el = document.getElementById("clickme");
		var fct1 = function() {
			ici = document.getElementById("ici");
			//ht = ici.innerHTML;
			ici.innerHTML+="X";
			el.style.backgroundColor = getRandomColor();
		}

		var mo1 = function() {
			el.style.backgroundColor = getRandomColor();
		}
		
		var bt = document.querySelector("button");
		bt1 = function() {
			var el = document.getElementById("clickme");
			el.removeEventListener('click',fct1,false);		
		}

		el.addEventListener('click',fct1,false);
		el.addEventListener('mouseover',mo1,false);
		bt.addEventListener('click',bt1,false);
	}
	
	function getRandomColor() {
		var letters = '0123456789ABCDEF';
		var color = '#';
		for (var i = 0; i < 6; i++ ) {
			color += letters[Math.floor(Math.random() * 16)];
		}
		return color;
	}
	