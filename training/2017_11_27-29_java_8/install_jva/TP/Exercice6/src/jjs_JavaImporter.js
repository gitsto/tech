/**
 * jjs_JavaImporter.js
 */
var java	= new JavaImporter(java.lang, java.util, java.time);

with(java) {

	var task	= new TimerTask() {
		run: function() {
			print("jjs_JavaImporter.js ");
			print(Instant.now());
		}
	}

	new Timer().schedule(task, 0, 1000);
	Thread.sleep(10000);
	task.cancel();
}
