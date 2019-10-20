CREATE TABLE gameorder(
	order_id int IDENTITY(1,1) NOT NULL,
	game_id int  NOT NULL,
	member_id int  NOT NULL,
	orderdate date  NOT NULL,
	amount int NOT NULL,
	payment nvarchar(20) NOT NULL,
	delivery_id int NOT NULL
)
CREATE TABLE delivery(
	delivery_id int IDENTITY(1,1) NOT NULL,
	startpoint nvarchar(50) NOT NULL,
	deliverytime date NOT NULL,
	arrivaltime date NOT NULL,
	currentlocation nvarchar(50),
	elapsedtime time,
	transporter nvarchar(20),
	shippingmethod nvarchar(20)
)