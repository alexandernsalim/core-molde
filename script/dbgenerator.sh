#!/bin/sh
password = ""

sudo -S -u postgres createdb <dbname> template molde_shop_template_db <<< $password