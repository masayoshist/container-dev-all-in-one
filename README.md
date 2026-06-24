# container-dev-all-in-one

## Install RockyLinux10.2
Install Rocky-10.2-x86_64-minimal

## OS Setup
Copy & Paste via terminal-emulator , it is GOOD.
```
# vi setup1.sh
# vi setup2.sh
# chmod 755 setup1.sh setup2.sh
# ./setup1.sh
# reboot
# ./setup2.sh
```

## Install Package by Ansible-Playbook
```
# git clone https://github.com/masayoshist/container-dev-all-in-one.git
# cd container-dev-all-in-one/
# ansible-playbook playbook-build-rockylinux10.yml
```

## Docker-Compose
```
# docker-compose --progress plain up -d
```
After Console back, you must wait for docker containar start. (gitlab's bundle is so long.)

## Gitlab First Login

### Add hosts Entry
Please add your server's IPaddr to hosts file of client PC.

### Get Gitlab InisitalPassword
```
# cat /opt/gitlab/config/initial_root_password
```

### First Login & ChangePassword
- http://dev.sample.local:8929/
- Login use id:'root' pw:InisitalPassword
- After first-login, change root password. It is good.


## Create InstanceRunner

### Create
- In root login, Access http://dev.sample.local:8929/admin/runners
- Create New Instance Runner
  - `Run untagged jobs` checked is good.
- Get RunnerToken.

### Edit Runner Config
change token with RunnerToken.
change extra_hosts setting your server's IPAddr.
```
# vi config.toml
...
token = "glrt-********************"  # <-
...
extra_hosts = ['dev.sample.local:XXX.XXX.XXX.XXX'] # <-
...
```

```
# cp config.toml /etc/gitlab-runner/config.toml
# systemctl restart gitlab-runner
```

## SonarQube Setting

This content is preparing.

## CI Test

### Create menu-app Project
- Create blank project

### Create PAT
- upper right's user icon > Preferences > Access > Personal Access tokens > Add new token
- Generate token, with checked...
  - read_repository
  - write_repository


### Push menu-app Sources
GitAuth is...
- Username: root
- Password: GeneratedToken Strings

```
# cd /root/
# git clone http://dev.sample.local:8929/root/menu-app.git
# cp -r container-dev-all-in-one/menu-app ./
# cd menu-app
# git config --global user.email "root@dev.sample.local"
# git config --global user.name "root"
# git add .
# git commit -m "first commit"
# git push origin main
```

### Run CI Pipeline
- On push, CI Pipeline run. You can watch Running Pipeline.
- After Pipeline finished, You can watch Built Container image in this repository's registry.

## CD Test 

### Create menu-app-deploy Project
- Create blank project

### Push menu-app Sources
```
# cd /root/
# git clone http://dev.sample.local:8929/root/menu-app-deploy.git
# cp -r container-dev-all-in-one/menu-app-deploy ./
# cd menu-app-deploy
# git add .
# git commit -m "first commit"
# git push origin main
```

### Run CD Pipeline
- Please run pipeline.
- CD Pipeline is manual start. You need to run CD Pipeline.
- On default, it is setting Latest-image and for-develop-configuration.

### Check Deployment
After CD pipeline, you watch Menu-app in http://<IPaddr> on Client Browser.

