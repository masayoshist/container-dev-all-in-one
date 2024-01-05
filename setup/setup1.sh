#!/usr/bin/sh -x

dnf update -y
dnf install net-tools -y

systemctl stop firewalld
systemctl disable firewalld

setenforce 0
sed -i s/SELINUX=enforcing/SELINUX=disabled/ /etc/selinux/config

echo "Please Reboot and Execute setup2.sh"
