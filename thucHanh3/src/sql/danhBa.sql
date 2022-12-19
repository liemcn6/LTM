use mysql_database;

CREATE TABLE `danhba` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL
);
-- database khoi tao ban dau
INSERT INTO `danhba` (`id`, `name`, `sdt`, `diachi`) VALUES
(2, 'vu', '0965445555','ha noi'),
(3, 'liem', '0967445585','tuyen quang'),
(9, 'duc', '0965666555','ha nam'),
(12, 'minh', '0965445158','bac lieu');

ALTER TABLE `danhba`
  ADD PRIMARY KEY (`id`);
