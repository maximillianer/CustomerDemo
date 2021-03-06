# CustomerDemo
эта программа возвращает работников (Customers), хранящихся в базе данных PostgreSQL (jdbc:postgresql://localhost:5432/customers) 
в SWT системе (mySWTApplication) по заданным критериям в поиске

для работы необходимо запустить mySWTApplication
в появившемся окне установить значения и критерий поиска в выпадающем списке, нажать "поиск",
чтобы получить всех работников - нажать "показать всех",
чтобы стереть данные - "очистить"

База данных содержит 1000 случайных работников (Customers) на англ. языке, у которых есть имя (first_name), фамилия (last_name), 
адрес (adress), дата рождения (date_of_birth) и зарплата (budget) 

данные пока выводятся в чистом виде, в настоящее время дорабатывается интерфейс отображения данных в окне вывода в виде таблицы
