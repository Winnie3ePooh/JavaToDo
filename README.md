# Java_ToDo
ToDo list on java Spring
We are using Hibername, Spring and some js framework, bitchezz!!

**Database deploy**
Установите [vagrant](http://www.vagrantup.com/downloads)

```
mkdir ~/todo
cd ~/todo
vagrant init ubuntu/trusty64
vagrant up
vagrant ssh
```

Далее устанавливаем mysql
```
sudo apt-get install mysql-server
```

Придумайте пароль администратора(root) базы
````
mysql -u root -p
```

Введите пароль администратора
```
CREATE DATABASE todo_develop;
```

Замените password новым паролем для пользователя todo
```
CREATE USER "todo"@"localhost" IDENTIFIED BY "password";
GRANT ALL PRIVILEGES ON todo_develop.* TO "todo"@"localhost";
FLUSH PRIVILEGES;
```

В VagrantFile добавляем строку:
```
config.vm.network "forwarded_port", guest: 3306, host: 3307
```

Даем доступ к mysql-server на гостевой(vagrant) с хоста:
В гостевой машине с mysql-server изменяем конфиг, комментируем строки
```
sudo nano /etc/mysql/my.cnf
skip-external-locking
bind-address
```

Входим с под root в mysql
```
mysql -u root -p
GRANT ALL PRIVILEGES ON todo_develop.* TO "todo"@"10.0.2.2" IDENTIFIED BY '4Ttm2x';
FLUSH PRIVILEGES;
```

Выйдите из mysql shall (Ctrl+C)
Заливаем .sql файл с таблицами
```
cd /Vagrant
mysql -u todo -p todo_develop < tables.sql
```
Заходим в базу под пользователем todo
```
mysql -u todo -p
```
