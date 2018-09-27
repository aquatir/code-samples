insert into PRODUCT(id, name, total_Quantity, price, picture_Description, picture_Url) values
  (1, 'milk', 50, 10.0, 'a picture of milk', 'http://milk.ru'),
  (2, 'bread', 150, 3.0, 'a picture of bread', 'http://bread.ru'),
  (3, 'butter', 30, 5.0, 'a picture of butter', 'http://butter.ru'),
  (4, 'salt', 40, 7.0, 'a picture of salt', 'http://salt.ru'),
  (5, 'meat', 60, 30.0, 'a picture of meat', 'http://meat.ru');

insert into CART(id) values
  (1),
  (2);

insert into CART_ITEM(id, cart_id, quantity, product_id) values
  (1, 1, 4, 1),
  (2, 1, 2, 3),
  (3, 1, 4, 2),
  (4, 1, 9, 4),
  (5, 2, 1, 3),
  (6, 2, 1, 4),
  (7, 2, 1, 5);
