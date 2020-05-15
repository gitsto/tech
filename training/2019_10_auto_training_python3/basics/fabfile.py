# © Copyright IPC Systems, Inc. All Rights Reserved

from fabric import Connection
def ls_file():
    c = Connection('root@192.168.49.78:22')
    c.run('ls -lrt')
