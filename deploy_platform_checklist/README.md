# Reinstall CAO

## Remove existing VMs from platform

Connect to esxi cao1 <https://172.17.1.97/ui/#/login>, then stop, and remove all the VMs: master, worker 1 & 2

Then,

Connect to [vcenter interface](https://172.17.1.103/ui/#?extensionId=vsphere.core.relateditems.specs.datacenter.vms.vmsForDatacenter&objectId=urn:vmomi:Datacenter:datacenter-1084:28c1b61a-9eb7-4a33-8b91-8583ff374a3d&navigator=vsphere.core.viTree.hostsAndClustersView
)

>vcenter_username: Administrator@vsphere.local  
>vcenter_password: Admin@94120

expand one unused Datacenter of a platform (check with your colleagues), and remove the node having the ip address has a name

## Virtualization playbooks

according to the doc
modify:

- [x] `ipc-deploy_cao_virtualization-XXXXXXXX\roles\create-vm\defaults\main.yml`
- [x] setup_hypervisor.yml
- [x] setup_vms.yaml

then start
>ansible-playbook setup_hypervisor.yml  

at this point, you could have an error, don't panic relaunch the command with ooption "-vvv"  
if its this one, you can continue to next playbook  

```error
"msg": "Failed to add host 172.17.1.97 to vCenter: The name '172.17.1.97' already exists."
```

then

>ansible-playbook setup_vms.yaml

## K8s-cluster playbooks

Go to esxi ui: <https://192.168.81.74/ui/#/login> (cao4 example)

- stop one by one the VMs: master, worker 1 & 2
- restore snapshot
- start all 3 VMs together

then prepare your playbooks with correct conf files

- [x] hosts.ini
- [x] all_variables.yml

after customization, you can save files for next time.

>ansible -m ping -i inventory/**cao4**/hosts.ini --become --become-user=ipctech --private-key ~/.ssh/id_rsa all

then install

>ansible-playbook -i inventory/**cao4**/hosts.ini --flush-cache --private-key ~/.ssh/id_rsa cao-platform.yml -e "@inventory/**cao4**/all_variables.yml"

>nohup ansible-playbook -i inventory/cao4/ion.ini cao-platform.yml -e "@inventory/cao4/all_variables.yml" --flush-cache > cao4-newdeploy.log &

## COSA APP

then install COSA, don't forget to prepare `hosts` and `cosa_app_k8s_deploy.yml` for the correct branch and gitea server.

>ansible-playbook -i hosts --flush-cache --private-key ~/.ssh/id_rsa cosa_app_k8s_deploy.yml

after update of a version, please execute this command to 'accelerate' redeploy of updated apps

>fluxctl --k8s-fwd-ns=flux sync

OTHER NOTES............................
>ansible -m shell -i inventory/cao3/hosts.ini --become --become-user=root k8s-cluster -a 'systemctl stop kubelet'
