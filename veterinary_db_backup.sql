toc.dat                                                                                             0000600 0004000 0002000 00000044013 15031041677 0014445 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        PGDMP                       }            veterinary_db    17.4    17.4 =    c           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false         d           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false         e           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false         f           1262    41087    veterinary_db    DATABASE     s   CREATE DATABASE veterinary_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'tr-TR';
    DROP DATABASE veterinary_db;
                     postgres    false         �            1259    41135    animal_vaccines    TABLE     �   CREATE TABLE public.animal_vaccines (
    id bigint NOT NULL,
    application_date date NOT NULL,
    protection_finish_date date NOT NULL,
    protection_start_date date NOT NULL,
    animal_id bigint NOT NULL,
    vaccine_id bigint NOT NULL
);
 #   DROP TABLE public.animal_vaccines;
       public         heap r       postgres    false         �            1259    41134    animal_vaccines_id_seq    SEQUENCE        CREATE SEQUENCE public.animal_vaccines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.animal_vaccines_id_seq;
       public               postgres    false    226         g           0    0    animal_vaccines_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.animal_vaccines_id_seq OWNED BY public.animal_vaccines.id;
          public               postgres    false    225         �            1259    41089    animals    TABLE     &  CREATE TABLE public.animals (
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
    DROP TABLE public.animals;
       public         heap r       postgres    false         �            1259    41088    animals_id_seq    SEQUENCE     w   CREATE SEQUENCE public.animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.animals_id_seq;
       public               postgres    false    218         h           0    0    animals_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.animals_id_seq OWNED BY public.animals.id;
          public               postgres    false    217         �            1259    41142    appointments    TABLE     �   CREATE TABLE public.appointments (
    id bigint NOT NULL,
    appointment_date timestamp(6) without time zone,
    animal_id bigint NOT NULL,
    doctor_id bigint NOT NULL
);
     DROP TABLE public.appointments;
       public         heap r       postgres    false         �            1259    41141    appointments_id_seq    SEQUENCE     |   CREATE SEQUENCE public.appointments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.appointments_id_seq;
       public               postgres    false    228         i           0    0    appointments_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.appointments_id_seq OWNED BY public.appointments.id;
          public               postgres    false    227         �            1259    41149    available_dates    TABLE     o   CREATE TABLE public.available_dates (
    id bigint NOT NULL,
    available_date date,
    doctor_id bigint
);
 #   DROP TABLE public.available_dates;
       public         heap r       postgres    false         �            1259    41148    available_dates_id_seq    SEQUENCE        CREATE SEQUENCE public.available_dates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.available_dates_id_seq;
       public               postgres    false    230         j           0    0    available_dates_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.available_dates_id_seq OWNED BY public.available_dates.id;
          public               postgres    false    229         �            1259    41098 	   customers    TABLE     �   CREATE TABLE public.customers (
    id bigint NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL
);
    DROP TABLE public.customers;
       public         heap r       postgres    false         �            1259    41097    customers_id_seq    SEQUENCE     y   CREATE SEQUENCE public.customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.customers_id_seq;
       public               postgres    false    220         k           0    0    customers_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;
          public               postgres    false    219         �            1259    41107    doctors    TABLE     X  CREATE TABLE public.doctors (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    specialty character varying(255),
    address character varying(255),
    city character varying(255)
);
    DROP TABLE public.doctors;
       public         heap r       postgres    false         �            1259    41106    doctors_id_seq    SEQUENCE     w   CREATE SEQUENCE public.doctors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.doctors_id_seq;
       public               postgres    false    222         l           0    0    doctors_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.doctors_id_seq OWNED BY public.doctors.id;
          public               postgres    false    221         �            1259    41116    vaccines    TABLE     �   CREATE TABLE public.vaccines (
    id bigint NOT NULL,
    name character varying(255),
    protection_end_date date,
    protection_start_date date,
    code character varying(255),
    protection_finish_date date,
    animal_id bigint NOT NULL
);
    DROP TABLE public.vaccines;
       public         heap r       postgres    false         �            1259    41115    vaccines_id_seq    SEQUENCE     x   CREATE SEQUENCE public.vaccines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.vaccines_id_seq;
       public               postgres    false    224         m           0    0    vaccines_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.vaccines_id_seq OWNED BY public.vaccines.id;
          public               postgres    false    223         �           2604    41138    animal_vaccines id    DEFAULT     x   ALTER TABLE ONLY public.animal_vaccines ALTER COLUMN id SET DEFAULT nextval('public.animal_vaccines_id_seq'::regclass);
 A   ALTER TABLE public.animal_vaccines ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    226    225    226         �           2604    41092 
   animals id    DEFAULT     h   ALTER TABLE ONLY public.animals ALTER COLUMN id SET DEFAULT nextval('public.animals_id_seq'::regclass);
 9   ALTER TABLE public.animals ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    218    217    218         �           2604    41145    appointments id    DEFAULT     r   ALTER TABLE ONLY public.appointments ALTER COLUMN id SET DEFAULT nextval('public.appointments_id_seq'::regclass);
 >   ALTER TABLE public.appointments ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    228    227    228         �           2604    41152    available_dates id    DEFAULT     x   ALTER TABLE ONLY public.available_dates ALTER COLUMN id SET DEFAULT nextval('public.available_dates_id_seq'::regclass);
 A   ALTER TABLE public.available_dates ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    230    229    230         �           2604    41101    customers id    DEFAULT     l   ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);
 ;   ALTER TABLE public.customers ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    220    219    220         �           2604    41110 
   doctors id    DEFAULT     h   ALTER TABLE ONLY public.doctors ALTER COLUMN id SET DEFAULT nextval('public.doctors_id_seq'::regclass);
 9   ALTER TABLE public.doctors ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    222    221    222         �           2604    41119    vaccines id    DEFAULT     j   ALTER TABLE ONLY public.vaccines ALTER COLUMN id SET DEFAULT nextval('public.vaccines_id_seq'::regclass);
 :   ALTER TABLE public.vaccines ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    223    224    224         \          0    41135    animal_vaccines 
   TABLE DATA           �   COPY public.animal_vaccines (id, application_date, protection_finish_date, protection_start_date, animal_id, vaccine_id) FROM stdin;
    public               postgres    false    226       4956.dat T          0    41089    animals 
   TABLE DATA           l   COPY public.animals (id, age, breed, name, species, customer_id, colour, date_of_birth, gender) FROM stdin;
    public               postgres    false    218       4948.dat ^          0    41142    appointments 
   TABLE DATA           R   COPY public.appointments (id, appointment_date, animal_id, doctor_id) FROM stdin;
    public               postgres    false    228       4958.dat `          0    41149    available_dates 
   TABLE DATA           H   COPY public.available_dates (id, available_date, doctor_id) FROM stdin;
    public               postgres    false    230       4960.dat V          0    41098 	   customers 
   TABLE DATA           >   COPY public.customers (id, first_name, last_name) FROM stdin;
    public               postgres    false    220       4950.dat X          0    41107    doctors 
   TABLE DATA           d   COPY public.doctors (id, email, first_name, last_name, phone, specialty, address, city) FROM stdin;
    public               postgres    false    222       4952.dat Z          0    41116    vaccines 
   TABLE DATA           �   COPY public.vaccines (id, name, protection_end_date, protection_start_date, code, protection_finish_date, animal_id) FROM stdin;
    public               postgres    false    224       4954.dat n           0    0    animal_vaccines_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.animal_vaccines_id_seq', 1, false);
          public               postgres    false    225         o           0    0    animals_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.animals_id_seq', 1, true);
          public               postgres    false    217         p           0    0    appointments_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.appointments_id_seq', 2, true);
          public               postgres    false    227         q           0    0    available_dates_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.available_dates_id_seq', 7, true);
          public               postgres    false    229         r           0    0    customers_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.customers_id_seq', 20, true);
          public               postgres    false    219         s           0    0    doctors_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.doctors_id_seq', 12, true);
          public               postgres    false    221         t           0    0    vaccines_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.vaccines_id_seq', 1, true);
          public               postgres    false    223         �           2606    41140 $   animal_vaccines animal_vaccines_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.animal_vaccines
    ADD CONSTRAINT animal_vaccines_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.animal_vaccines DROP CONSTRAINT animal_vaccines_pkey;
       public                 postgres    false    226         �           2606    41096    animals animals_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.animals DROP CONSTRAINT animals_pkey;
       public                 postgres    false    218         �           2606    41147    appointments appointments_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.appointments DROP CONSTRAINT appointments_pkey;
       public                 postgres    false    228         �           2606    41154 $   available_dates available_dates_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT available_dates_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.available_dates DROP CONSTRAINT available_dates_pkey;
       public                 postgres    false    230         �           2606    41105    customers customers_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public                 postgres    false    220         �           2606    41114    doctors doctors_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.doctors DROP CONSTRAINT doctors_pkey;
       public                 postgres    false    222         �           2606    41123 $   doctors uk_caifv0va46t2mu85cg5afmayf 
   CONSTRAINT     `   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT uk_caifv0va46t2mu85cg5afmayf UNIQUE (email);
 N   ALTER TABLE ONLY public.doctors DROP CONSTRAINT uk_caifv0va46t2mu85cg5afmayf;
       public                 postgres    false    222         �           2606    41121    vaccines vaccines_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT vaccines_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT vaccines_pkey;
       public                 postgres    false    224         �           2606    41167 (   appointments fk95vepu86o8syqtux9gkr71bhy    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fk95vepu86o8syqtux9gkr71bhy FOREIGN KEY (animal_id) REFERENCES public.animals(id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fk95vepu86o8syqtux9gkr71bhy;
       public               postgres    false    4780    228    218         �           2606    41124 #   animals fkb36lt3kj4mrbdx5btxmp4j60n    FK CONSTRAINT     �   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n FOREIGN KEY (customer_id) REFERENCES public.customers(id);
 M   ALTER TABLE ONLY public.animals DROP CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n;
       public               postgres    false    218    220    4782         �           2606    41182 $   vaccines fkeasdy15b2kp5j4k13x2dfudqs    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs FOREIGN KEY (animal_id) REFERENCES public.animals(id);
 N   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs;
       public               postgres    false    4780    224    218         �           2606    41157 +   animal_vaccines fkiwvg30h9kqewspm3hj6xl2kn9    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal_vaccines
    ADD CONSTRAINT fkiwvg30h9kqewspm3hj6xl2kn9 FOREIGN KEY (animal_id) REFERENCES public.animals(id);
 U   ALTER TABLE ONLY public.animal_vaccines DROP CONSTRAINT fkiwvg30h9kqewspm3hj6xl2kn9;
       public               postgres    false    4780    226    218         �           2606    41172 (   appointments fkmujeo4tymoo98cmf7uj3vsv76    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76 FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76;
       public               postgres    false    222    4784    228         �           2606    41177 +   available_dates fknb419ilm71d71rm584rk460pk    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT fknb419ilm71d71rm584rk460pk FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);
 U   ALTER TABLE ONLY public.available_dates DROP CONSTRAINT fknb419ilm71d71rm584rk460pk;
       public               postgres    false    4784    222    230         �           2606    41162 *   animal_vaccines fktx6d054a6qgimiblyxm4f5ue    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal_vaccines
    ADD CONSTRAINT fktx6d054a6qgimiblyxm4f5ue FOREIGN KEY (vaccine_id) REFERENCES public.vaccines(id);
 T   ALTER TABLE ONLY public.animal_vaccines DROP CONSTRAINT fktx6d054a6qgimiblyxm4f5ue;
       public               postgres    false    224    4788    226                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             4956.dat                                                                                            0000600 0004000 0002000 00000000005 15031041677 0014260 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           4948.dat                                                                                            0000600 0004000 0002000 00000000077 15031041677 0014272 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	\N	Golden	Karabas	Köpek	20	Sarışın	2022-01-01	Erkek
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                 4958.dat                                                                                            0000600 0004000 0002000 00000000040 15031041677 0014261 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        2	2025-06-25 14:00:00	1	12
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                4960.dat                                                                                            0000600 0004000 0002000 00000000025 15031041677 0014255 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        6	2025-06-25	12
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           4950.dat                                                                                            0000600 0004000 0002000 00000000050 15031041677 0014252 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        20	Mehmet	Yüksel
19	Mustafa	Demir
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        4952.dat                                                                                            0000600 0004000 0002000 00000000111 15031041677 0014252 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        12	ali.a@example.com	Dr. Ali	Demir	05551234567	\N	Şişli	İstanbul
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                       4954.dat                                                                                            0000600 0004000 0002000 00000000062 15031041677 0014261 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Kuduz Gün	\N	2025-01-01	K002	2025-12-31	1
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                              restore.sql                                                                                         0000600 0004000 0002000 00000034551 15031041677 0015400 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        --
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

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

DROP DATABASE veterinary_db;
--
-- Name: veterinary_db; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE veterinary_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'tr-TR';


ALTER DATABASE veterinary_db OWNER TO postgres;

\connect veterinary_db

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
-- Name: animal_vaccines_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animal_vaccines_id_seq OWNED BY public.animal_vaccines.id;


--
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
-- Name: animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.animals_id_seq OWNED BY public.animals.id;


--
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
-- Name: appointments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.appointments_id_seq OWNED BY public.appointments.id;


--
-- Name: available_dates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.available_dates (
    id bigint NOT NULL,
    available_date date,
    doctor_id bigint
);


ALTER TABLE public.available_dates OWNER TO postgres;

--
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
-- Name: available_dates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.available_dates_id_seq OWNED BY public.available_dates.id;


--
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    id bigint NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL
);


ALTER TABLE public.customers OWNER TO postgres;

--
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
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;


--
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
-- Name: doctors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.doctors_id_seq OWNED BY public.doctors.id;


--
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
-- Name: vaccines_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vaccines_id_seq OWNED BY public.vaccines.id;


--
-- Name: animal_vaccines id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal_vaccines ALTER COLUMN id SET DEFAULT nextval('public.animal_vaccines_id_seq'::regclass);


--
-- Name: animals id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animals ALTER COLUMN id SET DEFAULT nextval('public.animals_id_seq'::regclass);


--
-- Name: appointments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointments ALTER COLUMN id SET DEFAULT nextval('public.appointments_id_seq'::regclass);


--
-- Name: available_dates id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.available_dates ALTER COLUMN id SET DEFAULT nextval('public.available_dates_id_seq'::regclass);


--
-- Name: customers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);


