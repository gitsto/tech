#!/bin/bash

jira="http://192.168.49.49:8080/secure/Dashboard.jspa"
bypasslist="*resa-salles*;127.0.0.1:8080"
proxyfile="http://192.168.2.22:81/proxy/config.proxy"
#google-chrome --proxy-pac-url="http://192.168.2.22:81/proxy/config.proxy" "$jira" & 
google-chrome --proxy-bypass-list="$bypasslist" --proxy-pac-url="$proxyfile" &