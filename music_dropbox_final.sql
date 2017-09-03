-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 03. Sep 2017 um 23:45
-- Server-Version: 10.1.26-MariaDB
-- PHP-Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `music_dropbox`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `filetype`
--

CREATE TABLE `filetype` (
  `id` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `filetype`
--


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `musicfile`
--

CREATE TABLE `musicfile` (
  `id` int(11) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `interpret` varchar(45) DEFAULT NULL,
  `album` varchar(45) DEFAULT NULL,
  `file` varchar(256) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `file_extension` varchar(256) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `musicfile`
--


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `playlist`
--

CREATE TABLE `playlist` (
  `id` int(11) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `playlist`
--



-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `playlistmusicfilemapping`
--

CREATE TABLE `playlistmusicfilemapping` (
  `id` int(11) NOT NULL,
  `music_file_id` int(11) DEFAULT NULL,
  `playlist_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `playlistmusicfilemapping`
--



-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `playlistusermapping`
--

CREATE TABLE `playlistusermapping` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `playlist_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(256) NOT NULL DEFAULT '',
  `password` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `user`
--


--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `filetype`
--
ALTER TABLE `filetype`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`);

--
-- Indizes für die Tabelle `musicfile`
--
ALTER TABLE `musicfile`
  ADD PRIMARY KEY (`id`),
  ADD KEY `musicfile___fk` (`user_id`);

--
-- Indizes für die Tabelle `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indizes für die Tabelle `playlistmusicfilemapping`
--
ALTER TABLE `playlistmusicfilemapping`
  ADD PRIMARY KEY (`id`),
  ADD KEY `MusicFile_id_idx` (`music_file_id`),
  ADD KEY `Playlist_id_idx` (`playlist_id`);

--
-- Indizes für die Tabelle `playlistusermapping`
--
ALTER TABLE `playlistusermapping`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pmu_user_id` (`user_id`),
  ADD KEY `pmu_playlist_id` (`playlist_id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `filetype`
--
ALTER TABLE `filetype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT für Tabelle `musicfile`
--
ALTER TABLE `musicfile`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=461;
--
-- AUTO_INCREMENT für Tabelle `playlist`
--
ALTER TABLE `playlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;
--
-- AUTO_INCREMENT für Tabelle `playlistmusicfilemapping`
--
ALTER TABLE `playlistmusicfilemapping`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=368;
--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=212;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `musicfile`
--
ALTER TABLE `musicfile`
  ADD CONSTRAINT `musicfile___fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints der Tabelle `playlist`
--
ALTER TABLE `playlist`
  ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints der Tabelle `playlistmusicfilemapping`
--
ALTER TABLE `playlistmusicfilemapping`
  ADD CONSTRAINT `MusicFile_id` FOREIGN KEY (`music_file_id`) REFERENCES `musicfile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Playlist_id` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `playlistusermapping`
--
ALTER TABLE `playlistusermapping`
  ADD CONSTRAINT `pmu_playlist_id` FOREIGN KEY (`playlist_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `pmu_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
