--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-10-07 00:23:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 229 (class 1259 OID 17090)
-- Name: purchase; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchase (
    id integer NOT NULL,
    date date,
    customer_id integer,
    product_id integer
);


ALTER TABLE public.purchase OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 17089)
-- Name: purchase_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.purchase ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.purchase_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3345 (class 0 OID 17090)
-- Dependencies: 229
-- Data for Name: purchase; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (1, '2023-11-24', 6, 24);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (2, '2024-07-08', 2, 41);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (3, '2024-02-12', 10, 25);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (4, '2024-08-27', 4, 10);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (5, '2023-12-28', 9, 29);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (6, '2022-11-24', 16, 49);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (7, '2024-02-13', 9, 48);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (8, '2023-11-13', 19, 25);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (9, '2023-07-30', 17, 15);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (10, '2022-09-30', 8, 7);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (11, '2023-02-10', 16, 24);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (12, '2022-10-08', 20, 46);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (13, '2023-10-23', 14, 6);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (14, '2024-07-23', 6, 28);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (15, '2024-07-20', 18, 47);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (16, '2023-10-17', 17, 45);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (17, '2023-12-12', 11, 17);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (18, '2023-08-13', 12, 34);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (19, '2024-04-30', 2, 38);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (20, '2024-05-09', 7, 11);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (21, '2024-06-28', 8, 34);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (22, '2024-07-13', 2, 48);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (23, '2024-07-31', 16, 19);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (24, '2022-09-04', 13, 11);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (25, '2024-01-27', 1, 50);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (26, '2024-08-02', 18, 5);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (27, '2023-06-06', 17, 25);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (28, '2024-05-13', 3, 36);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (29, '2023-08-13', 11, 16);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (30, '2023-07-18', 3, 42);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (31, '2023-07-21', 4, 11);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (32, '2023-09-14', 3, 48);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (33, '2024-06-22', 6, 41);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (34, '2022-08-20', 4, 16);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (35, '2023-05-14', 9, 48);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (36, '2022-11-02', 15, 31);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (37, '2024-02-15', 2, 27);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (38, '2024-04-30', 2, 28);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (39, '2023-10-28', 18, 44);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (40, '2023-09-03', 9, 34);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (41, '2023-12-24', 7, 28);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (42, '2024-09-12', 11, 7);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (43, '2023-08-15', 16, 19);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (44, '2024-01-13', 17, 12);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (45, '2023-10-24', 6, 43);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (46, '2024-08-21', 11, 40);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (47, '2023-01-07', 4, 4);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (48, '2024-01-04', 11, 49);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (49, '2024-07-20', 6, 4);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (50, '2023-08-21', 7, 47);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (51, '2022-10-04', 9, 41);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (52, '2022-12-26', 18, 8);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (53, '2022-09-14', 15, 48);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (54, '2022-10-12', 15, 31);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (55, '2022-12-09', 3, 28);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (56, '2024-07-21', 9, 45);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (57, '2023-01-23', 3, 41);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (58, '2022-10-25', 13, 46);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (59, '2023-03-06', 15, 17);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (60, '2023-09-04', 20, 27);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (61, '2024-04-14', 7, 31);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (62, '2024-03-01', 17, 17);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (63, '2024-03-12', 6, 13);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (64, '2024-04-17', 4, 13);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (65, '2022-07-31', 11, 7);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (66, '2022-07-03', 4, 42);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (67, '2023-06-01', 14, 7);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (68, '2022-09-15', 11, 17);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (69, '2024-07-16', 3, 17);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (70, '2024-05-13', 12, 38);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (71, '2023-06-17', 15, 4);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (72, '2022-08-03', 5, 38);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (73, '2022-08-31', 1, 3);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (74, '2022-08-30', 6, 32);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (75, '2024-03-21', 4, 36);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (76, '2024-07-12', 2, 2);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (77, '2024-05-24', 8, 21);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (78, '2024-07-26', 5, 1);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (79, '2023-12-28', 7, 24);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (80, '2024-02-14', 18, 13);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (81, '2024-08-29', 7, 6);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (82, '2023-02-21', 17, 41);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (83, '2023-01-23', 20, 8);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (84, '2024-08-31', 14, 3);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (85, '2023-09-27', 8, 40);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (86, '2022-07-21', 3, 48);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (87, '2022-09-27', 14, 18);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (88, '2023-01-20', 13, 46);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (89, '2022-09-22', 9, 41);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (90, '2024-06-21', 3, 11);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (91, '2023-01-24', 18, 35);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (92, '2023-09-07', 19, 40);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (93, '2022-07-24', 11, 26);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (94, '2024-08-09', 3, 2);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (95, '2024-06-29', 4, 27);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (96, '2023-05-13', 12, 28);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (97, '2023-03-10', 11, 38);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (98, '2023-11-21', 19, 14);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (99, '2023-08-27', 11, 9);
INSERT INTO public.purchase (id, date, customer_id, product_id) VALUES (100, '2023-11-30', 17, 24);


--
-- TOC entry 3351 (class 0 OID 0)
-- Dependencies: 228
-- Name: purchase_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.purchase_id_seq', 100, true);


--
-- TOC entry 3199 (class 2606 OID 17094)
-- Name: purchase purchase_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT purchase_pkey PRIMARY KEY (id);


--
-- TOC entry 3200 (class 2606 OID 17095)
-- Name: purchase fk2pehe23hwdcyql94c531rbf70; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT fk2pehe23hwdcyql94c531rbf70 FOREIGN KEY (customer_id) REFERENCES public.customer(id);


--
-- TOC entry 3201 (class 2606 OID 17100)
-- Name: purchase fk3s4jktret4nl7m8yhfc8mfrn5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT fk3s4jktret4nl7m8yhfc8mfrn5 FOREIGN KEY (product_id) REFERENCES public.product(id);


-- Completed on 2023-10-07 00:23:26

--
-- PostgreSQL database dump complete
--

