CREATE TABLE "clients" (
                           "client_id" serial PRIMARY KEY,
                           "username" varchar,
                           "password" varchar,
                           "name" varchar,
                           "surname" varchar,
                           "email" varchar,
                           "phone_number" varchar,
                           "address_id" integer,
                           "birth_date" timestamp,
                           "nationality" varchar,
                           "gender" varchar,
                           "is_active" bool
);

CREATE TABLE "addresses" (
                             "address_id" serial PRIMARY KEY,
                             "country" varchar,
                             "city" varchar,
                             "street" varchar,
                             "postal_code" varchar,
                             "street_no" varchar
);

CREATE TABLE "payments" (
                            "payment_id" serial PRIMARY KEY,
                            "client_id" integer,
                            "card_number" varchar,
                            "cvv" varchar,
                            "expiration_date" timestamp,
                            "is_active" bool
);

CREATE TABLE "owners" (
                          "owner_id" serial PRIMARY KEY,
                          "username" varchar,
                          "password" varchar,
                          "company_name" varchar,
                          "email" varchar,
                          "phone_number" varchar,
                          "address_id" integer,
                          "nip" varchar,
                          "verified" bool,
                          "is_active" bool
);

CREATE TABLE "admins" (
                          "admin_id" serial PRIMARY KEY,
                          "username" varchar,
                          "password" varchar,
                          "name" varchar
);

CREATE TABLE "hotels" (
                          "hotel_id" serial PRIMARY KEY,
                          "manager_id" integer,
                          "name" varchar,
                          "add_date" timestamp,
                          "description" varchar,
                          "address_id" integer,
                          "email" varchar,
                          "phone_number" varchar,
                          "account_number" varchar,
                          "is_active" bool
);

CREATE TABLE "offers" (
                          "offer_id" serial PRIMARY KEY,
                          "hotel_id" integer,
                          "type" varchar,
                          "offer_name" varchar,
                          "add_date" timestamp,
                          "description" varchar,
                          "bathroom_no" integer,
                          "room_no" integer,
                          "bed_no" integer,
                          "has_kitchen" bool,
                          "pet_friendly" bool,
                          "price" float,
                          "is_active" bool
);

CREATE TABLE "ratings" (
                           "rating_id" serial PRIMARY KEY,
                           "offer_id" integer,
                           "client_id" integer,
                           "rating" integer,
                           "comment" varchar,
                           "hidden" bool
);

CREATE TABLE "reservations" (
                                "reservation_id" serial PRIMARY KEY,
                                "client_id" integer,
                                "offer_id" integer,
                                "date_start" timestamp,
                                "date_end" timestamp,
                                "additional_info" timestamp,
                                "paid_amount" float,
                                "status" varchar
);

CREATE TABLE "favourite_hotels" (
                                    "id" serial PRIMARY KEY,
                                    "client_id" integer,
                                    "hotel_id" integer
);

CREATE TABLE "favourite_offers" (
                                    "id" serial PRIMARY KEY,
                                    "client_id" integer,
                                    "offer_id" integer
);

CREATE TABLE "penalties" (
                             "penalty_id" serial PRIMARY KEY,
                             "reservation_id" integer,
                             "reason" varchar,
                             "amount" float
);

CREATE TABLE "discounts" (
                             "discount_id" serial PRIMARY KEY,
                             "code" varchar,
                             "value_type" integer,
                             "type" integer,
                             "description" varchar,
                             "value" float,
                             "hotel_id" integer
);

ALTER TABLE offers
    ADD CONSTRAINT fk_offers_hotels FOREIGN KEY (hotel_id) REFERENCES hotels (hotel_id);

ALTER TABLE payments
    ADD CONSTRAINT fk_payments_clients FOREIGN KEY (client_id) REFERENCES clients (client_id);

ALTER TABLE clients
    ADD CONSTRAINT fk_clients_addresses FOREIGN KEY (address_id) REFERENCES addresses (address_id);

ALTER TABLE owners
    ADD CONSTRAINT fk_owners_addresses FOREIGN KEY (address_id) REFERENCES addresses (address_id);

ALTER TABLE hotels
    ADD CONSTRAINT fk_hotels_owners FOREIGN KEY (manager_id) REFERENCES owners (owner_id);

ALTER TABLE hotels
    ADD CONSTRAINT fk_hotels_addresses FOREIGN KEY (address_id) REFERENCES addresses (address_id);

ALTER TABLE ratings
    ADD CONSTRAINT fk_ratings_offers FOREIGN KEY (offer_id) REFERENCES offers (offer_id);

ALTER TABLE ratings
    ADD CONSTRAINT fk_ratings_clients FOREIGN KEY (client_id) REFERENCES clients (client_id);

ALTER TABLE favourite_hotels
    ADD CONSTRAINT fk_favourite_hotels_clients FOREIGN KEY (client_id) REFERENCES clients (client_id);

ALTER TABLE favourite_hotels
    ADD CONSTRAINT fk_favourite_hotels_hotels FOREIGN KEY (hotel_id) REFERENCES hotels (hotel_id);

ALTER TABLE favourite_offers
    ADD CONSTRAINT fk_favourite_offers_clients FOREIGN KEY (client_id) REFERENCES clients (client_id);

ALTER TABLE favourite_offers
    ADD CONSTRAINT fk_favourite_offers_offers FOREIGN KEY (offer_id) REFERENCES offers (offer_id);

ALTER TABLE reservations
    ADD CONSTRAINT fk_reservations_clients FOREIGN KEY (client_id) REFERENCES clients (client_id);

ALTER TABLE reservations
    ADD CONSTRAINT fk_reservations_offers FOREIGN KEY (offer_id) REFERENCES offers (offer_id);

ALTER TABLE penalties
    ADD CONSTRAINT fk_penalties_reservations FOREIGN KEY (reservation_id) REFERENCES reservations (reservation_id);

ALTER TABLE discounts
    ADD CONSTRAINT fk_discounts_hotels FOREIGN KEY (hotel_id) REFERENCES hotels (hotel_id);


