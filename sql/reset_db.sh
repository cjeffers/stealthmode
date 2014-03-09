#! /bin/bash

mysql -h mysql-user.stanford.edu --user=ccs108jvangogh --password=ahweeyoi < \
metropolises.sql

mysql -h mysql-user.stanford.edu --user=ccs108jvangogh --password=ahweeyoi < \
users.sql

mysql -h mysql-user.stanford.edu --user=ccs108jvangogh --password=ahweeyoi < \
questions.sql
