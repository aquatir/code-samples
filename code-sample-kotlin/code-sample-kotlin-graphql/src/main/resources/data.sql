insert into PRODUCT(id, price, quantity) values
  (1, 10.0, 2),
  (2, 1.0, 4),
  (3, 2.5, 3),
  (4, 7.0, 99),
  (5, 5.0, 5);

insert into CART(id) values
  (1),
  (2);

insert into CART_PRODUCTS(CART_ID, PRODUCT_ID) values
  (1, 1),
  (1, 2),
  (1, 3),
  (2, 2),
  (2, 4),
  (2, 5);
