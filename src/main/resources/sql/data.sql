/* employee */
insert into employee (employee_id, employee_name, age) values (1, '山田　太郎', 30);
/* m_user */
insert into m_user (user_id, password, user_name, birthday, age, marriage, role)
 values ('yamada@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '山田 太郎', '1990-01-01', 28, false, 'ROLE_ADMIN');
insert into m_user (user_id, password, user_name, birthday, age, marriage, role)
 values ('tamura@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '田村 雄一', '1986-11-05', 31, false, 'ROLE_GENERAL');