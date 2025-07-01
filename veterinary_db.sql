--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2025-07-01 23:11:25

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- TOC entry 226 (class 1259 OID 41135)
-- Name: animal_vaccines; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animal_vaccines (
    id bigint NOT NULL,
    application_date date NOT NULL,
    protection_finish_date date NOT NULL,
    protection_start_date date NOT NULL,
    animal_id bigint NOT NULL,
    vaccine_id bigint NOT NULL
);


ALTER TABLE public.animal_vaccines OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 41134)
-- Name: animal_vaccines_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animal_vaccines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.animal_vaccines_id_seq OWNER TO postgres;

--
-- TOC entry 4966 (class 0 OID 0)
-- Dependencies: 225
-- Name: animal_vaccines_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animal_vaccines_id_seq OWNED BY public.animal_vaccines.id;


--
-- TOC entry 218 (class 1259 OID 41089)
-- Name: animals; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animals (
    id bigint NOT NULL,
    age integer,
    breed character varying(255),
    name character varying(255),
    species character varying(255),
    customer_id bigint,
    colour character varying(255),
    date_of_birth date,
    gender character varying(255)
);


ALTER TABLE public.animals OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 41088)
-- Name: animals_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.animals_id_seq OWNER TO postgres;

--
-- TOC entry 4967 (class 0 OID 0)
-- Dependencies: 217
-- Name: animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animals_id_seq OWNED BY public.animals.id;


--
-- TOC entry 228 (class 1259 OID 41142)
-- Name: appointments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.appointments (
    id bigint NOT NULL,
    appointment_date timestamp(6) without time zone,
    animal_id bigint NOT NULL,
    doctor_id bigint NOT NULL
);


ALTER TABLE public.appointments OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 41141)
-- Name: appointments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.appointments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.appointments_id_seq OWNER TO postgres;

--
-- TOC entry 4968 (class 0 OID 0)
-- Dependencies: 227
-- Name: appointments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.appointments_id_seq OWNED BY public.appointments.id;


--
-- TOC entry 230 (class 1259 OID 41149)
-- Name: available_dates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.available_dates (
    id bigint NOT NULL,
    available_date date,
    doctor_id bigint
);


ALTER TABLE public.available_dates OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 41148)
-- Name: available_dates_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.available_dates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.available_dates_id_seq OWNER TO postgres;

--
-- TOC entry 4969 (class 0 OID 0)
-- Dependencies: 229
-- Name: available_dates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.available_dates_id_seq OWNED BY public.available_dates.id;


--
-- TOC entry 220 (class 1259 OID 41098)
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    id bigint NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 41097)
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.customers_id_seq OWNER TO postgres;

--
-- TOC entry 4970 (class 0 OID 0)
-- Dependencies: 219
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;


--
-- TOC entry 222 (class 1259 OID 41107)
-- Name: doctors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doctors (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    specialty character varying(255),
    address character varying(255),
    city character varying(255)
);


ALTER TABLE public.doctors OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 41106)
-- Name: doctors_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.doctors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.doctors_id_seq OWNER TO postgres;

--
-- TOC entry 4971 (class 0 OID 0)
-- Dependencies: 221
-- Name: doctors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.doctors_id_seq OWNED BY public.doctors.id;


--
-- TOC entry 224 (class 1259 OID 41116)
-- Name: vaccines; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vaccines (
    id bigint NOT NULL,
    name character varying(255),
    protection_end_date date,
    protection_start_date date,
    code character varying(255),
    protection_finish_date date,
    animal_id bigint NOT NULL
);


ALTER TABLE public.vaccines OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 41115)
-- Name: vaccines_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vaccines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vaccines_id_seq OWNER TO postgres;

--
-- TOC entry 4972 (class 0 OID 0)
-- Dependencies: 223
-- Name: vaccines_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vaccines_id_seq OWNED BY public.vaccines.id;


--
-- TOC entry 4776 (class 2604 OID 41138)
-- Name: animal_vaccines id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal_vaccines ALTER COLUMN id SET DEFAULT nextval('public.animal_vaccines_id_seq'::regclass);


--
-- TOC entry 4772 (class 2604 OID 41092)
-- Name: animals id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animals ALTER COLUMN id SET DEFAULT nextval('public.animals_id_seq'::regclass);


--
-- TOC entry 4777 (class 2604 OID 41145)
-- Name: appointments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointments ALTER COLUMN id SET DEFAULT nextval('public.appointments_id_seq'::regclass);


--
-- TOC entry 4778 (class 2604 OID 41152)
-- Name: available_dates id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.available_dates ALTER COLUMN id SET DEFAULT nextval('public.available_dates_id_seq'::regclass);


--
-- TOC entry 4773 (class 2604 OID 41101)
-- Name: customers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);


--
-- TOC entry 4774 (class 2604 OID 41110)
-- Name: doctors id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctors ALTER COLUMN id SET DEFAULT nextval('public.doctors_id_seq'::regclass);


--
-- TOC entry 4775 (class 2604 OID 41119)
-- Name: vaccines id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccines ALTER COLUMN id SET DEFAULT nextval('public.vaccines_id_seq'::regclass);


--
-- TOC entry 4956 (class 0 OID 41135)
-- Dependencies: 226
-- Data for Name: animal_vaccines; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.animal_vaccines (id, application_date, protection_finish_date, protection_start_date, animal_id, vaccine_id) FROM stdin;
\.