--
-- Name: doctors id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctors ALTER COLUMN id SET DEFAULT nextval('public.doctors_id_seq'::regclass);


--
-- Name: vaccines id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccines ALTER COLUMN id SET DEFAULT nextval('public.vaccines_id_seq'::regclass);


--
-- Data for Name: animal_vaccines; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.animal_vaccines (id, application_date, protection_finish_date, protection_start_date, animal_id, vaccine_id) FROM stdin;
\.
COPY public.animal_vaccines (id, application_date, protection_finish_date, protection_start_date, animal_id, vaccine_id) FROM '$$PATH$$/4956.dat';

--
-- Data for Name: animals; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.animals (id, age, breed, name, species, customer_id, colour, date_of_birth, gender) FROM stdin;
\.
COPY public.animals (id, age, breed, name, species, customer_id, colour, date_of_birth, gender) FROM '$$PATH$$/4948.dat';

--
-- Data for Name: appointments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.appointments (id, appointment_date, animal_id, doctor_id) FROM stdin;
\.
COPY public.appointments (id, appointment_date, animal_id, doctor_id) FROM '$$PATH$$/4958.dat';

--
-- Data for Name: available_dates; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.available_dates (id, available_date, doctor_id) FROM stdin;
\.
COPY public.available_dates (id, available_date, doctor_id) FROM '$$PATH$$/4960.dat';

