#!/usr/bin/sh -x

### gitインストール
dnf install git -y

### 
curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py
python get-pip.py --user
pip install --user ansible
