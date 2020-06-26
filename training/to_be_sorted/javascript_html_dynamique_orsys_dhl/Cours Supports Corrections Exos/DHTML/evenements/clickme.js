

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
			var ff = document.querySelector("form");
			ff.reset();
		}

		var winGoogle = function(evt) {
			evt.preventDefault();
			var target = evt.target || evt.srcElement;
			var inp = document.querySelector("input[type]");
			var url = inp.value;
			if (!url.startsWith('http://')) {
				url = 'http://' + url;
				inp.value = url;
			}
			window.open(url);
			return false;
		}
		
		
		var toggleView = function() {
			var ff = document.querySelector("form");
			var vis = ff.style.display;
			if (vis!= 'none') 
				ff.style.display = 'none';
			else 
				ff.style.display = 'block';
		}

		el.addEventListener('click',fct1,false);
		el.addEventListener('mouseover',mo1,false);
		bt.addEventListener('click',bt1,false);
		
		fo = document.querySelector("form");
		fo.addEventListener('submit',function(e) { winGoogle(e) },false);

		im = document.querySelector("img");
		im.addEventListener('click',toggleView,false);
	}
	
	function getRandomColor() {
		var letters = '0123456789ABCDEF';
		var color = '#';
		for (var i = 0; i < 6; i++ ) {
			color += letters[Math.floor(Math.random() * 16)];
		}
		return color;
	}
	