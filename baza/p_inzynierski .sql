-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 16 Lis 2017, 16:42
-- Wersja serwera: 10.1.28-MariaDB
-- Wersja PHP: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `p_inzynierski`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `forma_zatrudnienia`
--

CREATE TABLE `forma_zatrudnienia` (
  `id_forma_zatrudnienia` int(11) NOT NULL,
  `nazwa` varchar(20) COLLATE utf32_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_polish_ci;

--
-- Zrzut danych tabeli `forma_zatrudnienia`
--

INSERT INTO `forma_zatrudnienia` (`id_forma_zatrudnienia`, `nazwa`) VALUES
(1, 'Dowolne'),
(2, 'Pełny etat'),
(3, 'Część etatu'),
(4, 'Praca czasowa'),
(5, 'Kontrakt');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kategoria`
--

CREATE TABLE `kategoria` (
  `id_kategoria` int(11) NOT NULL,
  `nazwa` varchar(40) COLLATE utf32_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_polish_ci;

--
-- Zrzut danych tabeli `kategoria`
--

INSERT INTO `kategoria` (`id_kategoria`, `nazwa`) VALUES
(1, 'Administracja biurowa'),
(2, 'Doradztwo/Konsulting'),
(3, 'Badania i rozwój'),
(4, 'Bankowość'),
(5, 'BHP / Ochrona środowiska'),
(6, 'Budownictwo'),
(7, 'Call Center'),
(8, 'Edukacja / Szkolenia'),
(9, 'Finanse /Ekonomia'),
(10, 'Franczyzna / Własny biznes'),
(11, 'Hotelarstwo / Turystyka / Gastronomia'),
(12, 'Zasoby  ludzkie'),
(13, 'Nowe media'),
(14, 'Inżynieria'),
(15, 'IT- Administracja'),
(16, 'IT-Rozwoj oprogramowania'),
(17, 'Łancuch dostaw'),
(18, 'Marketing'),
(19, 'Media / Sztuka / Rozrywka'),
(20, 'Nieruchomości'),
(21, 'Prawo'),
(22, 'Produkcja'),
(23, 'Reklama / Grafika / Kreacja / Fotografia'),
(24, 'Sektor publiczny'),
(25, 'Sprzedaż'),
(26, 'Transport /Spedycja'),
(27, 'Ubezpieczenia'),
(28, 'Zakupy'),
(29, 'Kontrola jakości'),
(30, 'Zdrowie / Uroda / Rekreacja'),
(31, 'Energetyka'),
(32, 'Inne');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ogloszenie`
--

CREATE TABLE `ogloszenie` (
  `id_ogloszenie` int(11) NOT NULL,
  `id_uzytkownik` int(11) NOT NULL,
  `id_kategoria` int(11) NOT NULL,
  `id_forma_zatrudnienia` int(11) NOT NULL,
  `id_stanowisko` int(11) NOT NULL,
  `tytul` varchar(50) COLLATE utf32_polish_ci NOT NULL,
  `lokalizacja` varchar(50) COLLATE utf32_polish_ci NOT NULL,
  `zarobki` int(11) NOT NULL,
  `opis` text COLLATE utf32_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `stanowisko`
--

CREATE TABLE `stanowisko` (
  `id_stanowisko` int(11) NOT NULL,
  `nazwa` varchar(30) COLLATE utf32_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_polish_ci;

--
-- Zrzut danych tabeli `stanowisko`
--

INSERT INTO `stanowisko` (`id_stanowisko`, `nazwa`) VALUES
(1, 'Dyrektor / Prezes'),
(2, 'Kierownik'),
(3, 'Specjalista'),
(4, 'Asystent'),
(5, 'Praktykant / Stażysta'),
(6, 'Pracownik fizyczny');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownik`
--

CREATE TABLE `uzytkownik` (
  `id_uzytkownik` int(11) NOT NULL,
  `login` text COLLATE utf32_polish_ci NOT NULL,
  `haslo` text COLLATE utf32_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_polish_ci;

--
-- Zrzut danych tabeli `uzytkownik`
--

INSERT INTO `uzytkownik` (`id_uzytkownik`, `login`, `haslo`) VALUES
(1, 'admin', 'admin');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `forma_zatrudnienia`
--
ALTER TABLE `forma_zatrudnienia`
  ADD PRIMARY KEY (`id_forma_zatrudnienia`);

--
-- Indexes for table `kategoria`
--
ALTER TABLE `kategoria`
  ADD PRIMARY KEY (`id_kategoria`);

--
-- Indexes for table `ogloszenie`
--
ALTER TABLE `ogloszenie`
  ADD PRIMARY KEY (`id_ogloszenie`);

--
-- Indexes for table `stanowisko`
--
ALTER TABLE `stanowisko`
  ADD PRIMARY KEY (`id_stanowisko`);

--
-- Indexes for table `uzytkownik`
--
ALTER TABLE `uzytkownik`
  ADD PRIMARY KEY (`id_uzytkownik`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `forma_zatrudnienia`
--
ALTER TABLE `forma_zatrudnienia`
  MODIFY `id_forma_zatrudnienia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT dla tabeli `kategoria`
--
ALTER TABLE `kategoria`
  MODIFY `id_kategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT dla tabeli `ogloszenie`
--
ALTER TABLE `ogloszenie`
  MODIFY `id_ogloszenie` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `stanowisko`
--
ALTER TABLE `stanowisko`
  MODIFY `id_stanowisko` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  MODIFY `id_uzytkownik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