--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customers (id, first_name, last_name) FROM stdin;
\.
COPY public.customers (id, first_name, last_name) FROM '$$PATH$$/4950.dat';

--
-- Data for Name: doctors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.doctors (id, email, first_name, last_name, phone, specialty, address, city) FROM stdin;
\.
COPY public.doctors (id, email, first_name, last_name, phone, specialty, address, city) FROM '$$PATH$$/4952.dat';

--
-- Data for Name: vaccines; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vaccines (id, name, protection_end_date, protection_start_date, code, protection_finish_date, animal_id) FROM stdin;
\.
COPY public.vaccines (id, name, protection_end_date, protection_start_date, code, protection_finish_date, animal_id) FROM '$$PATH$$/4954.dat';

--
-- Name: animal_vaccines_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animal_vaccines_id_seq', 1, false);


--
-- Name: animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.animals_id_seq', 1, true);


--
-- Name: appointments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.appointments_id_seq', 2, true);


--
-- Name: available_dates_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.available_dates_id_seq', 7, true);


--
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customers_id_seq', 20, true);


--
-- Name: doctors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.doctors_id_seq', 12, true);


--
-- Name: vaccines_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vaccines_id_seq', 1, true);


--
-- Name: animal_vaccines animal_vaccines_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal_vaccines
    ADD CONSTRAINT animal_vaccines_pkey PRIMARY KEY (id);


