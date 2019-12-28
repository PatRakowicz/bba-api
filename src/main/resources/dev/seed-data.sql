insert into bb_user (created, active, login_name, login_pass) values (sysdate(), 'Y', 'demo1', 'demo');
insert into bb_user (created, active, login_name, login_pass) values (sysdate(), 'Y', 'demo2', 'demo');
insert into bb_user (created, active, login_name, login_pass) values (sysdate(), 'Y', 'demo3', 'demo');

insert into bb_account (created, status, business_name, business_type, owner_name, email, phone, sales_tax) values (sysdate(), 'A', 'Demo 1 LLC', 'T', 'Demo One', 'demo1@test.com', '303-123-1111', 5.1);
insert into bb_account (created, status, business_name, business_type, owner_name, email, phone, sales_tax) values (sysdate(), 'A', 'Demo 1 LLC', 'T', 'Demo Two', 'demo2@test.com', '303-123-2222', 5.1);
insert into bb_account (created, status, business_name, business_type, owner_name, email, phone, sales_tax) values (sysdate(), 'A', 'Demo 1 LLC', 'T', 'Demo Three', 'demo3@test.com', '303-123-3333', 5.1);

insert into bb_user_account (user_id, account_id) values (1, 1);
insert into bb_user_account (user_id, account_id) values (2, 2);
insert into bb_user_account (user_id, account_id) values (3, 3);

insert into bb_settings (ACCOUNT_ID, KEY_, VAL_) values (1, 'timezone', 'US/Mountain');
insert into bb_settings (ACCOUNT_ID, KEY_, VAL_) values (2, 'timezone', 'US/Eastern');
insert into bb_settings (ACCOUNT_ID, KEY_, VAL_) values (3, 'timezone', 'US/Pacific');
