/**
 * jjs_ShortPackages.js
 */
var Thread		= Packages.java.lang.Thread;
var TimerTask	= Packages.java.util.TimerTask;
var Timer		= Packages.java.util.Timer;
var Instant		= Packages.java.time.Instant;

var task	= new TimerTask() {
	run: function() {
		print("jjs_ShortPackages.js ");
		print(Instant.now());
	}
}

new Timer().schedule(task, 0, 1000);
Thread.sleep(10000);
task.cancel();
