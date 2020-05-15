/**
 * jjs_Packages.js
 */
var java	= Packages.java;
var time	= Packages.java.time;
var util	= Packages.java.util;

var task	= new java.util.TimerTask() {
	run: function() {
		print("jjs_Packages.js ");
		print(time.Instant.now());
	}
}

new util.Timer().schedule(task, 0, 1000);
java.lang.Thread.sleep(10000);
task.cancel();