--
-- Name: animals animals_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);


--
-- Name: appointments appointments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (id);


--
-- Name: available_dates available_dates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT available_dates_pkey PRIMARY KEY (id);


--
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- Name: doctors doctors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (id);


--
-- Name: doctors uk_caifv0va46t2mu85cg5afmayf; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT uk_caifv0va46t2mu85cg5afmayf UNIQUE (email);


--
-- Name: vaccines vaccines_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT vaccines_pkey PRIMARY KEY (id);


--
-- Name: appointments fk95vepu86o8syqtux9gkr71bhy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fk95vepu86o8syqtux9gkr71bhy FOREIGN KEY (animal_id) REFERENCES public.animals(id);


--
-- Name: animals fkb36lt3kj4mrbdx5btxmp4j60n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: vaccines fkeasdy15b2kp5j4k13x2dfudqs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs FOREIGN KEY (animal_id) REFERENCES public.animals(id);


--
-- Name: animal_vaccines fkiwvg30h9kqewspm3hj6xl2kn9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal_vaccines
    ADD CONSTRAINT fkiwvg30h9kqewspm3hj6xl2kn9 FOREIGN KEY (animal_id) REFERENCES public.animals(id);


--
-- Name: appointments fkmujeo4tymoo98cmf7uj3vsv76; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76 FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);


--
-- Name: available_dates fknb419ilm71d71rm584rk460pk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT fknb419ilm71d71rm584rk460pk FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);


--
-- Name: animal_vaccines fktx6d054a6qgimiblyxm4f5ue; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.animal_vaccines
    ADD CONSTRAINT fktx6d054a6qgimiblyxm4f5ue FOREIGN KEY (vaccine_id) REFERENCES public.vaccines(id);


--
-- PostgreSQL database dump complete
--

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       