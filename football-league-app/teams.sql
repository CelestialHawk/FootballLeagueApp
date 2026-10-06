--
-- PostgreSQL database dump
--

\restrict RiArqPnftaJqsYrUVW5YMA5c72cRW3PGGtNgc6ecTOYYmveXLTdNcV7kDxqZyUY

-- Dumped from database version 18.6
-- Dumped by pg_dump version 18.6

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

--
-- Data for Name: team; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.team (id, name) FROM stdin;
1	Cardiff City
2	Swansea
3	West Ham
4	Middlesbrough
5	Wolves
6	West Brom
7	QPR
8	Stoke City
9	Bristol City
10	Charlton
11	Birmingham
12	Millwall
13	Lincoln City
14	Southampton
15	Wrexham
16	Bolton
17	Blackburn Rovers
18	Norwich City
19	Sheffield United
20	Portsmouth
21	Watford
22	Derby County
23	Preston
24	Burnley
\.


--
-- Name: team_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.team_id_seq', 24, true);


--
-- PostgreSQL database dump complete
--

\unrestrict RiArqPnftaJqsYrUVW5YMA5c72cRW3PGGtNgc6ecTOYYmveXLTdNcV7kDxqZyUY

