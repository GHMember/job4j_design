begin transaction

declare product_cursor cursor for select * from products;

move last from product_cursor;

fetch backward 5 from product_cursor;

fetch backward 8 from product_cursor;

fetch backward 5 from product_cursor;

fetch backward from product_cursor;

close product_cursor;

commit;