--
-- TOC entry 4948 (class 0 OID 41089)
-- Dependencies: 218
-- Data for Name: animals; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.animals (id, age, breed, name, species, customer_id, colour, date_of_birth, gender) FROM stdin;
1	\N	Golden	Karabas	Köpek	20	Sarışın	2022-01-01	Erkek
\.


--
-- TOC entry 4958 (class 0 OID 41142)
-- Dependencies: 228
-- Data for Name: appointments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.appointments (id, appointment_date, animal_id, doctor_id) FROM stdin;
2	2025-06-25 14:00:00	1	12
\.


--
-- TOC entry 4960 (class 0 OID 41149)
-- Dependencies: 230
-- Data for Name: available_dates; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.available_dates (id, available_date, doctor_id) FROM stdin;
6	2025-06-25	12
\.


--
-- TOC entry 4950 (class 0 OID 41098)
-- Dependencies: 220
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customers (id, first_name, last_name) FROM stdin;
20	Mehmet	Yüksel
19	Mustafa	Demir
\.


--
-- TOC entry 4952 (class 0 OID 41107)
-- Dependencies: 222
-- Data for Name: doctors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.doctors (id, email, first_name, last_name, phone, specialty, address, city) FROM stdin;
12	ali.a@example.com	Dr. Ali	Demir	05551234567	\N	Şişli	İstanbul
\.


--
-- TOC entry 4954 (class 0 OID 41116)
-- Dependencies: 224
-- Data for Name: vaccines; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vaccines (id, name, protection_end_date, protection_start_date, code, protection_finish_date, animal_id) FROM stdin;
1	Kuduz Gün	\N	2025-01-01	K002	2025-12-31	1
\.


--
-- TOC entry 4973 (class 0 OID 0)
-- Dependencies: 225
-- Name: animal_vaccines_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animal_vaccines_id_seq', 1, false);


--
-- TOC entry 4974 (class 0 OID 0)
-- Dependencies: 217
-- Name: animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animals_id_seq', 1, true);


--
-- TOC entry 4975 (class 0 OID 0)
-- Dependencies: 227
-- Name: appointments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.appointments_id_seq', 2, true);


--
-- TOC entry 4976 (class 0 OID 0)
-- Dependencies: 229
-- Name: available_dates_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.available_dates_id_seq', 7, true);


--
-- TOC entry 4977 (class 0 OID 0)
-- Dependencies: 219
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customers_id_seq', 20, true);


--
-- TOC entry 4978 (class 0 OID 0)
-- Dependencies: 221
-- Name: doctors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.doctors_id_seq', 12, true);


--
-- TOC entry 4979 (class 0 OID 0)
-- Dependencies: 223
-- Name: vaccines_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vaccines_id_seq', 1, true);


--
-- TOC entry 4790 (class 2606 OID 41140)
-- Name: animal_vaccines animal_vaccines_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal_vaccines
    ADD CONSTRAINT animal_vaccines_pkey PRIMARY KEY (id);


--
-- TOC entry 4780 (class 2606 OID 41096)
-- Name: animals animals_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);


--
-- TOC entry 4792 (class 2606 OID 41147)
-- Name: appointments appointments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (id);


--
-- TOC entry 4794 (class 2606 OID 41154)
-- Name: available_dates available_dates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT available_dates_pkey PRIMARY KEY (id);


--
-- TOC entry 4782 (class 2606 OID 41105)
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- TOC entry 4784 (class 2606 OID 41114)
-- Name: doctors doctors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (id);


--
-- TOC entry 4786 (class 2606 OID 41123)
-- Name: doctors uk_caifv0va46t2mu85cg5afmayf; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT uk_caifv0va46t2mu85cg5afmayf UNIQUE (email);


--
-- TOC entry 4788 (class 2606 OID 41121)
-- Name: vaccines vaccines_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT vaccines_pkey PRIMARY KEY (id);


--
-- TOC entry 4799 (class 2606 OID 41167)
-- Name: appointments fk95vepu86o8syqtux9gkr71bhy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fk95vepu86o8syqtux9gkr71bhy FOREIGN KEY (animal_id) REFERENCES public.animals(id);


--
-- TOC entry 4795 (class 2606 OID 41124)
-- Name: animals fkb36lt3kj4mrbdx5btxmp4j60n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- TOC entry 4796 (class 2606 OID 41182)
-- Name: vaccines fkeasdy15b2kp5j4k13x2dfudqs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs FOREIGN KEY (animal_id) REFERENCES public.animals(id);


--
-- TOC entry 4797 (class 2606 OID 41157)
-- Name: animal_vaccines fkiwvg30h9kqewspm3hj6xl2kn9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal_vaccines
    ADD CONSTRAINT fkiwvg30h9kqewspm3hj6xl2kn9 FOREIGN KEY (animal_id) REFERENCES public.animals(id);


--
-- TOC entry 4800 (class 2606 OID 41172)
-- Name: appointments fkmujeo4tymoo98cmf7uj3vsv76; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76 FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);


--
-- TOC entry 4801 (class 2606 OID 41177)
-- Name: available_dates fknb419ilm71d71rm584rk460pk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT fknb419ilm71d71rm584rk460pk FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);


--
-- TOC entry 4798 (class 2606 OID 41162)
-- Name: animal_vaccines fktx6d054a6qgimiblyxm4f5ue; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal_vaccines
    ADD CONSTRAINT fktx6d054a6qgimiblyxm4f5ue FOREIGN KEY (vaccine_id) REFERENCES public.vaccines(id);


-- Completed on 2025-07-01 23:11:25

--
-- PostgreSQL database dump complete
--

