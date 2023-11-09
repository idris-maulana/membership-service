-- public.banners definition

-- Drop table

-- DROP TABLE public.banners;

CREATE TABLE banners (
	id int8 NOT NULL,
	"version" int8 NULL,
	banner_image varchar(255) NULL,
	banner_name varchar(255) NULL,
	description varchar(255) NULL,
	CONSTRAINT banners_pkey PRIMARY KEY (id)
);


-- public.images definition

-- Drop table

-- DROP TABLE public.images;

CREATE TABLE images (
	id int8 NOT NULL,
	"version" int8 NULL,
	filename varchar(255) NULL,
	raw oid NULL,
	CONSTRAINT images_pkey PRIMARY KEY (id),
	CONSTRAINT uk_gepakjgnrlvvh3r6y98q6ikei UNIQUE (filename)
);


-- public.services definition

-- Drop table

-- DROP TABLE public.services;

CREATE TABLE services (
	id int8 NOT NULL,
	"version" int8 NULL,
	service_code varchar(255) NULL,
	service_icon varchar(255) NULL,
	service_name varchar(255) NULL,
	service_tarif int8 NULL,
	CONSTRAINT services_pkey PRIMARY KEY (id),
	CONSTRAINT uk_rm9rfu0ekvjb9y1ff8blnaf0i UNIQUE (service_code)
);


-- public.transaction_histories definition

-- Drop table

-- DROP TABLE public.transaction_histories;

CREATE TABLE transaction_histories (
	id int8 NOT NULL,
	"version" int8 NULL,
	created_on timestamp NULL,
	description varchar(255) NULL,
	invoice_number varchar(255) NULL,
	total_amount int8 NULL,
	transaction_type varchar(255) NULL,
	user_id int8 NULL,
	CONSTRAINT transaction_histories_pkey PRIMARY KEY (id),
	CONSTRAINT uk_3afqe31orfaidi6xoi4aoo49t UNIQUE (invoice_number)
);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE users (
	id int8 NOT NULL,
	"version" int8 NULL,
	email varchar(255) NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	"password" varchar(255) NULL,
	profile_image varchar(255) NULL,
	balance int8 NULL,
	CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

INSERT INTO banners(id,banner_name,banner_image,description) VALUES (1,'Banner 1','https://nutech-integrasi.app/dummy.jpg','Lerem Ipsum Dolor sit amet');
INSERT INTO banners(id,banner_name,banner_image,description) VALUES (2,'Banner 2','https://nutech-integrasi.app/dummy.jpg','Lerem Ipsum Dolor sit amet');
INSERT INTO banners(id,banner_name,banner_image,description) VALUES (3,'Banner 3','https://nutech-integrasi.app/dummy.jpg','Lerem Ipsum Dolor sit amet');
INSERT INTO banners(id,banner_name,banner_image,description) VALUES (4,'Banner 4','https://nutech-integrasi.app/dummy.jpg','Lerem Ipsum Dolor sit amet');
INSERT INTO banners(id,banner_name,banner_image,description) VALUES (5,'Banner 5','https://nutech-integrasi.app/dummy.jpg','Lerem Ipsum Dolor sit amet');
INSERT INTO banners(id,banner_name,banner_image,description) VALUES (6,'Banner 6','https://nutech-integrasi.app/dummy.jpg','Lerem Ipsum Dolor sit amet');

INSERT INTO services(id,service_code,service_name,service_icon,service_tarif) VALUES (1,'PAJAK','Pajak PBB','https://nutech-integrasi.app/dummy.jpg',40000);
INSERT INTO services(id,service_code,service_name,service_icon,service_tarif) VALUES (2,'PLN','Listrik','https://nutech-integrasi.app/dummy.jpg',10000);
INSERT INTO services(id,service_code,service_name,service_icon,service_tarif) VALUES (3,'PDAM','PDAM Berlangganan','https://nutech-integrasi.app/dummy.jpg',40000);
INSERT INTO services(id,service_code,service_name,service_icon,service_tarif) VALUES (4,'PULSA','Pulsa','https://nutech-integrasi.app/dummy.jpg',40000);
INSERT INTO services(id,service_code,service_name,service_icon,service_tarif) VALUES (5,'PGN','PGN Berlangganan','https://nutech-integrasi.app/dummy.jpg',50000);
INSERT INTO services(id,service_code,service_name,service_icon,service_tarif) VALUES (6,'MUSIK','Musik Berlangganan','https://nutech-integrasi.app/dummy.jpg',50000);
INSERT INTO services(id,service_code,service_name,service_icon,service_tarif) VALUES (7,'TV','TV Berlangganan','https://nutech-integrasi.app/dummy.jpg',50000);
INSERT INTO services(id,service_code,service_name,service_icon,service_tarif) VALUES (8,'PAKET_DATA','Paket data','https://nutech-integrasi.app/dummy.jpg',50000);
INSERT INTO services(id,service_code,service_name,service_icon,service_tarif) VALUES (9,'VOUCHER_GAME','Voucher Game','https://nutech-integrasi.app/dummy.jpg',100000);
INSERT INTO services(id,service_code,service_name,service_icon,service_tarif) VALUES (10,'VOUCHER_MAKANAN','Voucher Makanan','https://nutech-integrasi.app/dummy.jpg',100000);
INSERT INTO services(id,service_code,service_name,service_icon,service_tarif) VALUES (11,'QURBAN','Qurban','https://nutech-integrasi.app/dummy.jpg',200000);
INSERT INTO services(id,service_code,service_name,service_icon,service_tarif) VALUES (12,'ZAKAT','Zakat','https://nutech-integrasi.app/dummy.jpg',300000);
