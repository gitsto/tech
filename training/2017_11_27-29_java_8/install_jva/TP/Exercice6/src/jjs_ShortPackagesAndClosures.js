/**
 * jjs_ShortPackagesAndClosures.js
 */
var java	= new JavaImporter(java.lang, java.util, java.time);

with(java) {
	var timer	= new Timer();
	
	timer.schedule(function() {
		print("jjs_ShortPackagesAndClosures.js ");
		print(Instant.now());
	}, 0, 1000);
	
	Thread.sleep(10000);
	timer.cancel();
}
