#!/bin/sh

# Simple autostart file for i3-wm, you can execute it from i3 config with
# exec $HOME/bin/auto-start-for-i3-simple
#
# xdotool and xmessage must be installed

# Wait for program
wait_for_program () {
    n=0
    while true
    do
	# PID of last background command
	if xdotool search --onlyvisible --pid $!; then
	    break
	else
	    # 20 seconds timeout
	    if [ $n -eq 20 ]; then
		xmessage "Error on executing"
		break
	    else
		n=`expr $n + 1`
		sleep 1
	    fi
	fi
    done
}

# Start some programs
#  _____________________
# |          |          |
# |  gedit   |  term    |
# |          |          |
# |          |----------|
# |          |sunflower |
# |__________|__________|

i3-msg workspace 1:tools
gedit &
#wait_for_program
i3-msg split h
gnome-terminal &
#wait_for_program
i3-msg split v
sunflower &
#wait_for_program

i3-msg workspace 2:ide
~/bin/scripts/eclipse.sh &

i3-msg workspace 3:im

#  _____________________
# |         |          |
# |  chrome |thunderbird|
# |         |          |
# |         |----------|
# |         |  xchat   |
# |_________|__________|

exec ~/bin/scripts/chrome.sh &
i3-msg split h
exec thunderbird &
i3-msg split v
exec xchat &

exit 0

