# List of Random Commands for K8s

>kubectl get svc -n slic

```shell
[root@k8s-master-cao ipctech]# kubectl get svc -n slic
NAME                TYPE           CLUSTER-IP     EXTERNAL-IP     PORT(S)                        AGE
slic-filebeat-svc   LoadBalancer   10.233.3.121   172.17.1.166    5044:32144/TCP                 20h
slic-slicws-svc     LoadBalancer   10.233.3.177   172.17.16.111   3000:31838/TCP,443:30792/TCP   20h
slic-syslog-svc     LoadBalancer   10.233.1.176   172.17.1.165    514:32424/UDP,516:30486/UDP    20h
```
